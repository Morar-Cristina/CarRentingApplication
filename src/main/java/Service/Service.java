package Service;

import Domain.Car;
import Domain.Customer;
import Domain.Reservation;
import Validators.Validator;
import Exception.*;

import java.lang.RuntimeException;
import java.util.ArrayList;


public class Service {

    public final CarService carService;

    public final CustomerService customerService;

    public final ReservationService reservationService;

    public Service(CarService carService, CustomerService customerService, ReservationService reservationService) {
        this.carService = carService;
        this.customerService = customerService;
        this.reservationService = reservationService;
    }


    public Reservation makeReservation(String CarID, String CustomerID, String date) throws RuntimeException, MainException {
        if (!carService.contains(CarID) || !customerService.contains(CustomerID))
            throw new MainException("Car or Customer hasn't been found!");
        Reservation res = new Reservation(CarID + CustomerID, CarID, CustomerID, date);
        Validator.check(res);
        reservationService.add(res);
        return res;
    }

    public boolean cancelReservation(String reservationID) throws MainException {
        return reservationService.remove(reservationService.get(reservationID));
    }

    public void addCar(Car... cars) throws RuntimeException, MainException {
        for (Car c : cars) {
            Validator.check(c);
            carService.add(c);
        }
    }

    public void addCustomer(Customer... customers) throws RuntimeException, MainException {
        for (Customer c : customers) {
            Validator.check(c);
            customerService.add(c);
        }
    }

    public void addReservation(Reservation... reservations) throws RuntimeException, MainException {
        for (Reservation r : reservations) {
            Validator.check(r);
            reservationService.add(r);
        }
    }


    public boolean removeCar(String carID) throws RuntimeException, MainException {
        return carService.remove(carService.get(carID));
    }

    public boolean removeCustomer(String customerID) throws RuntimeException, MainException {
        return customerService.remove(customerService.get(customerID));
    }

    public void updateCar(Car c) throws RuntimeException, MainException {
        Validator.check(c);
        carService.update(c);
    }


    public void updateCustomer(Customer c) throws RuntimeException, MainException {
        Validator.check(c);
        customerService.update(c);
    }

    public void updateReservation(Reservation r) throws RuntimeException, MainException {
        Validator.check(r);
        reservationService.update(r);
    }

    public ArrayList<Car> getCars() {
        return carService.get();
    }

    public ArrayList<Customer> getCustomers() {
        return customerService.get();
    }

    public ArrayList<Reservation> getReservations() {
        return reservationService.get();
    }
}
