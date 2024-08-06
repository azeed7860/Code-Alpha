package CodeAlpha;

import java.util.HashMap;
import java.util.Scanner;

public class HotelReservationSystem {
    static HashMap<Integer,Integer> priceMap = new HashMap<>();
    static HashMap<Integer,String> categoryMap = new HashMap<>();
    static boolean[] standardRooms = {
            true, false, true, true, false, false, true, false, true, true,
            false, true, false, true, true, false, true, false, false, true,
            false, true, false, true, true, false, false, false, true, false
    };
    static boolean[] deluxeRooms = {
            true, false, true, false, true, false, false, true, true, false,
            false, true, true, true, false, false, true, false, true, true
    };
    static boolean[] luxuryRooms = {
            true, false, true, false, true, false, true, true, false, true
    };

    public static void main(String[] args) {
        priceMap.put(1, 2000);
        priceMap.put(2, 5000);
        priceMap.put(3, 10000);
        categoryMap.put(1, "STANDARDROOM");
        categoryMap.put(2, "DELUXEROOM");
        categoryMap.put(3, "LUXURYROOM");

        Scanner sc = new Scanner(System.in);
        Room obj = new Room();

        // User Interaction
        int category = obj.selectCategory(sc, priceMap,categoryMap);
        sc.nextLine();
        System.out.print("Enter name of the person: ");
        String name = sc.nextLine();

        // Reservation
        int reserveDetails;
        if (category == 1) {
            reserveDetails = obj.reservation(standardRooms, category, name);
        } else if (category == 2) {
            reserveDetails = obj.reservation(deluxeRooms, category, name);
        } else {
            reserveDetails = obj.reservation(luxuryRooms, category, name);
        }

        // Display Booking Details
        obj.bookingDetails(category, name, reserveDetails);

        // Payment Processing
        System.out.println("Make sure to complete payment for reservation of the room");
        obj.paymentDetails(category);

        // Final Confirmation
        System.out.println("Reservation completed under the name: " + name);
        System.out.println("Category: " + categoryMap.get(category));
        System.out.println("Room Number: " + reserveDetails);
    }
}

class Room {
    String name;
    int category;

    public int reservation(boolean[] arr, int category, String name) {
        this.name = name;
        this.category = category;
        System.out.println("Available rooms:");

        // Display available rooms
        int roomNumber = -1;
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i]) {
                System.out.print((i + 1) + " ");
                roomNumber = i + 1;
            }
        }

        if (roomNumber != -1) {
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter the room number: ");
            int choice = sc.nextInt();
            if (choice > 0 && choice <= arr.length && !arr[choice - 1]) {
                arr[choice - 1] = true;
                return choice;
            } else {
                System.out.println("Invalid room number. Please try again.");
                return reservation(arr, category, name); // Retry reservation
            }
        } else {
            System.out.println("No rooms available in this category.");
            return 0;
        }
    }

    public void bookingDetails(int category, String name, int room) {
        System.out.println("\nBooking Details:");
        System.out.println("Name: " + name);
        System.out.println("Category of Room: " + category);
        System.out.println("Room Number: " + room);
    }

    public void paymentDetails(int category) {
        System.out.println("\nPayment Processing...");
        try {
            Thread.sleep(3000); // Simulating payment processing time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Payment Successful.");
    }

    public int selectCategory(Scanner sc, HashMap<Integer,Integer> map,HashMap<Integer,String> categoryMap) {
        System.out.println("\nCATEGORIES:");
        for (int i = 1; i <= categoryMap.size(); i++) {
            System.out.println(i + ". " + categoryMap.get(i) + " - Cost per day: " + map.get(i));
        }
        System.out.print("Enter the category of room: ");
        int choice = sc.nextInt();
        if (choice < 1 || choice > categoryMap.size()) {
            System.out.println("Invalid category. Please select again.");
            return selectCategory(sc, map,categoryMap);
        }
        return choice;
    }
}