package Repository.FileRepository;

import Domain.Car;

import java.io.*;
import java.util.ArrayList;

public class CarBinary extends FileRepos<String, Car> {

    public CarBinary(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        ArrayList<Car> cars = null;
        try (var in = new ObjectInputStream(new FileInputStream(this.filename))) {
            cars = (ArrayList<Car>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Car c : cars) {
            super.add(c.getID(), c);
        }
    }

    @Override
    public void writeToFile() {
        var cars = new ArrayList<>(this.repository.values());
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(this.filename));
            out.writeObject(cars);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
