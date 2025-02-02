import java.util.*;

class Passenger {
    private String name;
    private int age;
    private char destination;

    public Passenger(String name, int age, char destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getDestination() {
        return destination;
    }
}

class Hyperloop {
    private List<Passenger> passengers = new ArrayList<>();
    private PriorityQueue<Pair> passengerQueue = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey).reversed());
    private Map<Character, List<Character>> stations = new HashMap<>();
    private char startingStation;
    public static int noOfPassengers = 0;
    public static int noOfInterConnections = 0;

    private void BFS(char destination) {
        Queue<Character> queue = new LinkedList<>();
        Map<Character, Character> prev = new HashMap<>();
        Set<Character> visited = new HashSet<>();
        queue.add(startingStation);

        while (!queue.isEmpty()) {
            char node = queue.poll();
            for (char neighbor : stations.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    prev.put(neighbor, node);
                }
            }
        }

        List<Character> result = new ArrayList<>();
        result.add(destination);
        while (prev.containsKey(destination)) {
            destination = prev.get(destination);
            result.add(destination);
        }
        Collections.reverse(result);
        
        if (result.get(0) == startingStation) {
            for (char station : result) {
                System.out.print(station + " ");
            }
            System.out.println();
        } else {
            System.out.println("No route present to the destination");
        }
    }

    public void init(Scanner scanner) {
        noOfInterConnections = scanner.nextInt();
        startingStation = scanner.next().charAt(0);
        
        for (int i = 0; i < noOfInterConnections; i++) {
            char u = scanner.next().charAt(0);
            char v = scanner.next().charAt(0);
            stations.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            stations.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
    }

    public void addPassenger(Scanner scanner) {
        int n = scanner.nextInt();
        while (n-- > 0) {
            String name = scanner.next();
            int age = scanner.nextInt();
            char destination = scanner.next().charAt(0);

            Passenger passenger = new Passenger(name, age, destination);
            passengers.add(passenger);
            passengerQueue.add(new Pair(age, noOfPassengers));
            noOfPassengers++;
        }
    }

    public void startPod(Scanner scanner) {
        int n = scanner.nextInt();
        while (n-- > 0 && !passengerQueue.isEmpty()) {
            Pair top = passengerQueue.poll();
            System.out.print(passengers.get(top.getValue()).getName() + " ");
            BFS(passengers.get(top.getValue()).getDestination());
        }
    }

    public void printQueue() {
        System.out.println(passengerQueue.size());
        PriorityQueue<Pair> passengerQueueCopy = new PriorityQueue<>(passengerQueue);
        while (!passengerQueueCopy.isEmpty()) {
            Pair top = passengerQueueCopy.poll();
            System.out.println(passengers.get(top.getValue()).getName() + " " + passengers.get(top.getValue()).getAge());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hyperloop bookingManagement = new Hyperloop();
        
        while (scanner.hasNext()) {
            String input = scanner.next();
            if (input.equals("INIT")) {
                bookingManagement.init(scanner);
            } else if (input.equals("ADD_PASSENGER")) {
                bookingManagement.addPassenger(scanner);
                bookingManagement.printQueue();
            } else if (input.equals("START_POD")) {
                bookingManagement.startPod(scanner);
            } else if (input.equals("PRINT_Q")) {
                bookingManagement.printQueue();
            }
        }
        scanner.close();
    }
}

class Pair {
    private int key;
    private int value;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}
