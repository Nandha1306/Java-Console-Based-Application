import java.text.ParseException;
import java.util.*;

public class Main{
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        ArrayList<Bus> buses = new ArrayList<>();

        buses.add(new Bus(1, true, 2));
        buses.add(new Bus(2, true, 3));
        buses.add(new Bus(3, true, 4));

        ArrayList<Booking> bookings = new ArrayList<>();

        int userOpt = 1;
        while(userOpt == 1){
            System.out.println("Enter 1 to book and 0 to exit:");
            userOpt = sc.nextInt();
            if(userOpt == 1){
                Booking booking = new Booking();
                if(booking.isAvailable(bookings, buses)){
                    bookings.add(booking);
                    System.out.println("Your booking is confirmed");
                }else{
                    System.out.println("Try another date or another bus..");
                }
            }else{
                System.out.println("Thanks for visiting");
            }
        }
    }
}