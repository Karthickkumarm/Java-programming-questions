
import java.util.*;
public class RailwayTicketRS {
    int totalBerths=3;
    int lowerBerths = 1;
    int middleBerths =1;
    int upperBerths = 1;
    int totalRACBerths = 1;
    int totalWaitingListTickets = 1;
    ArrayList<Passenger> bookedTickets = new ArrayList<>();
    ArrayList<Passenger> racTickets = new ArrayList<>();
    ArrayList<Passenger> waitingListTickets = new ArrayList<>();

    void bookTicket(){
        Scanner in=new Scanner(System.in);
        if(totalWaitingListTickets==0){
            return;
        }
        System.out.print("Enter passenger name: ");
        String name = in.nextLine();
        System.out.print("Enter passenger age: ");
        int age = in.nextInt();
        in.nextLine(); // Consume the newline left by nextInt()
        System.out.print("Enter passenger gender (M/F): ");
        String gender = in.nextLine().toUpperCase();
        System.out.print("Enter berth preference (Lower/Middle/Upper): ");
        String berthPreference = in.nextLine().toLowerCase();
        if (age < 5) {
            System.out.println("Children below 5 years are not allowed to book tickets.");
            return;
        }

        if(berthPreference.equals("lower")) {
            if (lowerBerths > 0) {
                lowerBerths--;
            } else {
                System.out.println("---------------------");
                System.out.println("Lower berth not available. Suggesting available berths:");
                suggestAvailableBerths(name, age, gender);
                return;
            }
        } else if (berthPreference.equals("middle")) {
            if (middleBerths > 0) {
                middleBerths--;
            } else {
                System.out.println("---------------------");
                System.out.println("Middle berth not available. Suggesting available berths:");
                suggestAvailableBerths(name, age, gender);
                return;
            }
        } else if (berthPreference.equals("upper")) {
            if (upperBerths > 0) {
                upperBerths--;
            } else {
                System.out.println("---------------------");
                System.out.println("Upper berth not available. Suggesting available berths:");
                suggestAvailableBerths(name, age, gender);
                return;
            }
        }
        else
        {
            System.out.println("invalid input");
            return;
        }
        if (age >5)
        {
            Passenger passenger = new Passenger(name, age, gender, berthPreference);

            if (totalBerths > 0 ) {
                bookedTickets.add(passenger);
                totalBerths--;
                System.out.println("Ticket booked successfully!");
                System.out.println("----------------------------");
            } else if (totalRACBerths > 0) {
                racTickets.add(passenger);
                totalRACBerths--;
                System.out.println("Ticket booked successfully! (RAC)");
                System.out.println("---------------------------------");
            } else {
                waitingListTickets.add(passenger);
                totalWaitingListTickets--;
                System.out.println("Ticket booked successfully! (Waiting List)");
                System.out.println("------------------------------------------");
            }
        }
    }
    public void printBookedTickets() {
        if (bookedTickets.isEmpty()) {
            System.out.println("No booked tickets available.");
            System.out.println("----------------------------");
        } else {
            System.out.println("Booked Tickets:");
            System.out.println("---------------");
            for (int i = 0; i < bookedTickets.size(); i++) {
                Passenger ticket = bookedTickets.get(i);
                System.out.println((i + 1) + ". Name: " + ticket.name + "\n   Age: " + ticket.age +
                        "\n   Gender: " + ticket.gender + "\n   Berth Preference: " + ticket.berthPreference);
            }
            System.out.println("----------------------");
            if(!racTickets.isEmpty()) {
                System.out.println("RAC Tickets:");
                System.out.println("----------------------");
                for (int i = 0; i < racTickets.size(); i++) {
                    Passenger ticket = racTickets.get(i);
                    System.out.println((i + 1) + ". Name: " + ticket.name + "\n   Age: " + ticket.age +
                            "\n   Gender: " + ticket.gender + "\n   Berth Preference: " + ticket.berthPreference);
                }
                System.out.println("----------------------");
            }
            if(!waitingListTickets.isEmpty()) {
                System.out.println("Waiting List Tickets:");
                System.out.println("----------------------");
                for (int i = 0; i < waitingListTickets.size(); i++) {
                    Passenger ticket = waitingListTickets.get(i);
                    System.out.println((i + 1) + ". Name: " + ticket.name + "\n   Age: " + ticket.age +
                            "\n   Gender: " + ticket.gender + "\n   Berth Preference: " + ticket.berthPreference);
                }
                System.out.println("----------------------");
            }
            System.out.println("Total Booked Tickets: " + bookedTickets.size());
            System.out.println("Total RAC_Booked Tickets: " + racTickets.size());
            System.out.println("Total Waiting_List Tickets: " + waitingListTickets.size());
            System.out.println("----------------------------");
        }
    }
    void suggestAvailableBerths(String name, int age, String gender) {
        System.out.println("------------------------------------------");
        if (lowerBerths > 0) {
            System.out.println("1. Lower");
        }
        if (middleBerths > 0) {
            System.out.println("2. Middle");
        }
        if (upperBerths > 0) {
            System.out.println("3. Upper");
        }

        if(lowerBerths==0 && middleBerths==0 && upperBerths==0)
        {
            System.out.println("4.Book on RAC");
        }


        System.out.print("Enter your choice (1-4): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        switch (choice) {
            case 1:
                lowerBerths--;
                bookedTickets.add(new Passenger(name, age, gender, "lower"));
                totalBerths--;
                System.out.println("Ticket booked successfully!");
                System.out.println("---------------------------------");
                break;
            case 2:
                middleBerths--;
                bookedTickets.add(new Passenger(name, age, gender, "middle"));
                totalBerths--;
                System.out.println("Ticket booked successfully!");
                System.out.println("---------------------------------");
                break;
            case 3:
                upperBerths--;
                bookedTickets.add(new Passenger(name, age, gender, "upper"));
                totalBerths--;
                System.out.println("Ticket booked successfully!");
                System.out.println("---------------------------------");
                break;

            case 4:
                if (totalRACBerths > 0) {
                    racTickets.add(new Passenger(name, age, gender, "side lower"));
                    totalRACBerths--;
                    System.out.println("Ticket booked successfully! (RAC)");
                    System.out.println("---------------------------------");
                    break;
                }else
                {
                    System.out.println("----------------------------");
                    System.out.println("RAC NOT Available,you can book on Waiting List");
                    System.out.println("Say Yes to book on Waiting_List");
                    String reply=scanner.next().toLowerCase();
                    if(reply.equals("yes"))
                    {
                        waitingListTickets.add(new Passenger(name, age, gender, "NA"));
                        totalWaitingListTickets--;
                        System.out.println("Ticket booked successfully! (Waiting List)");
                        System.out.println("------------------------------------------");
                    }
                    break;
                }





            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public void cancelTicket() {

        Scanner scanner = new Scanner(System.in);

        if (bookedTickets.isEmpty()) {
            System.out.println("No booked tickets to cancel.");
            return;
        }

        System.out.print("Enter passenger name to cancel the ticket: ");
        String canceledPassengerName = scanner.nextLine();

        Passenger canceledTicket = null;
        for (Passenger ticket : bookedTickets) {
            if (ticket.name.equalsIgnoreCase(canceledPassengerName)) {
                canceledTicket = ticket;
                break;
            }

        }
        if (canceledTicket == null) {
            System.out.println("Ticket with the provided name not found.");
            return;
        }


        bookedTickets.remove(canceledTicket);
        totalBerths++;

        if (!racTickets.isEmpty()) {
            Passenger confirmedTicket = racTickets.remove(0);
            totalRACBerths++;
            bookedTickets.add(new Passenger(confirmedTicket.name, confirmedTicket.age, confirmedTicket.gender, canceledTicket.berthPreference));
            System.out.println("Ticket canceled successfully for " + canceledTicket.name );
            totalBerths--;
        } else {
            System.out.println("Ticket canceled successfully for " + canceledTicket.name + ". No RAC passenger available to move.");
        }

        // Check and move a passenger from waiting list to RAC
        if (!waitingListTickets.isEmpty()) {
            Passenger racCandidate = waitingListTickets.remove(0);
            racTickets.add(new Passenger(racCandidate.name, racCandidate.age, racCandidate.gender,"side lower"));
            System.out.println("Waiting list passenger moved to RAC.");
            totalRACBerths--;
            totalWaitingListTickets++;
        }

        System.out.println("-----------------------------------------");
    }


}
