package Repository.FileRepository;

import Domain.Customer;

import java.io.*;
import java.util.ArrayList;

public class CustomerBinary extends FileRepos<String, Customer> {

    public CustomerBinary(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        ArrayList<Customer> customers = null;
        try (var in = new ObjectInputStream(new FileInputStream(this.filename))) {
            customers = (ArrayList<Customer>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Customer c : customers) {
            super.add(c.getID(), c);
        }
    }

    @Override
    public void writeToFile() {
        var customers = new ArrayList<>(this.repository.values());
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(this.filename));
            out.writeObject(customers);
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
