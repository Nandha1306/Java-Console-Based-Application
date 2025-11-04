package entity;

import java.util.Date;
import java.util.Objects;

public class Rental{
    protected String custId;
    protected String userName;
    protected String rentedModel;
    protected int vehicleId;
    protected int totalRent;
    protected Date returnDate;

    public Rental(String custId, String userName, String rentedModel, int vehicleId, int totalRent, Date returnDate
    ) {
//        super();
        this.custId = custId;
        this.userName = userName;
        this.rentedModel = rentedModel;
        this.vehicleId = vehicleId;
        this.totalRent = totalRent;
        this.returnDate = returnDate;
    }


    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRentedModel() {
        return rentedModel;
    }

    public void setRentedModel(String rentedModel) {
        this.rentedModel = rentedModel;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getRentPrice() {
        return totalRent;
    }

    public void setRentPrice(int totalRent) {
        this.totalRent = totalRent;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Rental rental)) return false;
        return vehicleId == rental.vehicleId && Objects.equals(custId, rental.custId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custId, vehicleId);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "custId=" + custId +
                ", userName='" + userName + '\'' +
                ", rentedModel='" + rentedModel + '\'' +
                ", vehicleId=" + vehicleId +
                ", rentPrice=" + totalRent +
                ", returnDate=" + returnDate +
                '}';
    }
}
