package Repository.FileRepository;

import Domain.Car;
import Repository.memory.EntityRepository;

import java.io.*;

public class CarTextFile extends FileRepos<String, Car> {

    public CarTextFile(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        EntityRepository<Car> repo = new EntityRepository<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] elems = line.split(",");
                if (elems.length != 4)
                    continue;
                Car c = new Car(elems[0].strip(), elems[1].strip(), elems[2].strip(), elems[3].strip());
                repo.add(c.getID(), c);
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
            for (Car c : super.getAll()) {
                bw.write(c.getID() + ", " + c.getModel() + ", " + c.getProductionYear() + ", " + c.getPrice());
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
