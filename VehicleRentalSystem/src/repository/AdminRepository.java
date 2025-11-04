package repository;

import entity.Rental;
import entity.Vehicle;

import java.util.List;
import java.util.Map;

public class AdminRepository {
//    private RentalRepository rentalRepository = new RentalRepository();

    private RentalRepository rentalRepository;
    private VehicleRepository vehicleRepository;

    public AdminRepository(RentalRepository rentalRepository, VehicleRepository vehicleRepository) {
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void getAllactiveRentals(){
        if(rentalRepository.getAllRentals().isEmpty()){
            System.out.println("No users have rented!");
            return;
        }
        for(Rental rental : rentalRepository.getAllRentals()){
            System.out.println(rental);
        }
    }

    public void getAllRentalHistory() {
        if(rentalRepository.getAllRentalHistory().isEmpty()){
            System.out.println("No history of rentals");
            return;
        }

        for(Rental rental : rentalRepository.getAllRentalHistory()){
            System.out.println(rental);
        }
    }

    public void removeVehicle(int vehicleId) {
        List<Vehicle> vehicles = vehicleRepository.getVehicles().stream().filter((vehicle ->
                                                                                                        vehicle.getId() == vehicleId)).toList();

            if(vehicles.isEmpty()){
            System.out.println("Vehicle with Id " + vehicleId + " is not available");
            return;
        } else {
            boolean isRented = rentalRepository.getAllRentals().stream().anyMatch((rental) ->
                    rental.getVehicleId() == vehicleId);

            if(isRented){
                System.out.println("Vehicle with Id " + vehicleId + " is already rented");
                System.out.println("After return you may delete it.");
                return;
            } else {
                vehicleRepository.removeVehicle(vehicles.get(0));
                return;
            }
        }
    }
}
