package ma.skypay.domain.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.skypay.domain.room.model.Room;
import ma.skypay.domain.user.model.User;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private User user;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
