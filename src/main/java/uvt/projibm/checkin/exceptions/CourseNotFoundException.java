package uvt.projibm.checkin.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Integer id) {
        super(String.format("Could not find the course with id %d", id));
    }
}
