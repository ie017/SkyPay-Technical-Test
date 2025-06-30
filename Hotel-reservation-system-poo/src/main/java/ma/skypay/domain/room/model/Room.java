package ma.skypay.domain.room.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.skypay.domain.room.enumeration.RoomType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    private int roomNumber;
    private RoomType type;
    private int pricePerNight;
}

