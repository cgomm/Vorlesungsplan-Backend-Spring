package api.controller;

import api.model.CourseModel;
import api.repository.CourseListRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    private final static String VORLESUNGSPLAN_ICAL_URL = "https://vorlesungsplan.dhbw-mannheim.de/ical.php";

    @Autowired
    CourseListRepository courseListRepository;

    @PatchMapping(path = "/createCourseTable")
    public @ResponseBody String createCourseTable() {
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
            return "INTERNAL SERVER ERROR";
        }
        return "Re-created the coursetable!";
    }


}
