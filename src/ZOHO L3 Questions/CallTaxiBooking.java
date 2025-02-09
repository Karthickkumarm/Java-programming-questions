import java.util.*;

class Taxi {
    String id;
    int earnings;
    String currentLocation;
    List<String> travelHistory;
    boolean isOnSharedBooking;
    String sharedBookingDestination;

    public Taxi(String id) {
        this.id = id;
        this.earnings = 0;
        this.currentLocation = "A";
        this.travelHistory = new ArrayList<>();
        this.isOnSharedBooking = false;
        this.sharedBookingDestination = null;
    }

    public void addTrip(String tripDetails) {
        travelHistory.add(tripDetails);
    }

    public void updateEarnings(int amount) {
        earnings += amount;
    }

    public void updateLocation(String location) {
        currentLocation = location;
    }

    public void setSharedBooking(boolean isOnSharedBooking, String sharedBookingDestination) {
        this.isOnSharedBooking = isOnSharedBooking;
        this.sharedBookingDestination = sharedBookingDestination;
    }
}

class Booking {
    static int bookingIdCounter = 1;
    int bookingId;
    String customerId;
    String startPoint;
    String endPoint;
    String startTime;
    String endTime; // endTime is no longer calculated in constructor
    String bookingType;
    int charges;

    public Booking(String customerId, String startPoint, String endPoint, String startTime, String bookingType,
            int charges) {
        this.bookingId = bookingIdCounter++;
        this.customerId = customerId;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startTime = startTime;
        this.bookingType = bookingType;
        this.charges = charges;
        this.endTime = null; // Initialize endTime to null
    }
}

public class CallTaxiBooking {
    static List<Taxi> taxis = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            taxis.add(new Taxi("Taxi" + i));
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Customer ID:");
            String customerId = scanner.next();
            System.out.println("Enter Starting Point (A, B, C, D, E):");
            String startPoint = scanner.next();
            System.out.println("Enter Destination Point (A, B, C, D, E):");
            String endPoint = scanner.next();
            System.out.println("Enter Start Time (e.g., 9:00 AM):");
            String startTime = scanner.next() + " " + scanner.next();

            int distance = Math.abs(endPoint.charAt(0) - startPoint.charAt(0)) * 15;
            int charges = calculateCharges(distance);
            String bookingType = "1";

            if (distance >= 45) {
                System.out.println("Charges For Normal(1) - " + charges + " Share(2) - " + (int) (charges * 0.4));
                System.out.println("Enter Booking Type (1 for Normal, 2 for Share):");
                bookingType = scanner.next();
            } else {
                System.out.println("Charges For Normal(1) - " + charges);
            }

            Booking booking = new Booking(customerId, startPoint, endPoint, startTime, bookingType,
                    bookingType.equals("1") ? charges : (int) (charges * 0.4));
            Taxi allottedTaxi = allotTaxi(booking);

            if (allottedTaxi != null) {
                System.out.println("Booking ID: " + booking.bookingId);
                System.out.println("Allotted Taxi: " + allottedTaxi.id);
            } else {
                System.out.println("No taxi available.");
            }

            System.out.println("Do you want to book another taxi? (yes/no)");
            if (!scanner.next().equalsIgnoreCase("yes")) {
                break;
            }
        }

        for (Taxi taxi : taxis) {
            displayTravelHistory(taxi);
        }
    }

    private static int calculateCharges(int distance) {
        return distance <= 5 ? 50 : 50 + (distance - 5) * 10;
    }

    private static String calculateEndTime(String startTime, int travelTime) { // Corrected
        String[] timeParts = startTime.split(" ");
        int hour = Integer.parseInt(timeParts[0].split(":")[0]);
        int minute = Integer.parseInt(timeParts[0].split(":")[1]);
        String period = timeParts[1];

        minute += travelTime;
        while (minute >= 60) {
            hour++;
            minute -= 60;
        }
        if (hour >= 12) {
            if (hour > 12)
                hour -= 12;
            period = period.equals("AM") ? "PM" : "AM";
        }

        return String.format("%d:%02d %s", hour, minute, period);
    }

    private static Taxi allotTaxi(Booking booking) { // Corrected and final version
        Taxi selectedTaxi = null;

        if (booking.bookingType.equals("2")) { // Shared booking
            for (Taxi taxi : taxis) {
                if (taxi.isOnSharedBooking && taxi.sharedBookingDestination.equals(booking.endPoint)
                     ) {
                    selectedTaxi = taxi;
                    break;
                }
            }
        } else { // Normal booking
            for (Taxi taxi : taxis) {
                if (taxi.currentLocation.equals(booking.startPoint)) {
                    if (selectedTaxi == null || taxi.earnings < selectedTaxi.earnings) {
                        selectedTaxi = taxi;
                    }
                }
            }

            if (selectedTaxi == null) {
                for (Taxi taxi : taxis) {
                    if (selectedTaxi == null || taxi.earnings < selectedTaxi.earnings) {
                        selectedTaxi = taxi;
                    }
                }
            }
        }

        if (selectedTaxi != null) {
            selectedTaxi.updateEarnings(booking.charges);

            int distance = Math.abs(booking.endPoint.charAt(0) - booking.startPoint.charAt(0)) * 15;
            int travelTime = distance;

            if (booking.bookingType.equals("2")) {
                if (!selectedTaxi.isOnSharedBooking) { // Starting a new shared ride
                    selectedTaxi.updateLocation(booking.endPoint);
                    selectedTaxi.setSharedBooking(true, booking.endPoint);
                }
                // If taxi is already on shared ride, location is not updated here.
            } else {
                selectedTaxi.updateLocation(booking.endPoint);
                selectedTaxi.setSharedBooking(false, null); // Reset shared status
            }

            booking.endTime = calculateEndTime(booking.startTime, travelTime); // Corrected End Time Calculation

            selectedTaxi.addTrip(String.format("%d\t%s\t%s\t%s\t%s\t%s\t%d",
                    booking.bookingId, booking.startPoint, booking.endPoint,
                    booking.startTime, booking.endTime, booking.bookingType, booking.charges));

            bookings.add(booking);
        }

        return selectedTaxi;
    }

    private static void displayTravelHistory(Taxi taxi) {
        System.out.println("Travel History of " + taxi.id + ":");
        System.out.println("Booking Id\tStartPoint\tEndPoint\tStart Time\tEnd Time\tBooking Type\tCharges");
        for (String trip : taxi.travelHistory) {
            System.out.println(trip);
        }
    }
}




// private static Taxi allotTaxi(Booking booking) { // Corrected and final version
//     Taxi selectedTaxi = null;

//     if (booking.bookingType.equals("2")) { // Shared booking
//         for (Taxi taxi : taxis) {
//             if (!taxi.isOnSharedBooking && taxi.currentLocation.equals(booking.startPoint)) {
//                 selectedTaxi = taxi;
//                 break;
//             } else if (taxi.isOnSharedBooking && taxi.sharedBookingDestination.equals(booking.endPoint)
//                     && taxi.currentLocation.equals(booking.startPoint) ) {
//                 selectedTaxi = taxi;
//                 break;
//             }
//         }
//     } else { // Normal booking
//         for (Taxi taxi : taxis) {
//             if (taxi.currentLocation.equals(booking.startPoint)) {
//                 if (selectedTaxi == null || taxi.earnings < selectedTaxi.earnings) {
//                     selectedTaxi = taxi;
//                 }
//             }
//         }

//         if (selectedTaxi == null) {
//             for (Taxi taxi : taxis) {
//                 if (selectedTaxi == null || taxi.earnings < selectedTaxi.earnings) {
//                     selectedTaxi = taxi;
//                 }
//             }
//         }
//     }

//     if (selectedTaxi != null) {
//         selectedTaxi.updateEarnings(booking.charges);

//         int distance = Math.abs(booking.endPoint.charAt(0) - booking.startPoint.charAt(0)) * 15;
//         int travelTime = distance;

//         if (booking.bookingType.equals("2")) {
//             if (!selectedTaxi.isOnSharedBooking) { // Starting a new shared ride
//                 selectedTaxi.updateLocation(booking.endPoint);
//                 selectedTaxi.setSharedBooking(true, booking.endPoint);
//             }
//             // If taxi is already on shared ride, location is not updated here.
//         } else {
//             selectedTaxi.updateLocation(booking.endPoint);
//             selectedTaxi.setSharedBooking(false, null); // Reset shared status
//         }

//         booking.endTime = calculateEndTime(booking.startTime, travelTime); // Corrected End Time Calculation

//         selectedTaxi.addTrip(String.format("%d\t%s\t%s\t%s\t%s\t%s\t%d",
//                 booking.bookingId, booking.startPoint, booking.endPoint,
//                 booking.startTime, booking.endTime, booking.bookingType, booking.charges));

//         bookings.add(booking);
//     }

//     return selectedTaxi;
// }

