package api.vorlesungsplan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import api.repository.CourseListRepository;
import api.model.CourseModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VorlesungsplanLoader {
    private final static String VORLESUNGSPLAN_ICAL_URL = "https://vorlesungsplan.dhbw-mannheim.de/ical.php";
    private Map courseMap = new HashMap();
    private List courseView = new ArrayList();

    @Autowired
    CourseListRepository courseListRepository;

    public Map loadCourses() {
        Document doc;
        try {
            doc = Jsoup.connect(this.VORLESUNGSPLAN_ICAL_URL).get();
            Elements courseJSON = doc.select("#class_select");
            //String courseTinf18 = doc.select("option[value=7431001]").text();
            for(Element element: courseJSON) {
                System.out.println(element.attr("optgroup"));
            }
            //System.out.println(courseTinf18);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseMap;
    }

    public Map courseListRequest() {
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
        return searchlist;
    }
}
