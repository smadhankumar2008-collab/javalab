package busticketbooking;

import java.sql.*;
import java.util.Scanner;

public class Main {

    // Database Connection
    static final String URL = "jdbc:mysql://localhost:3306/BusTicketBooking";
    static final String USER = "root";
    static final String PASSWORD = "test@123";   // Change to your MySQL password

    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database Connected Successfully!");

            while (true) {

                System.out.println("\n==============================");
                System.out.println(" BUS TICKET BOOKING SYSTEM");
                System.out.println("==============================");
                System.out.println("1. Add User");
                System.out.println("2. View Users");
                System.out.println("3. Add Bus");
                System.out.println("4. View Buses");
                System.out.println("5. Add Route");
                System.out.println("6. View Routes");
                System.out.println("7. Add Schedule");
                System.out.println("8. View Schedules");
                System.out.println("9. Book Ticket");
                System.out.println("10. View Bookings");
                System.out.println("11. Make Payment");
                System.out.println("12. Cancel Ticket");
                System.out.println("13. Exit");

                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        addUser();
                        break;

                    case 2:
                        viewUsers();
                        break;

                    case 3:
                        addBus();
                        break;

                    case 4:
                        viewBus();
                        break;

                    case 5:
                        addRoute();
                        break;

                    case 6:
                        viewRoute();
                        break;

                    case 7:
                        addSchedule();
                        break;

                    case 8:
                        viewSchedule();
                        break;

                    case 9:
                        bookTicket();
                        break;

                    case 10:
                        viewBooking();
                        break;

                    case 11:
                        makePayment();
                        break;

                    case 12:
                        cancelTicket();
                        break;

                    case 13:
                        System.out.println("Thank You...");
                        con.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // ================= USER =================

   // ================= USER =================

static void addUser() throws SQLException {

    System.out.print("Enter Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Email: ");
    String email = sc.nextLine();

    System.out.print("Enter Phone: ");
    String phone = sc.nextLine();

    System.out.print("Enter Password: ");
    String password = sc.nextLine();

    String sql = "INSERT INTO Users(Name, Email, Phone, Password) VALUES (?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, name);
    ps.setString(2, email);
    ps.setString(3, phone);
    ps.setString(4, password);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("User Added Successfully...");
    } else {
        System.out.println("Failed to Add User...");
    }

    ps.close();
}

static void viewUsers() throws SQLException {

    String sql = "SELECT * FROM Users";

    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery(sql);

    System.out.println("\n--------------------------------------------------------------");
    System.out.printf("%-10s %-20s %-25s %-15s\n",
            "UserID", "Name", "Email", "Phone");
    System.out.println("--------------------------------------------------------------");

    while (rs.next()) {

        System.out.printf("%-10d %-20s %-25s %-15s\n",
                rs.getInt("UserID"),
                rs.getString("Name"),
                rs.getString("Email"),
                rs.getString("Phone"));
    }

    rs.close();
    st.close();
}

    // ================= BUS =================

    // ================= BUS =================

static void addBus() throws SQLException {

    System.out.print("Enter Bus Name: ");
    String busName = sc.nextLine();

    System.out.print("Enter Bus Number: ");
    String busNumber = sc.nextLine();

    System.out.print("Enter Bus Type: ");
    String busType = sc.nextLine();

    System.out.print("Enter Total Seats: ");
    int totalSeats = sc.nextInt();
    sc.nextLine();

    String sql = "INSERT INTO Bus(BusName, BusNumber, BusType, TotalSeats) VALUES (?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, busName);
    ps.setString(2, busNumber);
    ps.setString(3, busType);
    ps.setInt(4, totalSeats);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Bus Added Successfully...");
    } else {
        System.out.println("Failed to Add Bus...");
    }

    ps.close();
}

static void viewBus() throws SQLException {

    String sql = "SELECT * FROM Bus";

    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery(sql);

    System.out.println("\n--------------------------------------------------------------------------");
    System.out.printf("%-8s %-20s %-15s %-15s %-10s\n",
            "BusID", "Bus Name", "Bus Number", "Bus Type", "Seats");
    System.out.println("--------------------------------------------------------------------------");

    while (rs.next()) {

        System.out.printf("%-8d %-20s %-15s %-15s %-10d\n",
                rs.getInt("BusID"),
                rs.getString("BusName"),
                rs.getString("BusNumber"),
                rs.getString("BusType"),
                rs.getInt("TotalSeats"));
    }

    rs.close();
    st.close();
}

    // ================= ROUTE =================

    // ================= ROUTE =================

static void addRoute() throws SQLException {

    System.out.print("Enter Source: ");
    String source = sc.nextLine();

    System.out.print("Enter Destination: ");
    String destination = sc.nextLine();

    System.out.print("Enter Fare: ");
    double fare = sc.nextDouble();
    sc.nextLine();

    String sql = "INSERT INTO Route(Source, Destination, Fare) VALUES (?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, source);
    ps.setString(2, destination);
    ps.setDouble(3, fare);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Route Added Successfully...");
    } else {
        System.out.println("Failed to Add Route...");
    }

    ps.close();
}

static void viewRoute() throws SQLException {

    String sql = "SELECT * FROM Route";

    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery(sql);

    System.out.println("\n--------------------------------------------------------------");
    System.out.printf("%-10s %-20s %-20s %-10s\n",
            "RouteID", "Source", "Destination", "Fare");
    System.out.println("--------------------------------------------------------------");

    while (rs.next()) {

        System.out.printf("%-10d %-20s %-20s %-10.2f\n",
                rs.getInt("RouteID"),
                rs.getString("Source"),
                rs.getString("Destination"),
                rs.getDouble("Fare"));
    }

    rs.close();
    st.close();
}

    // ================= SCHEDULE =================

    // ================= SCHEDULE =================

static void addSchedule() throws SQLException {

    System.out.print("Enter Bus ID: ");
    int busID = sc.nextInt();

    System.out.print("Enter Route ID: ");
    int routeID = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Journey Date (YYYY-MM-DD): ");
    String journeyDate = sc.nextLine();

    System.out.print("Enter Departure Time (HH:MM:SS): ");
    String departureTime = sc.nextLine();

    System.out.print("Enter Arrival Time (HH:MM:SS): ");
    String arrivalTime = sc.nextLine();

    String sql = "INSERT INTO Schedule(BusID, RouteID, JourneyDate, DepartureTime, ArrivalTime) VALUES (?, ?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, busID);
    ps.setInt(2, routeID);
    ps.setDate(3, Date.valueOf(journeyDate));
    ps.setTime(4, Time.valueOf(departureTime));
    ps.setTime(5, Time.valueOf(arrivalTime));

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Schedule Added Successfully...");
    } else {
        System.out.println("Failed to Add Schedule...");
    }

    ps.close();
}

static void viewSchedule() throws SQLException {

    String sql = "SELECT * FROM Schedule";

    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery(sql);

    System.out.println("\n----------------------------------------------------------------------------");
    System.out.printf("%-12s %-8s %-10s %-15s %-15s %-15s\n",
            "ScheduleID", "BusID", "RouteID", "Journey Date", "Departure", "Arrival");
    System.out.println("----------------------------------------------------------------------------");

    while (rs.next()) {

        System.out.printf("%-12d %-8d %-10d %-15s %-15s %-15s\n",
                rs.getInt("ScheduleID"),
                rs.getInt("BusID"),
                rs.getInt("RouteID"),
                rs.getDate("JourneyDate"),
                rs.getTime("DepartureTime"),
                rs.getTime("ArrivalTime"));
    }

    rs.close();
    st.close();
}

    // ================= BOOKING =================

    // ================= BOOKING =================

static void bookTicket() throws SQLException {

    System.out.print("Enter User ID: ");
    int userID = sc.nextInt();

    System.out.print("Enter Schedule ID: ");
    int scheduleID = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Seat Number: ");
    String seatNumber = sc.nextLine();

    System.out.print("Enter Booking Date (YYYY-MM-DD): ");
    String bookingDate = sc.nextLine();

    System.out.print("Enter Total Amount: ");
    double amount = sc.nextDouble();
    sc.nextLine();

    System.out.print("Enter Booking Status (Booked/Confirmed): ");
    String status = sc.nextLine();

    String sql = "INSERT INTO Booking(UserID, ScheduleID, SeatNumber, BookingDate, TotalAmount, Status) VALUES (?, ?, ?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, userID);
    ps.setInt(2, scheduleID);
    ps.setString(3, seatNumber);
    ps.setDate(4, Date.valueOf(bookingDate));
    ps.setDouble(5, amount);
    ps.setString(6, status);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Ticket Booked Successfully...");
    } else {
        System.out.println("Ticket Booking Failed...");
    }

    ps.close();
}

static void viewBooking() throws SQLException {

    String sql = "SELECT * FROM Booking";

    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery(sql);

    System.out.println("\n----------------------------------------------------------------------------------------------");
    System.out.printf("%-10s %-8s %-12s %-10s %-15s %-12s %-12s\n",
            "BookingID", "UserID", "ScheduleID", "Seat", "Booking Date", "Amount", "Status");
    System.out.println("----------------------------------------------------------------------------------------------");

    while (rs.next()) {

        System.out.printf("%-10d %-8d %-12d %-10s %-15s %-12.2f %-12s\n",
                rs.getInt("BookingID"),
                rs.getInt("UserID"),
                rs.getInt("ScheduleID"),
                rs.getString("SeatNumber"),
                rs.getDate("BookingDate"),
                rs.getDouble("TotalAmount"),
                rs.getString("Status"));
    }

    rs.close();
    st.close();
}

    // ================= PAYMENT =================

   // ================= PAYMENT =================

static void makePayment() throws SQLException {

    System.out.print("Enter Booking ID: ");
    int bookingID = sc.nextInt();

    System.out.print("Enter Amount: ");
    double amount = sc.nextDouble();
    sc.nextLine();

    System.out.print("Enter Payment Method (Cash/UPI/Card): ");
    String method = sc.nextLine();

    System.out.print("Enter Payment Status (Paid/Pending): ");
    String status = sc.nextLine();

    String sql = "INSERT INTO Payment(BookingID, Amount, PaymentMethod, PaymentStatus) VALUES (?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, bookingID);
    ps.setDouble(2, amount);
    ps.setString(3, method);
    ps.setString(4, status);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Payment Successful...");
    } else {
        System.out.println("Payment Failed...");
    }

    ps.close();
}

// ================= CANCELLATION =================

static void cancelTicket() throws SQLException {

    System.out.print("Enter Booking ID: ");
    int bookingID = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Cancel Date (YYYY-MM-DD): ");
    String cancelDate = sc.nextLine();

    System.out.print("Enter Refund Amount: ");
    double refund = sc.nextDouble();
    sc.nextLine();

    String sql = "INSERT INTO Cancellation(BookingID, CancelDate, RefundAmount) VALUES (?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, bookingID);
    ps.setDate(2, Date.valueOf(cancelDate));
    ps.setDouble(3, refund);

    int rows = ps.executeUpdate();

    if (rows > 0) {

        String update = "UPDATE Booking SET Status='Cancelled' WHERE BookingID=?";

        PreparedStatement ps2 = con.prepareStatement(update);
        ps2.setInt(1, bookingID);
        ps2.executeUpdate();
        ps2.close();

        System.out.println("Ticket Cancelled Successfully...");
    } else {
        System.out.println("Cancellation Failed...");
    }

    ps.close();
}

}
