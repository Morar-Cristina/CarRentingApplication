package Domain;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Entity<String>, Serializable {

    private String Id;

    private String name;

    private String email;

    private String phone;

    public Customer(String id, String name, String email, String phone) {
        this.Id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getID() {
        return Id;
    }

    @Override
    public void setID(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return "{Customer: " + this.Id + " " + this.name + " " + this.email + " " + this.phone + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(Id, customer.Id);
    }
}
