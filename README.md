# SkyPay Hotel Reservation System

A Java-based hotel reservation system developed as part of a technical assessment. This console-based application allows users to book rooms, manage user balances, and handle reservation conflicts based on date ranges.

## ✨ Features

- Room booking with check-in and check-out date validation
- User balance checking before reservation confirmation
- Prevention of overlapping bookings for the same room
- Price calculation based on number of nights and room rate
- Clean object-oriented structure using classes like `User`, `Room`, and `Booking`

## 🛠️ Technologies Used

- Java 17+
- Java Collections (`ArrayList`, `Date`)
- `SimpleDateFormat` and `ChronoUnit` for date handling

## 📦 Project Structure

- `User.java`: Represents a hotel customer with a balance
- `Room.java`: Represents hotel rooms with ID and price
- `Booking.java`: Represents a booking including dates and total cost
- `Service.java`: Handles booking logic and business rules
- `Main.java`: Entry point with test scenarios

## 🚀 How to Run

#### 1. Clone the repository:
   ```bash
   git clone https://github.com/yowww1094/skypay-hotel-reservation-system
```

#### 2. Open the project in your favorite Java IDE (e.g. IntelliJ, Eclipse)

#### 3. Run Main.java
