package service;

import net.fortuna.ical4j.model.Calendar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.ical.ICalService;

@SpringBootApplication
public class ApplicationMainExec {
    public static void main(String[] args) {
        ICalService ical = new ICalService();

        Calendar cal = ical.createCalender("7431001");
        System.out.println(cal.toString());
        SpringApplication.run(ApplicationMainExec.class, args);
    }

}
