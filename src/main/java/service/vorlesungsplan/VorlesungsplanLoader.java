package service.vorlesungsplan;

import controller.JsoupNetworkController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VorlesungsplanLoader {
    private final static String VORLESUNGSPLAN_ICAL_URL = "https://vorlesungsplan.dhbw-mannheim.de/ical.php";
    private Map courseMap = new HashMap();
    private List courseView = new ArrayList();

    public VorlesungsplanLoader() {
        this.courseMap = loadCourses();
    }

    public VorlesungsplanLoader(String search) {
        this.courseView = searchCourses(search);
    }

    public Map loadCourses() {
        Document doc;
        try {
            doc = Jsoup.connect(this.VORLESUNGSPLAN_ICAL_URL).get();
            Elements courseJSON = doc.select("#class_select");
            String courseTinf18 = doc.select("option[value=7431001]").text();
            for(Element element: courseJSON) {
                System.out.println(element.attr("optgroup"));
            }
            System.out.println(courseTinf18);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseMap;
    }

    public static List searchCourses(String searchterm) {
        List searchlist = new ArrayList();

        try {
            Document doc = Jsoup.connect(VORLESUNGSPLAN_ICAL_URL).get();
            Elements courseList = doc.select("option[label*=" + searchterm + "]");
            /*for(Element element: courseList) {
                searchlist.add(element.select("option[label*=" + searchterm + "]").text());
            }*/
            searchlist = courseList.eachText();
            searchlist.stream().distinct().filter(courseName -> courseName.equals("")).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchlist;
        //return String.join(", ", searchlist);
    }

    public List getCourseView() {
        return courseView;
    }
}
