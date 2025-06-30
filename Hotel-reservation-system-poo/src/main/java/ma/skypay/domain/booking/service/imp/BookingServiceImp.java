package ma.skypay.domain.booking.service.imp;

import ma.skypay.domain.booking.exception.InvalidBookingDatesException;
import ma.skypay.domain.booking.model.Booking;
import ma.skypay.domain.booking.service.BookingService;
import ma.skypay.domain.room.enumeration.RoomType;
import ma.skypay.domain.room.exception.RoomNotAvailableException;
import ma.skypay.domain.room.exception.RoomNotFoundException;
import ma.skypay.domain.room.model.Room;
import ma.skypay.domain.user.exception.NotEnoughBalanceException;
import ma.skypay.domain.user.exception.UserNotFoundException;
import ma.skypay.domain.user.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingServiceImp implements BookingService {

    private List<Room> rooms = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    @Override
    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        rooms.removeIf(r -> r.getRoomNumber() == roomNumber);
        rooms.add(0, new Room(roomNumber, roomType, roomPricePerNight));
    }

    @Override
    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        if (checkOut.before(checkIn)) throw new InvalidBookingDatesException("Dates de réservation invalides");

        Room room = rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().orElseThrow(
                () -> new RoomNotFoundException("Salle introuvable")
        );
        User user = users.stream().filter(u -> u.getId() == userId).findFirst().orElseThrow(
                () -> new UserNotFoundException("Uilisateur introuvable")
        );

        bookings.forEach(
                booking -> {
                    if (booking.getRoom().equals(room) &&
                            !(checkOut.before(booking.getCheckIn()) || checkIn.after(booking.getCheckOut())))
                        throw new RoomNotAvailableException("Salle non disponible pour la période sélectionnée");
                }
        );

        int nights = (int) ((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
        int cost = nights * room.getPricePerNight();

        if (user.getBalance() < cost) throw new NotEnoughBalanceException("Solde insuffisant");

        user.deductBalance(cost);
        bookings.add(0, new Booking(user, room, checkIn, checkOut));
    }

    @Override
    public void printAll() {
        System.out.println("Rooms:");
        rooms.forEach(System.out::println);
        System.out.println("\nBookings:");
        bookings.forEach(System.out::println);
    }

    @Override
    public void setUser(int userId, int balance) {
        users.removeIf(u -> u.getId() == userId);
        users.add(0, new User(userId, balance));
    }

    @Override
    public void printAllUsers() {
        System.out.println("Users:");
        users.forEach(System.out::println);
    }
}
