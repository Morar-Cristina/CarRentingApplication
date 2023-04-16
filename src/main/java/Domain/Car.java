package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Entity<String>, Serializable {
    private String Id;

    private String model;

    private String productionYear;

    private String price;


    public Car(String Id, String model, String productionYear, String price) {
        this.Id = Id;
        this.model = model;
        this.productionYear = productionYear;
        this.price = price;
    }

    @Override
    public void setID(String id) {
        this.Id = id;
    }

    public String getID() {
        return Id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "{Car: " + this.getID() + " " + this.model + " " + this.productionYear + " " + this.getPrice() + " " +  "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(Id, car.Id) && Objects.equals(productionYear, car.productionYear) && Objects.equals(car.price, price) && Objects.equals(model, car.model);
    }
}
