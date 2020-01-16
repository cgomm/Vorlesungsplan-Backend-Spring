package api.ical;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ICalService {
    private final String ICAL_DHBW_LINK_TEMPLATE = "https://vorlesungsplan.dhbw-mannheim.de/ical.php?uid=";
    private final String USER_AGENT = "JavaBackend/1.0";

    public Calendar getCourseCalender(String courseUID) {
        Calendar cal = new Calendar();
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(ICAL_DHBW_LINK_TEMPLATE + courseUID).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("userAgent", USER_AGENT);
            connection.setInstanceFollowRedirects(true);
            int responseCode = connection.getResponseCode();
            System.out.println("GET RESPONSE CODE --> " + responseCode);
            System.out.println(connection.getResponseMessage());
            System.out.println(connection.getContent().toString());
            if(responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                cal = new CalendarBuilder().build(in);
                in.close();
            } else {
                System.err.println("GET REQUEST FAILED");
            }
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
        return cal;
    }
}
