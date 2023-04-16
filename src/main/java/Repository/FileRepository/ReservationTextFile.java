package Repository.FileRepository;

import Domain.Reservation;
import Repository.memory.EntityRepository;

import java.io.*;

public class ReservationTextFile extends FileRepos<String, Reservation> {

    public ReservationTextFile(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        EntityRepository<Reservation> repo = new EntityRepository<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] elems = line.split(",");
                if (elems.length != 4)
                    continue;
                repo.add(elems[0].strip(), new Reservation(elems[0].strip(), elems[1].strip(), elems[2].strip(), elems[3].strip()));
            }
            this.repository = repo.repository;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error while closing the file " + e);
                }
        }
    }

    @Override
    public void writeToFile() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(this.filename));
            for (Reservation r : super.getAll()) {
                bw.write(r.getID() + ", " + r.getCarID() + ", " + r.getCustomerID() + ", " + r.getDate().toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bw != null;
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
