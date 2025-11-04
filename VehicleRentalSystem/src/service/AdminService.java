package service;

import entity.Vehicle;
import repository.AdminRepository;
import repository.RentalRepository;
import repository.VehicleRepository;

public class AdminService {
//    private AdminRepository adminRepository = new AdminRepository();

    private AdminRepository adminRepository;
    private VehicleRepository vehicleRepository;
    private RentalRepository rentalRepository;

    public AdminService(AdminRepository adminRepository, VehicleRepository vehicleRepository, RentalRepository rentalRepository){
        this.adminRepository = adminRepository;
        this.vehicleRepository = vehicleRepository;
        this.rentalRepository = rentalRepository;
    }

    public void getAllactiveRentals(){
        adminRepository.getAllactiveRentals();
    }

    public void getAllRentalHistory() {
        adminRepository.getAllRentalHistory();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicleRepository.addVehicle(vehicle);
    }

    public void removeVehicle(int vehicleId) {
        adminRepository.removeVehicle(vehicleId);
    }

    public void getVehiclesCount(){
        int totalVehicles = vehicleRepository.getAvailableVehiclesCount() + rentalRepository.getRentedVehiclesCount();
        System.out.println("-------- VEHICLE SUMMARY --------");
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Rented vehicles: " + vehicleRepository.getAvailableVehiclesCount());
        System.out.println("Available vehicles: " + rentalRepository.getRentedVehiclesCount());
        System.out.println("---------------------------------");
    }
}
