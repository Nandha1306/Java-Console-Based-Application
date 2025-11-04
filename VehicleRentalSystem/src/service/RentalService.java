package service;

import repository.RentalRepository;

import java.util.Date;

public class RentalService {
//    private RentalRepository rentalRepository = new RentalRepository();

    private RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository){
        this.rentalRepository = rentalRepository;
    }

    public void rentAnVehicle(String custId, String userName, int vehicleId, Date returnDate){
        rentalRepository.rentAnVehicle(custId, userName, vehicleId , returnDate);
    }
}
