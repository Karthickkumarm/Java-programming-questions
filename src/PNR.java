import java.util.*;

class PNR {
    static final int MAX_SEATS = 8;
    static final int MAX_WAITING = 2;
    static int pnrCounter = 1;

    static class Ticket {
        int pnr;
        String source;
        String destination;
        List<String> seats = new ArrayList<>();
        String status;

        Ticket(int pnr, String source, String destination, String status) {
            this.pnr = pnr;
            this.source = source;
            this.destination = destination;
            this.status = status;
        }

        void addSeats(List<String> seatNumbers) {
            seats.addAll(seatNumbers);
        }

        @Override
        public String toString() {
            return "PNR " + pnr + ", " + source + " to " + destination +
                    ". Seat Nos: " + (seats.isEmpty() ? "-" : String.join(", ", seats)) +
                    " Status: " + status;
        }
    }

    static class Train {
        Map<String, List<Integer>> seatMap = new HashMap<>();
        Queue<Ticket> waitingList = new LinkedList<>();
        List<Ticket> tickets = new ArrayList<>();

        public Train() {
            // Initialize seat map for routes
            for (char start = 'A'; start < 'E'; start++) {
                for (char end = (char) (start + 1); end <= 'E'; end++) {
                    String route = "" + start + end;
                    seatMap.put(route, new ArrayList<>());
                }
            }
        }

        public void book(String source, String destination, int numSeats) {
            String route = source + destination;
            List<Integer> availableSeats = getAvailableSeats(source, destination);

            if (availableSeats.size() >= numSeats) {
                List<String> allocatedSeats = new ArrayList<>();
                for (int i = 0; i < numSeats; i++) {
                    allocatedSeats.add("S" + availableSeats.get(i));
                    allocateSeatToRoute(availableSeats.get(i), source, destination);
                }
                Ticket ticket = new Ticket(pnrCounter++, source, destination, "Confirmed");
                ticket.addSeats(allocatedSeats);
                tickets.add(ticket);
                System.out.println(ticket);
            } else if (waitingList.size() < MAX_WAITING) {
                List<String> waitingNumbers = new ArrayList<>();
                for (int i = 1; i <= numSeats; i++) {
                    waitingNumbers.add("WL" + (waitingList.size() + i));
                }
                Ticket ticket = new Ticket(pnrCounter++, source, destination, "Waiting");
                ticket.addSeats(waitingNumbers);
                waitingList.add(ticket);
                System.out.println(ticket);
            } else {
                System.out.println("No seats available");
            }
        }

        public void cancel(int pnr, int numSeats) {
            Ticket ticket = findTicketByPNR(pnr);
            if (ticket == null) {
                System.out.println("Invalid PNR");
                return;
            }

            List<String> canceledSeats = new ArrayList<>();
            for (int i = 0; i < numSeats && !ticket.seats.isEmpty(); i++) {
                String seat = ticket.seats.remove(0);
                canceledSeats.add(seat);
                if (seat.startsWith("S")) {
                    freeSeatFromRoute(Integer.parseInt(seat.substring(1)), ticket.source, ticket.destination);
                }
            }

            System.out.println("PNR " + ticket.pnr + ", " + ticket.source + " to " + ticket.destination +
                    ". Canceled Seats: " + String.join(", ", canceledSeats));

            if (ticket.seats.isEmpty()) {
                tickets.remove(ticket);
            }

            processWaitingList();
        }

        public void chart() {
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        }

        private Ticket findTicketByPNR(int pnr) {
            for (Ticket ticket : tickets) {
                if (ticket.pnr == pnr) {
                    return ticket;
                }
            }
            return null;
        }

        private List<Integer> getAvailableSeats(String source, String destination) {
            Set<Integer> unavailableSeats = new HashSet<>();
            for (Map.Entry<String, List<Integer>> entry : seatMap.entrySet()) {
                String[] stations = entry.getKey().split("");
                if (stations[0].compareTo(destination) < 0 && stations[1].compareTo(source) > 0) {
                    unavailableSeats.addAll(entry.getValue());
                }
            }

            List<Integer> availableSeats = new ArrayList<>();
            for (int i = 1; i <= MAX_SEATS; i++) {
                if (!unavailableSeats.contains(i)) {
                    availableSeats.add(i);
                }
            }
            return availableSeats;
        }

        private void allocateSeatToRoute(int seat, String source, String destination) {
            for (char start = source.charAt(0); start < destination.charAt(0); start++) {
                String route = "" + start + (char) (start + 1);
                seatMap.get(route).add(seat);
            }
        }

        private void freeSeatFromRoute(int seat, String source, String destination) {
            for (char start = source.charAt(0); start < destination.charAt(0); start++) {
                String route = "" + start + (char) (start + 1);
                seatMap.get(route).remove((Integer) seat);
            }
        }

        private void processWaitingList() {
            Iterator<Ticket> iterator = waitingList.iterator();
            while (iterator.hasNext()) {
                Ticket waitingTicket = iterator.next();
                List<Integer> availableSeats = getAvailableSeats(waitingTicket.source, waitingTicket.destination);
                if (availableSeats.size() >= waitingTicket.seats.size()) {
                    List<String> allocatedSeats = new ArrayList<>();
                    for (int i = 0; i < waitingTicket.seats.size(); i++) {
                        allocatedSeats.add("S" + availableSeats.get(i));
                        allocateSeatToRoute(availableSeats.get(i), waitingTicket.source, waitingTicket.destination);
                    }
                    waitingTicket.status = "Confirmed";
                    waitingTicket.seats = allocatedSeats;
                    tickets.add(waitingTicket);
                    iterator.remove();
                    System.out.println("Waiting ticket PNR " + waitingTicket.pnr + " confirmed");
                }
            }
        }
    }

    public static void main(String[] args) {
        Train train = new Train();
        train.book("A", "E", 8);
        train.book("A", "E", 2);
        train.cancel(1, 5);
       // train.chart();
        train.cancel(1, 3);
        train.cancel(2, 2);
        train.book("A", "C", 8);
        train.book("C", "E", 8);
        train.book("A", "E", 2);
        //train.chart();
        train.book("C", "D", 2);
        train.cancel(3, 8);
        //train.chart();
        train.cancel(4, 8);
        //train.chart();
        train.book("C", "E", 3);
    }
}
