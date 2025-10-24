
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Booking {
    private String passengerName;
    private int busNo;
    private Date date;

    public Booking() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Passenger Name: ");
        this.passengerName = sc.nextLine();
        System.out.println("Enter Bus No: ");
        this.busNo = sc.nextInt();
        System.out.println("Enter Date dd-mm-yyyy: ");
        String inputDate = sc.next();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try{
            date = simpleDateFormat.parse(inputDate);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    public boolean isAvailable(ArrayList<Booking> bookings, ArrayList<Bus> buses){
        int capacity = 0;
        for(Bus bus : buses){
            if(bus.getBusNo() == busNo){
                capacity = bus.getCapacity();
            }
        }

        int booked = 0;
        for(Booking booking : bookings){
            if(booking.busNo == busNo && booking.date.equals(date)){
                booked++;
            }
        }

        return booked < capacity;
    }
}
