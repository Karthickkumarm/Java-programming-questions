
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodDelivery {

    public static class DeliveryExecutive {
        String id;
        int deliveryCharges = 0;
        int allowance = 0; // Start with 0
        int tripCount = 0; // Start with 0 trips
        List<Booking> bookings = new ArrayList<>();

        public DeliveryExecutive(String id) {
            this.id = id;
        }

        public void addBooking(Booking booking, int deliveryCharge) {
            this.bookings.add(booking);
            this.deliveryCharges += deliveryCharge;
        }

        public void addAllowance() {
        
                this.allowance += 10; // Add allowance of Rs. 10 for the trip
            
        }

        public void incrementTripCount() {
            this.tripCount++; // Increment the trip count
        }

        public List<Booking> getBookings() {
            return bookings;
        }

        public String getId() {
            return id;
        }

        public int getTotalEarned() {
            return this.deliveryCharges + this.allowance;
        }

        @Override
        public String toString() {
            return id + " - Earned: " + getTotalEarned() + " (Delivery: " + deliveryCharges + ", Allowance: " + allowance + ", Trips: " + tripCount + ")";
        }
    }

    public static class Booking {
        int customerId;
        String restaurant;
        String destination;
        String time;
    
        public Booking(int customerId, String restaurant, String destination, String time) {
            this.customerId = customerId;
            this.restaurant = restaurant;
            this.destination = destination;
            this.time = time;
        }
    
        public int getCustomerId() {
            return customerId;
        }
    
        public String getRestaurant() {
            return restaurant;
        }
    
        public String getDestination() {
            return destination;
        }
    
        public String getTime() {
            return time;
        }
    
        @Override
        public String toString() {
            return "Booking [Customer ID=" + customerId + ", Restaurant=" + restaurant + ", Destination=" + destination + ", Time=" + time + "]";
        }
    }

    static Scanner scanner = new Scanner(System.in);
    static DeliveryExecutive[] executives = new DeliveryExecutive[5];
    static List<Booking> bookingHistory = new ArrayList<>();

    // Initialize 2 delivery executives
    static {
        for (int i = 0; i < 5; i++) {
            executives[i] = new DeliveryExecutive("DE" + (i + 1));
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Enter New Booking");
            System.out.println("2. Show Delivery History");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1, 2, or 3): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    handleNewBooking();
                    break;
                case 2:
                    showDeliveryHistory();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void handleNewBooking() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Restaurant: ");
        String restaurant = scanner.nextLine();

        System.out.print("Destination: ");
        String destination = scanner.nextLine();

        System.out.print("Time (e.g., 9:00 AM): ");
        String time = scanner.nextLine();

        Booking newBooking = new Booking(customerId, restaurant, destination, time);
        bookingHistory.add(newBooking);

        DeliveryExecutive assignedExecutive = assignDeliveryExecutive(newBooking);

        System.out.println("Handling booking: " + newBooking);
        System.out.println("Assigned Delivery Executive: " + assignedExecutive.getId());
    }

    private static DeliveryExecutive assignDeliveryExecutive(Booking newBooking) {
        // Check if the same restaurant, destination, and within 15 minutes of another booking
        for (DeliveryExecutive executive : executives) {
            for (Booking booking : executive.getBookings()) {
                // String r=booking.restaurant == newBooking.restaurant ? "t" : "e";
                if (booking.getRestaurant().equals(newBooking.getRestaurant()) &&
                    booking.getDestination().equals(newBooking.getDestination()) &&
                    isWithin15Minutes(booking.getTime(), newBooking.getTime())) {

                    // If it matches, add only 5 to the delivery charge
                    executive.addBooking(newBooking, 5); // Update delivery charge to 5 for repeat bookings
                    executive.incrementTripCount();
                    return executive; // Return the matched executive
                }
            }
        }

        // If no match, assign the next available executive
        for (DeliveryExecutive executive : executives) {
            if (executive.getBookings().size() == 0) {
                executive.addBooking(newBooking, 50); // Assign new booking charge
                executive.addAllowance(); // Add allowance for the first trip
                executive.incrementTripCount(); // Increment trip count
                return executive;
            }
        }

        // If all executives have been assigned, assign based on the fewest bookings
        DeliveryExecutive leastBusyExecutive = executives[0];
        for (DeliveryExecutive executive : executives) {
            if (executive.getBookings().size() < leastBusyExecutive.getBookings().size()) {
                leastBusyExecutive = executive;
            }
        }
        leastBusyExecutive.addBooking(newBooking, 50); // Assign new booking charge
        leastBusyExecutive.addAllowance(); // Add allowance for the first trip
        leastBusyExecutive.incrementTripCount(); // Increment trip count
        return leastBusyExecutive;
    }

    private static boolean isWithin15Minutes(String time1, String time2) {
        // Parsing time and accounting for AM/PM
        int totalMinutes1 = convertToMinutes(time1);
        int totalMinutes2 = convertToMinutes(time2);

        int diffInMinutes = Math.abs(totalMinutes1 - totalMinutes2);
        return diffInMinutes <= 15;
    }

    private static int convertToMinutes(String time) {
        String[] parts = time.split(" ");
        String[] hourMinute = parts[0].split(":");
        int hours = Integer.parseInt(hourMinute[0]);
        int minutes = Integer.parseInt(hourMinute[1]);

        // Convert to 24-hour format
        if (parts.length > 1 && parts[1].equalsIgnoreCase("PM") && hours < 12) {
            hours += 12;
        } else if (parts.length > 1 && parts[1].equalsIgnoreCase("AM") && hours == 12) {
            hours = 0;
        }

        return hours * 60 + minutes; // Convert to total minutes
    }

    private static void showDeliveryHistory() {
        System.out.println("\nExecutive Summary:");
        for (DeliveryExecutive executive : executives) {
            System.out.println(executive);
        }
    }
}
