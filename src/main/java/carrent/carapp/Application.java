package carrent.carapp;

import Domain.Car;
import Domain.Customer;
import Domain.Reservation;
import Repository.FileRepository.*;
import Repository.IRepo;
import Repository.memory.EntityRepository;
import Service.CarService;
import Service.CustomerService;
import Service.ReservationService;
import Service.Service;
import controllers.GuiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Application extends javafx.application.Application {
    public static IRepo<String, Car> readPropertiesCreateCarRepo() {
        String repoType = null;
        String carFilePath = null;
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream("gradle/wrapper/gradle-wrapper.properties")) {
            properties.load(is);

            repoType = properties.getProperty("Repository");
            carFilePath = properties.getProperty("Car");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (repoType.equals("memory"))
            return createDefaultCarRepo();
        if (repoType.equals("text"))
            return new CarTextFile(carFilePath);
        if (repoType.equals("binary"))
            return new CarBinary(carFilePath);
        if (repoType.equals("database"))
            return new CarDataBase(carFilePath);

        return createDefaultCarRepo();
    }

    private static IRepo<String, Car> createDefaultCarRepo() {

        IRepo<String, Car> CRepo = new EntityRepository<>();

        CRepo.add("1", new Car("1", "BMW", "2013", "15000"));
        CRepo.add("2", new Car("2", "Audi", "2007", "8000"));
        CRepo.add("3", new Car("3", "Dacia", "1998", "2000"));

        return CRepo;
    }

    public static IRepo<String, Customer> readPropertiesCreateCustomerRepo() {
        String repoType = null;
        String customerFilePath = null;
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream("gradle/wrapper/gradle-wrapper.properties")) {
            properties.load(is);

            repoType = properties.getProperty("Repository");
            customerFilePath = properties.getProperty("Customer");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (repoType.equals("memory"))
            return createDefaultCustomerRepo();
        if (repoType.equals("text"))
            return new CustomerTextFile(customerFilePath);
        if (repoType.equals("binary"))
            return new CustomerBinary(customerFilePath);
        if (repoType.equals("database"))
            return new CustomerDataBase(customerFilePath);

        return createDefaultCustomerRepo();
    }

    private static IRepo<String, Customer> createDefaultCustomerRepo() {

        IRepo<String, Customer> CRepo = new EntityRepository<>();

        CRepo.add("1", new Customer("1", "Phineas Flin", "phineas66@yahoo.com", "0711223344"));
        CRepo.add("2", new Customer("2", "Mirela Vaida", "mirela.vaida20@gmail.com", "0751234567"));
        CRepo.add("3", new Customer("3", "Pablo Escobar", "pabloescobar420@yahoo.com", "0766666666"));

        return CRepo;
    }

    public static IRepo<String, Reservation> readPropertiesCreateReservationRepo() {
        String repoType = null;
        String reservationFilePath = null;
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream("gradle/wrapper/gradle-wrapper.properties")) {
            properties.load(is);

            repoType = properties.getProperty("Repository");
            reservationFilePath = properties.getProperty("Reservation");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (repoType.equals("memory"))
            return createDefaultReservationRepo();
        if (repoType.equals("text"))
            return new ReservationTextFile(reservationFilePath);
        if (repoType.equals("binary"))
            return new ReservationBinary(reservationFilePath);
        if (repoType.equals("database"))
            return new ReservationDataBase(reservationFilePath);

        return createDefaultReservationRepo();
    }

    private static IRepo<String, Reservation> createDefaultReservationRepo() {

        IRepo<String, Reservation> RRepo = new EntityRepository<>();

        RRepo.add("23", new Reservation("23", "2", "3", "2022-12-05"));
        RRepo.add("12", new Reservation("12", "1", "2", "2022-12-06"));
        RRepo.add("31", new Reservation("31", "3", "1", "2022-12-07"));

        return RRepo;
    }


    @Override
    public void start(Stage stage) throws IOException {

        IRepo<String, Car> carRepo = readPropertiesCreateCarRepo();
        IRepo<String, Customer> customerRepo = readPropertiesCreateCustomerRepo();
        IRepo<String, Reservation> reservationRepo = readPropertiesCreateReservationRepo();
        CarService carService = new CarService(carRepo);
        CustomerService customerService = new CustomerService(customerRepo);
        ReservationService reservationService = new ReservationService(reservationRepo);
        Service serv = new Service(carService, customerService, reservationService);


        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        GuiController ctrl=fxmlLoader.getController();
        ctrl.setService(serv);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}