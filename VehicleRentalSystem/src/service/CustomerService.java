package service;

import entity.Customer;
import entity.Rental;
import entity.User;
import repository.RentalRepository;
import repository.UserRepository;
import repository.VehicleRepository;

public class CustomerService {
//    private static UserRepository userRepository = new UserRepository();
//    private static VehicleRepository vehicleRepository = new VehicleRepository();
//    private static RentalRepository rentalRepository = new RentalRepository();

    private UserRepository userRepository;
    private VehicleRepository vehicleRepository;
    private RentalRepository rentalRepository;

    public CustomerService(UserRepository userRepository, VehicleRepository vehicleRepository, RentalRepository rentalRepository){
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.rentalRepository = rentalRepository;
    }

    public User login(String userName, String password){
        return userRepository.login(userName, password);
    }

    public Rental custactiveRentals(String userName, String userId){
        return rentalRepository.custactiveRentals(userName, userId);
    }

    public void listAllAvailableVehicles(){
        vehicleRepository.listAllAvailableVehicles();
    }

    public void returnVehicle(String userName, String userId){
        rentalRepository.returnVehicle(userName, userId);
    }
}
