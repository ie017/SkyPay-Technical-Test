package ma.skypay.domain.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.skypay.domain.room.model.Room;
import ma.skypay.domain.user.model.User;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private User user;
    private Room room;
    private Date checkIn;
    private Date checkOut;

    @Override
    public String toString() {
        return "[Booking] : [Room=" + room + ", User=" + user + ", From=" + checkIn + ", To=" + checkOut + "]";
    }
}
