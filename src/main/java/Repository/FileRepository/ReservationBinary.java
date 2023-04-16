package Repository.FileRepository;

import Domain.Reservation;

import java.io.*;
import java.util.ArrayList;

public class ReservationBinary extends FileRepos<String, Reservation> {

    public ReservationBinary(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        ArrayList<Reservation> reservations = null;
        try (var in = new ObjectInputStream(new FileInputStream(this.filename))) {
            reservations = (ArrayList<Reservation>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Reservation r : reservations) {
            super.add(r.getID(), r);
        }
    }

    @Override
    public void writeToFile() {
        var reservations = new ArrayList<>(this.repository.values());
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(this.filename));
            out.writeObject(reservations);
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
