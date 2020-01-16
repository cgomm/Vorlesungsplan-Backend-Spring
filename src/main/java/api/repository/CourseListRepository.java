package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import api.model.CourseModel;

import java.util.List;

@Repository
public interface CourseListRepository extends JpaRepository<CourseModel, Long> {
    String findByValue(String label);

    String findByName(String value);

    @Query(value = "SELECT course_value FROM course_model", nativeQuery = true)
    List<String> findAllValue();

    @Query(value = "SELECT course_name FROM course_model", nativeQuery = true)
    List<String> findAllName();
}
