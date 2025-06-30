package ma.skypay;

import ma.skypay.domain.booking.service.BookingService;
import ma.skypay.domain.booking.service.imp.BookingServiceImp;
import ma.skypay.domain.room.enumeration.RoomType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {
        BookingService service = new BookingServiceImp();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        service.setUser(1, 5000);
        service.setUser(2, 10000);

        //service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026")); // give Solde insuffisant as message
        //service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026")); // give Dates de réservation invalides as message
        service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));
        //service.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026")); // give Salle non disponible pour la période sélectionnée as message
        service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

        service.setRoom(1, RoomType.SUITE, 10000);

        service.printAll();
        service.printAllUsers();
    }
}