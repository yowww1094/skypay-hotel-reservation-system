import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        System.out.println("===================================");
        System.out.println("Welcome to Hotel Reservation System");
        System.out.println("===================================");

        Service service = new Service();

        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.MASTER, 3000);

        service.setUser(1, 5000);
        service.setUser(2, 10000);

        service.bookRoom(1, 2, new Date("06/30/2026"), new Date("07/07/2026"));
        service.bookRoom(1, 2, new Date("07/07/2026"), new Date("06/30/2026"));
        service.bookRoom(1, 1, new Date("07/07/2026"), new Date("07/08/2026"));

        service.bookRoom(2, 1, new Date("07/07/2026"), new Date("07/09/2026"));
        service.bookRoom(2, 3, new Date("07/07/2026"), new Date("07/08/2026"));

        service.printAll();

        service.printAllUsers();


    }
}
