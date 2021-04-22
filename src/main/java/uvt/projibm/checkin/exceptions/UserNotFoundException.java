package uvt.projibm.checkin.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super(String.format("Could not find the user with id %d", id));
    }
}
