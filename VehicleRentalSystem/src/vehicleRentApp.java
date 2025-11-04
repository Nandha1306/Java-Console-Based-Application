import entity.*;
import repository.AdminRepository;
import repository.RentalRepository;
import repository.UserRepository;
import repository.VehicleRepository;
import service.AdminService;
import service.CustomerService;
import service.RentalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class vehicleRentApp {

    private static final Scanner sc = new Scanner(System.in);

    private static UserRepository userRepository = new UserRepository();
    private static VehicleRepository vehicleRepository = new VehicleRepository();
    private static RentalRepository rentalRepository = new RentalRepository(vehicleRepository);
    private static AdminRepository adminRepository = new AdminRepository(rentalRepository, vehicleRepository);

    private static CustomerService customerService = new CustomerService(userRepository, vehicleRepository, rentalRepository);
    private static AdminService adminService = new AdminService(adminRepository, vehicleRepository, rentalRepository);
    private static RentalService rentalService = new RentalService(rentalRepository);

    public static void main(String[] args){
        while(true){
            System.out.println("Enter Username: ");
            String userName = sc.next();

            System.out.println("Enter Password: ");
            String password = sc.next();

            User user = userRepository.login(userName, password);

            if (user == null) {
                System.out.println("Login failed. Try again!");
                continue;
            }

            //runtime type identification
            //if the logged-in user is customer means it will get into initCustomer method and same as fot admin
            if (user instanceof Customer) {
                initUser(user);
            } else if (user instanceof Admin) {
                initAdmin(user);
            } else {
                System.out.println("Login Failed");
            }
        }
    }

    private static void initUser(User user){
        boolean flag = true;
        System.out.println("Heyy! " + user.getUserName());
        System.out.println("You logged in as Customer");

        while(flag){
            System.out.println("------------------------------");
            System.out.println("        CUSTOMER MENU         ");
            System.out.println("------------------------------");
            System.out.println("1.View available vehicle");
            System.out.println("2.See rented details");
            System.out.println("3.Rent an vehicle");
            System.out.println("4.Return an rented vehicle");
            System.out.println("5.Logout");
            System.out.println("------------------------------");

            int option = -1;

            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter numbers only.");
                sc.nextLine(); // clear buffer
                continue; // restart loop
            }

            switch (option) {
                case 1:
                    customerService.listAllAvailableVehicles();
                    break;
                case 2:
                    Rental userRentalDetail = customerService.custactiveRentals(user.getUserName(), user.getUserId());
                    if(userRentalDetail == null){
                        break;
                    }
                    System.out.println(userRentalDetail);
                    break;
                case 3:
                    customerService.listAllAvailableVehicles();
                    System.out.println("Enter the id of an car you want to rent.");
                    int vehicleId = sc.nextInt();

                    System.out.println("Enter return date (formate - dd-mm-yyyy)");
                    String date = sc.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    Date returnDate = null;
                    try {
                        returnDate = dateFormat.parse(date);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format! Please use dd-MM-yyyy");
                        continue; //go back to menu
                    }

                    rentalService.rentAnVehicle(user.getUserId(), user.getUserName(), vehicleId, returnDate);
                    break;
                case 4:
                    Rental custRentalDetail = customerService.custactiveRentals(user.getUserName(), user.getUserId());

                    if(custRentalDetail == null){
                        System.out.println("You have not rented any vehicle!");
                        break;
                    }

                    customerService.returnVehicle(custRentalDetail.getUserName(), custRentalDetail.getCustId());
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void initAdmin(User user){
        boolean flag = true;
        System.out.println("Heyy " + user.getUserName());
        System.out.println("You logged in as Admin");

        while(flag){
            System.out.println("------------------------------");
            System.out.println("          ADMIN MENU          ");
            System.out.println("------------------------------");
            System.out.println("1.See rental details");
            System.out.println("2.See History of rentals");
            System.out.println("3.Add Vehicle");
            System.out.println("4.Delete a vehicle");
            System.out.println("5.View vehicle availability summary");
            System.out.println("6.Logout");
            System.out.println("------------------------------");

            int option = -1;

            try {
                option = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter numbers only.");
                sc.nextLine(); // clear buffer
                continue; // restart loop
            }
            switch (option){
                case 1:
                    adminService.getAllactiveRentals();
                    break;
                case 2:
                    adminService.getAllRentalHistory();
                    break;
                case 3:
                    System.out.println("Enter Vehicle ID: ");
                    int id = sc.nextInt();
                    System.out.println("Enter Brand: ");
                    String brand = sc.next();
                    System.out.println("Enter Model: ");
                    String model = sc.next();
                    System.out.println("Enter Rent per day: ");
                    int price = sc.nextInt();

                    Vehicle newVehicle = new Vehicle(id, brand, model, price, true);
                    adminService.addVehicle(newVehicle);
                    break;
                case 4:
                    System.out.println("Enter Vehicle Id want to delete: ");
                    int vehicleId = -1;
                    try {
                        vehicleId = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Enter only number!");
                        continue; //go back to menu
                    }
                    adminService.removeVehicle(vehicleId);
                    break;
                case 5:
                    adminService.getVehiclesCount();
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
