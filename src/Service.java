import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class Service {
    private final List<User> users = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    public Service() {
    }
    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight){
        if (!this.rooms.isEmpty()) {
            for (Room room : this.rooms) {
                if (room.getId() == roomNumber && room.getType() == roomType) { // validation if room with same id and type exists
                    System.out.println("This room already exists");
                    break; // sends a message and breaks from the loop
                }
                Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
                this.rooms.add(newRoom);
                break;
            }
        } else {
            Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
            this.rooms.add(newRoom);
        }
    }


    // for this method I wrote a lot of code just to do some easy functionalities,
    // I didn't know how to solve it with less code :) At least we got the result we want
    // As you can see there's a lot of blocks that getting repeated
    // I'm working on separating them in functions and make the code more readable and comprehensible
    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut){
        for (User user : this.users){
            for (Room room : this.rooms) {
                if (user.getUserId() == userId && room.getId() == roomNumber) { // check if user and room exist
                    int roomPricePerNight = room.getPricePerNight();
                    long period = this.getPeriod(checkIn,checkOut); // a function below to calculate days between checkIn and checkOut
                    int total = (int) period*roomPricePerNight; // calculate total needed for the booking, and converting into int because period is type of long

                    if (checkIn.before(checkOut)){ // check if checkIn is before checkOut
                        if (bookings.isEmpty()) {
                            if (user.getBalance() > total) { // check if user balance is valid for the booking period
                                Booking newBooking = new Booking(user, room, checkIn, checkOut); // creating the booking object

                                user.setBalance(user.getBalance() - total); // subtracting the total from user balance
                                this.bookings.add(newBooking); // adding the booking to the list of bookings

                            } else {
                                System.out.println("User: " + user.getUserId() + " Cant book Room: " + room.getId() +
                                        " || Balance: " + user.getBalance() + " , Price: " + total);
                            }
                        }else {
                            for (Booking booking : bookings) {
                                if (booking.getRoom().getId() == roomNumber) {
                                    if (checkOut.before(booking.getCheckIn()) || checkIn.after(booking.getCheckOut())) { // checking if the room is not already booked
                                        if (user.getBalance() > total) {
                                            Booking newBooking = new Booking(user, room, checkIn, checkOut);

                                            user.setBalance(user.getBalance() - total);
                                            this.bookings.add(newBooking);

                                        } else {
                                            System.out.println("User: " + user.getUserId()
                                                    + " Cant book Room: " + room.getId() +
                                                    " || Balance: " + user.getBalance() + " , Price: " + total);
                                        }
                                    } else {
                                        System.out.println("User: " + user.getUserId()
                                                + " Cant book Room: " + room.getId() +
                                                " || Room is already booked");
                                    }
                                } else {
                                    if (user.getBalance() > total) {
                                        Booking newBooking = new Booking(user, room, checkIn, checkOut);
                                        user.setBalance(user.getBalance() - total);
                                        this.bookings.add(newBooking);
                                        break;

                                    } else {
                                        System.out.println("User: " + user.getUserId()
                                                + " Cant book Room: " + room.getId() +
                                                " || Balance: " + user.getBalance() + " , Price: " + total);
                                    }
                                }
                            }
                        }
                    }else {
                        System.out.println("User: " + user.getUserId() + " Cant book Room: " + room.getId() +
                                " || Check in and check out dates are invalid");
                    }
                }
            }
        }
    }
    public void printAll(){
        System.out.println("=========================================");
        System.out.println("              All Rooms");
        System.out.println("=========================================");

        System.out.println("Room ID || Room Type      || Price Per Night");
        for (Room room : this.rooms){
            System.out.println(room.getId() + "       || " + room.getType() + "         || " + room.getPricePerNight());
        }

        System.out.println("=========================================");
        System.out.println("              Booked Rooms");
        System.out.println("=========================================");
        System.out.println("User   || Room  || Check In                      || Check Out                     || Period");
        for (Booking booking : bookings){
            System.out.println(booking.getUser().getUserId()
                    + "      || " + booking.getRoom().getId()
                    + "     || " + booking.getCheckIn()
                    + " || " + booking.getCheckOut()
                    + " || " + this.getPeriod(booking.getCheckIn(),booking.getCheckOut()) + " days");
        }
        System.out.println("=========================================");
    }
    public void setUser(int userId, int balance){
        if (!this.users.isEmpty()) {
            for (User user : this.users) {
                if (user.getUserId() == userId) { // validating if user with the ID already exists
                    System.out.println("This room already exists");
                    break; // sends a message and breaks from the loop
                }
                User newUser = new User(userId, balance);
                this.users.add(newUser);
                break;
            }
        } else {
            User newUser = new User(userId, balance);
            this.users.add(newUser);
        }
    }
    public void printAllUsers(){
        System.out.println("              Users balances");
        System.out.println("=========================================");
        for (User user : users){
            System.out.println("User :" + user.getUserId() + " || Balance: " + user.getBalance());
        }
        System.out.println("=========================================");
    }
    private long getPeriod(Date checkIn, Date checkOut) { // calculating days between checkIn and checkOut
        long diffInMillis = checkOut.getTime() - checkIn.getTime();
        long daysBetween = TimeUnit.MILLISECONDS.toDays(diffInMillis);
        return daysBetween;
    }
}