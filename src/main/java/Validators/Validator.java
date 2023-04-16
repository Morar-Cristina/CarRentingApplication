package Validators;

import Domain.Car;
import Domain.Customer;
import Domain.Reservation;


public class Validator {

    private static void Namevalidator(String name) throws RuntimeException {
        if (name == null || name.equals(""))
            throw new RuntimeException("Please input a valid name!");
    }

    private static void PhoneValidator(String phone) throws RuntimeException {
        if (phone == null || phone.equals(""))
            throw new RuntimeException("Please input a valid phone number!");
        if (phone.length() > 11)
            throw new RuntimeException("Please input a valid phone number!");
    }

    private static void EmailValidator(String email) throws RuntimeException {
        if (email == null || email.equals(""))
            throw new RuntimeException("Please input a valid email!");
    }

    private static void ModelValidator(String model) throws RuntimeException {
        if (model == null || model.equals(""))
            throw new RuntimeException("Please input a valid car model!");
    }

    private static void PriceValidator(String price) throws RuntimeException {
        if (price == null || price.equals(""))
            throw new RuntimeException("Please input a valid price!");
        if (price.length() > 6)
            throw new RuntimeException("Car's price exceeded it's limit!");
    }

    private static void ProductionValidator(String year) throws RuntimeException {
        if (year == null || year.equals(""))
            throw new RuntimeException("Please input a valid production year!");
        if (year.length() > 5)
            throw new RuntimeException("Please input a valid year!");
        if (year.length() < 4)
            throw new RuntimeException("Please input a valid year!");
    }

    static public void check(Customer c) throws RuntimeException {
        if (c == null)
            throw new RuntimeException("Customer doesn't exist!");
        Namevalidator(c.getName());
        PhoneValidator(c.getPhone());
        EmailValidator(c.getEmail());
    }

    static public void check(Car c) throws RuntimeException {
        if (c == null)
            throw new RuntimeException("Car doesn't exist!");
        ModelValidator(c.getModel());
        ProductionValidator(c.getProductionYear());
        PriceValidator(c.getPrice());
    }

    static public void check(Reservation r) throws RuntimeException {
        if (r == null)
            throw new RuntimeException("Reservation doesn't exist");
    }
}
