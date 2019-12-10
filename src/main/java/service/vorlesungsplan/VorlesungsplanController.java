package service.vorlesungsplan;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VorlesungsplanController {
    @RequestMapping("/vorlesungsplan")
    public List greeting(@RequestParam(value="searchterm", defaultValue="") String searchterm) {
        return VorlesungsplanLoader.searchCourses(searchterm);
    }
}
