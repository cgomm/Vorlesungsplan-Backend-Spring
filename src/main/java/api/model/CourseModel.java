package api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class CourseModel {
    private String courseName;
    @Id
    private String courseValue;

    @OneToMany(mappedBy = "course")
    private List<EventModel> events;

    public CourseModel() {}

    public CourseModel(String courseName, String courseValue) {
        this.courseName = courseName;
        this.courseValue = courseValue;
    }

    @Override
    public String toString(){
        return String.format(
                "Course[Name=%s,Value=%s]", this.courseName, this.courseValue);
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseValue(String courseValue) {
        this.courseValue = courseValue;
    }

    public String getCourseValue() {
        return courseValue;
    }

    public String getCourseName() {
        return courseName;
    }
}
