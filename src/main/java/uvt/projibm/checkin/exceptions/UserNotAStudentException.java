package uvt.projibm.checkin.exceptions;

public class UserNotAStudentException extends RuntimeException {
    public UserNotAStudentException(Integer id) {
        super(String.format("User with id %d is not a students"));
    }
}
