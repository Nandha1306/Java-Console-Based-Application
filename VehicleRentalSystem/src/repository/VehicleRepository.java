package repository;

import entity.Rental;
import entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public VehicleRepository() {
        Vehicle vehicle1 = new Vehicle(1, "swift", "dezire", 1000, true);
        Vehicle vehicle2 = new Vehicle(2, "maruthi", "alto", 1500, true);
        Vehicle vehicle3 = new Vehicle(3, "hundai", "k10", 800, true);
        Vehicle vehicle4 = new Vehicle(4, "honda", "some", 2000, true);

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void listAllAvailableVehicles(){

        if(vehicles.isEmpty()){
            System.out.println("Sorry, no vehicles are available now");
            return;
        }

        for(Vehicle vehicle : vehicles){
            if(vehicle.isAvailable()){
                System.out.println(vehicle);
            }
        }
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
        System.out.println("Vehicle added successfully");
    }

    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
        System.out.println("Vehicle removed successfully");
    }

    public int getAvailableVehiclesCount() {
        int availableVehicleCnt = 0;

        for(Vehicle vehicle : vehicles){
            if(vehicle.isAvailable()){
                availableVehicleCnt++;
            }
        }
        return availableVehicleCnt;
    }
}
