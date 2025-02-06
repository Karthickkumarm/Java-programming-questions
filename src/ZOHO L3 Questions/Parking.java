import java.util.ArrayList;
import java.util.Scanner;

public class Parking {
    static int slots;
    static int availabaleSlot;
    static ArrayList<Integer> parkingSlot = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the total number of parking slots: ");
        slots = sc.nextInt();
        availabaleSlot = slots;

        while(true){
            System.out.println("Enter your choice: ");
            System.out.println("1.Park a car");
            System.out.println("2.Remove a car");
            System.out.println("3.View parked car");
            System.out.println("4.Exit");

            int input = sc.nextInt();
            switch (input){
                case 1:
                    parkCar();
                    break;
                case 2:
                    removeCar();
                    break;
                case 3:
                    viewParkedCars();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Enter a valid input");
            }
        }
    }

    private static void parkCar() {
        Scanner sc = new Scanner(System.in);
        // if parking lot is full already
        if(availabaleSlot == 0){
            System.out.println("The parking lot is already full");
        }
        System.out.println("Enter the license plate number of car: ");
        int license_no = sc.nextInt();
        parkingSlot.add(license_no);
        System.out.print("Car parked Successfully. ");
        availabaleSlot--;
        System.out.println("Available slots: "+availabaleSlot);
    }

    private static void removeCar() {
        Scanner sc = new Scanner(System.in);
        if(availabaleSlot == slots){
            System.out.println("There is no cars parked yet. So no cars can be cancelled");
            return;
        }
        System.out.println("Enter the license plate number of the car to be removed: ");
        int license_num = sc.nextInt();
        for(int i=0;i<parkingSlot.size();i++){
            if(license_num == parkingSlot.get(i)){
                parkingSlot.remove(Integer.valueOf(license_num));
                System.out.print("Car removed Successfully. ");
                availabaleSlot++;
                System.out.println("Available slots: "+availabaleSlot);
                break;
            }
            System.out.println("The car is not parked here");
        }
    }

    private static void viewParkedCars() {
        if(parkingSlot.isEmpty()){
            System.out.println("There are no parked cars");
        }
        System.out.println("Parked cars");
        for(int i=0;i<parkingSlot.size();i++){
            System.out.println(parkingSlot.get(i));
        }
    }
}