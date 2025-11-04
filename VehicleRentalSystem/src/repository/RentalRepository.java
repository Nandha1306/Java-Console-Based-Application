package repository;

import entity.Rental;
import entity.Vehicle;

import javax.crypto.spec.PSource;
import java.util.Date;
import java.util.*;

// !!! Repo should only contain add, fetch, removal. It do not contains any bussiness logic !!!

//don't extend vehicleRepo!! because we just only want the list of vehicle form vehicle repo.
//it is "has-a" relationship - "composition"
//Inheritance "is-a" relationship.
public class RentalRepository{
    //private static Scanner sc = new Scanner(System.in);
    //composition
//    private VehicleRepository vehicleRepository = new VehicleRepository();

    private VehicleRepository vehicleRepository;

    public RentalRepository(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    private HashSet<Rental> activeRentals = new HashSet(); //contains only active rentals.
    private HashSet<Rental> rentalHistory = new HashSet(); //contains history of rentals.

    public HashSet<Rental> getAllRentals(){
        return activeRentals;
    }

    public HashSet<Rental> getAllRentalHistory(){
        return rentalHistory;
    }

    private boolean hasActiveRental(String custId){
        return activeRentals.stream()
                .anyMatch(rental -> rental.getCustId().equals(custId));    }

    public void rentAnVehicle(String custId, String userName, int vehicleId, Date returnDate){

        boolean hasActiveRental = hasActiveRental(custId);

        if(hasActiveRental){
            System.out.println("You have already rented an vehicle");
            return;
        }

        List<Vehicle> chosenVehicle = vehicleRepository.getVehicles().stream()
                                                                        .filter(user ->
                                                                                user.getId() == vehicleId).toList();

        if(chosenVehicle.isEmpty()) {
            System.out.println("You've entered invalid id!");
            return;
        }

        Vehicle choosenVehicle = chosenVehicle.getFirst();

        String rentedModel = choosenVehicle.getModel();

        Date currentDate = new Date();

        if(returnDate == null){
            System.out.println("ReturnDate is not given properly");
            return; 
        }

        //Java Date objects store time internally as milliseconds.
        long diffInMs = returnDate.getTime() - currentDate.getTime();

        //1000 ms is 1 sec, 60 secs in a min, 60 mins in a hour, 24 hrs in a day
        long diffInDays = diffInMs / (1000 * 60 * 60 * 24);

        if(diffInDays <= 0){
            System.out.println("Return date should be after today!!");
            return;
        }

        int rentPrice  = choosenVehicle.getRentPerDay();
        long totalRent = diffInDays * rentPrice;

        Rental rentalDetail = new Rental(custId, userName, rentedModel, vehicleId, (int)totalRent, returnDate);
        activeRentals.add(rentalDetail);
        chosenVehicle.getFirst().setAvailable(false);

    }

    public Rental getCustactiveRentals(String userName, String userId){
        List<Rental> custRentalDetail = getAllRentals().stream().filter((user) ->
                                                                                    user.getUserName().equals(userName) &&
                                                                                    user.getCustId().equals(userId)).toList();

        if(custRentalDetail.isEmpty()){
            return null;
        }

        return custRentalDetail.getFirst();
    }

    public Rental custactiveRentals(String userName, String userId){
        Rental custRentalDetail = getCustactiveRentals(userName, userId);

        if(custRentalDetail == null){
            System.out.println("You have not rented any vehicle!");
            return null;
        }

        System.out.println("-----------------------------");
        System.out.println("    Your rental details    ");
        System.out.println("-----------------------------");

        System.out.println(custRentalDetail);
        return custRentalDetail;
    }

    public void returnVehicle(String userName, String userId){
        Rental rental = getCustactiveRentals(userName, userId);

        if(rental == null) {
            return;
        }

        int vehicleId = rental.getVehicleId();

        List<Vehicle> rentedVehicle = vehicleRepository.getVehicles().stream().filter((vehicle ->
                                                                                            vehicle.getId() == vehicleId)).toList();

        if(rentedVehicle.isEmpty()){
            System.out.println("You have not rented any vehicle!");
            return;
        }

        rentedVehicle.getFirst().setAvailable(true);
        // storing the history of rental.
        rentalHistory.add(rental);
        // then removing it from the active rentals.
        activeRentals.remove(rental);
        System.out.println("You've returned successfully!!");
    }

    public int getRentedVehiclesCount() {
        return activeRentals.size();
    }
}
