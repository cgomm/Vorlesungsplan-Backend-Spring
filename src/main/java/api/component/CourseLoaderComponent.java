package api.component;

import api.model.CourseModel;
import api.repository.CourseListRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CourseLoaderComponent implements ApplicationRunner {
    private final static String VORLESUNGSPLAN_ICAL_URL = "https://vorlesungsplan.dhbw-mannheim.de/ical.php";

    @Autowired
    private CourseListRepository courseListRepository;

    @Autowired
    public CourseLoaderComponent(CourseListRepository courseListRepository) {
        this.courseListRepository = courseListRepository;
    }

    public void run(ApplicationArguments args) throws Exception {
        loadCoursesIntoDatabase();
    }

    private void loadCoursesIntoDatabase() {
        courseListRepository.deleteAll();
        Map searchlist = new HashMap();

        try {
            Document doc = Jsoup.connect(VORLESUNGSPLAN_ICAL_URL).get();
            Elements courseList = doc.select("option");
            courseList.remove(0);
            for(Element element: courseList) {
                Attributes attributes = element.attributes();
                searchlist.putAll(attributes.dataset());
                searchlist.put(attributes.get("label"), attributes.get("value"));

                CourseModel course = new CourseModel();
                course.setCourseName(attributes.get("label"));
                course.setCourseValue(attributes.get("value"));

                courseListRepository.save(course);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

