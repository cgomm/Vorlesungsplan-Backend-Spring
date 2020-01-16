package api.vorlesungsplan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VorlesungsplanController {
    @RequestMapping("/vorlesungsplan")
    public Map greeting() {
        return new VorlesungsplanLoader().loadCourses();
    }

    @RequestMapping("/courselist")
    public Map courses() {
        return new VorlesungsplanLoader().courseListRequest();
    }
}
