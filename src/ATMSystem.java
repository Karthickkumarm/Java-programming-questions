import java.io.*;
import java.util.*;

public class ATMSystem {

    static Map<Integer, Integer> atmCash = new HashMap<>(); // Denomination, Count
    static Map<Integer, Customer> customers = new HashMap<>();

    static class Customer {
        int accountNumber;
        String name;
        int pin;
        int balance;

        public Customer(int accountNumber, String name, int pin, int balance) {
            this.accountNumber = accountNumber;
            this.name = name;
            this.pin = pin;
            this.balance = balance;
        }
    }

    public static void main(String[] args) throws IOException {
        loadInitialData();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Load Cash to ATM");
            System.out.println("2. Show Customer Details");
            System.out.println("3. Show ATM Operations");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    loadCashToATM(scanner);
                    break;
                case 2:
                    showCustomerDetails();
                    break;
                case 3:
                    handleATMProcess(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    //saveDataToFile();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void loadInitialData() {
        // Initialize ATM cash
        atmCash.put(2000, 0);
        atmCash.put(500, 0);
        atmCash.put(100, 0);

        // Initialize Customers
        customers.put(101, new Customer(101, "Suresh", 2343, 25234));
        customers.put(102, new Customer(102, "Ganesh", 5432, 34123));
        customers.put(103, new Customer(103, "Magesh", 7854, 26100));
        customers.put(104, new Customer(104, "Naresh", 2345, 80000));
        customers.put(105, new Customer(105, "Harish", 1907, 103400));
    }

    private static void loadCashToATM(Scanner scanner) {
        System.out.println("Enter the number of 2000₹ notes:");
        int twoThousands = scanner.nextInt();
        System.out.println("Enter the number of 500₹ notes:");
        int fiveHundreds = scanner.nextInt();
        System.out.println("Enter the number of 100₹ notes:");
        int hundreds = scanner.nextInt();

        atmCash.put(2000, atmCash.get(2000) + twoThousands);
        atmCash.put(500, atmCash.get(500) + fiveHundreds);
        atmCash.put(100, atmCash.get(100) + hundreds);

        displayATMBalance();
    }

    private static void displayATMBalance() {
        System.out.println("ATM Balance:");
        int total = 0;
        System.out.printf("%-10s%-10s%-10s\n", "Denomination", "Count", "Value");
        for (Map.Entry<Integer, Integer> entry : atmCash.entrySet()) {
            int value = entry.getKey() * entry.getValue();
            total += value;
            System.out.printf("%-10d%-10d%-10d\n", entry.getKey(), entry.getValue(), value);
        }
        System.out.println("Total Amount: " + total + "₹");
    }

    private static void showCustomerDetails() {
        System.out.println("Customer Details:");
        System.out.printf("%-10s%-20s%-10s%-15s\n", "Acc No", "Account Holder", "Pin", "Balance");
        for (Customer customer : customers.values()) {
            System.out.printf("%-10d%-20s%-10d%-15d\n", customer.accountNumber, customer.name, customer.pin, customer.balance);
        }
    }

    private static void handleATMProcess(Scanner scanner) {
        System.out.println("Enter Account Number:");
        int accountNumber = scanner.nextInt();
        System.out.println("Enter PIN:");
        int pin = scanner.nextInt();

        Customer customer = customers.get(accountNumber);
        if (customer == null || customer.pin != pin) {
            System.out.println("Invalid credentials.");
            return;
        }

        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Transfer Money");
        System.out.println("4. Check ATM Balance");
        System.out.println("5. Mini Statement");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Balance: " + customer.balance + "₹");
                break;
            case 2:
                withdrawMoney(scanner, customer);
                break;
            case 3:
                transferMoney(scanner, customer);
                break;
            case 4:
                displayATMBalance();
                break;
            case 5:
                showMiniStatement(customer.accountNumber);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void withdrawMoney(Scanner scanner, Customer customer) {
        System.out.println("Enter amount to withdraw:");
        int amount = scanner.nextInt();

        if (amount < 100 || amount > 10000 || amount > customer.balance) {
            System.out.println("Invalid amount or insufficient balance.");
            return;
        }

        int remaining = amount;
        Map<Integer, Integer> withdrawn = new LinkedHashMap<>();

        for (int denom : atmCash.keySet()) {
            int notes = Math.min(remaining / denom, atmCash.get(denom));
            withdrawn.put(denom, notes);
            remaining -= notes * denom;
        }

        if (remaining > 0) {
            System.out.println("ATM cannot dispense the requested amount.");
            return;
        }

        for (Map.Entry<Integer, Integer> entry : withdrawn.entrySet()) {
            atmCash.put(entry.getKey(), atmCash.get(entry.getKey()) - entry.getValue());
        }

        customer.balance -= amount;
        System.out.println("Withdrawn successfully. Remaining balance: " + customer.balance + "₹");
        logTransaction(customer.accountNumber, "Withdrawn", amount, customer.balance);
    }

    private static void transferMoney(Scanner scanner, Customer sender) {
        System.out.println("Enter recipient account number:");
        int recipientAcc = scanner.nextInt();
        Customer recipient = customers.get(recipientAcc);

        if (recipient == null || recipient.accountNumber == sender.accountNumber) {
            System.out.println("Invalid recipient.");
            return;
        }

        System.out.println("Enter amount to transfer:");
        int amount = scanner.nextInt();

        if (amount < 1000 || amount > 10000 || sender.balance < amount) {
            System.out.println("Invalid amount or insufficient balance.");
            return;
        }

        sender.balance -= amount;
        recipient.balance += amount;

        System.out.println("Transfer successful. Remaining balance: " + sender.balance + "₹");
        logTransaction(sender.accountNumber, "Transfer to " + recipientAcc, amount, sender.balance);
        logTransaction(recipient.accountNumber, "Transfer from " + sender.accountNumber, amount, recipient.balance);
    }

    private static void showMiniStatement(int accountNumber) {
        String fileName = accountNumber + "_transactions.txt";
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("No transactions available.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Last 10 Transactions:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading transaction file.");
        }
    }

    private static void logTransaction(int accountNumber, String description, int amount, int closingBalance) {
        String fileName = accountNumber + "_transactions.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(description + " - " + amount + "₹ - Closing Balance: " + closingBalance + "₹\n");
        } catch (IOException e) {
            System.out.println("Error logging transaction.");
        }
    }

    private static void saveDataToFile() {
        try (ObjectOutputStream atmFile = new ObjectOutputStream(new FileOutputStream("atm_data.dat"));
             ObjectOutputStream customerFile = new ObjectOutputStream(new FileOutputStream("customer_data.dat"))) {

            atmFile.writeObject(atmCash);
            customerFile.writeObject(customers);

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }
}
