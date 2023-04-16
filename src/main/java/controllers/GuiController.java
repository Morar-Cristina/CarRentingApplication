package controllers;

import Domain.Car;
import Domain.Customer;
import Domain.Entity;
import Domain.Reservation;
import Service.Service;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.RuntimeException;
import java.security.KeyStore;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import Exception.*;
import javafx.util.Callback;
import javafx.util.Pair;

public class GuiController {

    public TextField idCar;
    public TextField yearCar;
    public TextField priceCar;
    public TextField modelMasina;
    public Button deleteCar;
    public TextField idReservation;
    public TextField idResCar;
    public TextField idCusRes;
    public TextField dateReservation;
    public Button deleteReservation;
    public TextField idCustomer;
    public TextField nameCustomer;
    public TextField emailCustomer;
    public TextField phoneCustomer;
    public Button deleteCustomer;
    private Service service;
    public Button buttonCancelCar;
    public Button buttonAddCar;
    public Button buttonCancelReservation;
    public Button buttonAddReservation;
    public Button buttonCancelCustomer;
    public Button buttonAddCustomer;
    //Buttons


    public Group grCar;
    public Group gtReservation;
    public Group grCustomer;
    //Groups

    public TableView<Car> tableCar;
    @FXML
    ObservableList<Car> modelCar = FXCollections.observableArrayList();
    public TableColumn<Car, String> colModel;
    public TableColumn<Car, String> colYear;
    public TableColumn<Car, String> colPrice;
    //Table Car

    public TableView<Customer> tableCustomer;
    ObservableList<Customer> modelCustomer = FXCollections.observableArrayList();

    public TableColumn<Customer, String> colName;
    public TableColumn<Customer, String> colPhone;
    //table costumer

    public TableView<Pair<Car, Customer>> tableReservation;
    ObservableList<Pair<Car, Customer>> modelReservation = FXCollections.observableArrayList();

    public TableColumn<Pair<Car, Customer>, String> colCar;
    public TableColumn<Pair<Car, Customer>, String> colCustomer;
    //table rent


    void populateTables() {
        var carList = service.getCars();
        var customerList = service.getCustomers();
        modelCar.setAll(carList);
        modelCustomer.setAll(customerList);
        var reservationList = service.getReservations().stream().map(e -> {
            try {
                return new Pair<Car, Customer>(service.carService.get(e.getCarID()), service.customerService.get(e.getCustomerID()));
            } catch (MainException ignored) {

            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        modelReservation.setAll(reservationList);
    }

    public void initialize() {

        colModel.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        colYear.setCellValueFactory(new PropertyValueFactory<Car, String>("productionYear"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Car, String>("price"));

        colName.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));

        colCar.setCellValueFactory(param -> new SimpleStringProperty((param.getValue().getKey().getModel())));
        colCustomer.setCellValueFactory(param -> new SimpleStringProperty((param.getValue().getValue().getName())));

        tableCar.setItems(modelCar);
        tableCustomer.setItems(modelCustomer);
        tableReservation.setItems(modelReservation);
    }

    public void setService(Service srv) {
        this.service = srv;
        populateTables();
    }


    public void addCar(ActionEvent actionEvent) {

        if (idCar.getText().isBlank() || priceCar.getText().isBlank() || modelMasina.getText().isBlank() || yearCar.getText().isBlank()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText("Empty fields");
            al.show();

        } else {
            Car aux = new Car(idCar.getText(), modelMasina.getText(), yearCar.getText(), priceCar.getText());
            try {
                service.addCar(aux);
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("A new car was added");
                al.show();
                cancelAddCar(actionEvent);
                populateTables();
            } catch (RuntimeException | MainException e) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText(e.getMessage());
                al.show();
                cancelAddCar(actionEvent);
            }
        }
    }

    public void cancelAddCar(ActionEvent actionEvent) {
        idCar.setText("");
        modelMasina.setText("");
        yearCar.setText("");
        priceCar.setText("");
    }

    public void deleteCar(ActionEvent actionEvent) {
        var x = tableCar.getSelectionModel().getSelectedItem();
        if (x == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText("Select a car");
            al.show();
            return;
        }
        try {
            service.carService.remove(x);
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Car was removed.");
            al.show();
            populateTables();
        } catch (MainException e) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText(e.getMessage());
            al.show();
        }
    }

    public void addCustomer(ActionEvent actionEvent) {
        if (idCustomer.getText().isBlank() || nameCustomer.getText().isBlank() || emailCustomer.getText().isBlank() || phoneCustomer.getText().isBlank()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText("Empty fields");
            al.show();
        } else {
            Customer aux = new Customer(idCustomer.getText(), nameCustomer.getText(), emailCustomer.getText(), phoneCustomer.getText());
            try {
                service.addCustomer(aux);
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("A new customer was added");
                al.show();
                cancelAddCustomer(actionEvent);
                populateTables();
            } catch (RuntimeException | MainException e) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText(e.getMessage());
                al.show();
                cancelAddCustomer(actionEvent);
            }
        }
    }

    public void cancelAddCustomer(ActionEvent actionEvent) {
        idCustomer.setText("");
        nameCustomer.setText("");
        emailCustomer.setText("");
        phoneCustomer.setText("");
    }

    public void deleteCustomer(ActionEvent actionEvent) {
        var x = tableCustomer.getSelectionModel().getSelectedItem();
        if (x == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText("Select a customer");
            al.show();
            return;
        }
        try {
            service.customerService.remove(x);
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Customer was removed");
            al.show();
            populateTables();
        } catch (MainException e) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText(e.getMessage());
            al.show();
        }
    }


    public void addReservation(ActionEvent actionEvent) {
        if (idReservation.getText().isBlank() || idResCar.getText().isBlank() || idCusRes.getText().isBlank() || dateReservation.getText().isBlank()) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText("Empty fields");
            al.show();
        } else {
            Reservation aux = new Reservation(idReservation.getText(), idResCar.getText(), idResCar.getId(), dateReservation.getText());
            try {
                service.addReservation(aux);
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText("A new reservation was added");
                al.show();
                cancelAddReservation(actionEvent);
                populateTables();
            } catch (RuntimeException | MainException e) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setContentText(e.getMessage());
                al.show();
                cancelAddReservation(actionEvent);
            }
        }
    }

    public void cancelAddReservation(ActionEvent actionEvent) {
        idReservation.setText("");
        idResCar.setText("");
        idCusRes.setText("");
        dateReservation.setText("");
    }

    public void deleteReservation(ActionEvent actionEvent) {
        var x = tableReservation.getSelectionModel().getSelectedItem();
        if (x == null) {
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setContentText("Select a reservation");
            al.show();
            return;
        }

    }


}
