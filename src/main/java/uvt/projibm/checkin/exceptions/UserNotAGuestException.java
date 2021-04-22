package uvt.projibm.checkin.exceptions;

public class UserNotAGuestException extends RuntimeException {
    public UserNotAGuestException(Integer id) {
        super(String.format("The user with id %d is not a guest!", id));
    }
}
