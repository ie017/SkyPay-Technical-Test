package ma.skypay.domain.booking.service;

import ma.skypay.domain.room.enumeration.RoomType;
import ma.skypay.domain.room.model.Room;
import ma.skypay.domain.user.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public interface BookingService {
    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight);
    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut);
    void printAll();
    void setUser(int userId, int balance);
    void printAllUsers();
}
