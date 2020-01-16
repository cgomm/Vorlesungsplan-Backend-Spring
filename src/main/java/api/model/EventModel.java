package api.model;

import net.fortuna.ical4j.model.DateTime;

import javax.persistence.*;

@Entity
public class EventModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String eventLocation;
    private DateTime startDate;
    private DateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COURSE_VALUE")
    private CourseModel course;

    protected EventModel() {}

    public EventModel(String eventName, String eventLocation, DateTime startDate, DateTime endDate) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }
}
