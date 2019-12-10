package service.ical;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ICalService {
    private final String ICAL_DHBW_LINK_TEMPLATE = "http://vorlesungsplan.dhbw-mannheim.de/ical.php?uid=";

    public Calendar createCalender(String courseUID) {
        Calendar cal = new Calendar();
        try {
            InputStream is = new URL(ICAL_DHBW_LINK_TEMPLATE + courseUID).openStream();
            cal = new CalendarBuilder().build(is);
            is.close();
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
        return cal;
    }
}
