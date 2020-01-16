package api.component;

import api.repository.CourseListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimeTableLoaderComponent implements ApplicationRunner {
    private List courseValueList = new ArrayList();

    @Autowired
    CourseListRepository courseListRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        courseValueList = courseListRepository.findAllValue();


    }
}
