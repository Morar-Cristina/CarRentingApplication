package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Reservation implements Entity<String>, Serializable {

    public String Id;

    public String CarID;

    public String CustomerID;

    public String date;

    public Reservation() {
        this.Id = "";
        this.CarID = "";
        this.CustomerID = "";
        this.date = "";
    }

    public Reservation(String id, String carID, String customerID, String date) {
        Id = id;
        CarID = carID;
        CustomerID = customerID;
        this.date = date;
    }

    public String getID() {
        return Id;
    }

    public void setID(String id) {
        Id = id;
    }

    public String getCarID() {
        return CarID;
    }

    public void setCarID(String carID) {
        CarID = carID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{Reservation: " + this.Id + " " + this.CarID + " " + this.CustomerID + " " + this.date + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation res = (Reservation) o;
        return Objects.equals(Id, res.Id);
    }
}
