import java.util.*;
public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RailwayTicketRS railwayTicketRS = new RailwayTicketRS();
        while(true) {

            System.out.println("Railway Ticket Reservation System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Print Booked Tickets");
            System.out.println("4. Print Available Tickets");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()
            System.out.println("-----------------------");

            switch (choice) {
                case 1:
                    railwayTicketRS.bookTicket();
                    break;
                case 2:
                    railwayTicketRS.cancelTicket();
                    break;
                case 3:
                    railwayTicketRS.printBookedTickets();
                    break;
                case 4:
                    //railwayTicketRS.printAvailableTickets();
                    break;
                case 5:
                    System.out.println("Exiting the application. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}


