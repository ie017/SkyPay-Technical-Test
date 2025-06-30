package ma.skypay.domain.room.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String s) {
        super(s);
    }
}
