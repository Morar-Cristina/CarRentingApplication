package Repository.FileRepository;

import Domain.Customer;
import Repository.memory.EntityRepository;

import java.io.*;

public class CustomerTextFile extends FileRepos<String, Customer> {

    public CustomerTextFile(String filename) {
        super(filename);
    }

    @Override
    public void readFromFile() {
        EntityRepository<Customer> repo = new EntityRepository<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] elems = line.split(",");
                if (elems.length != 4)
                    continue;
                Customer c = new Customer(elems[0].strip(), elems[1].strip(), elems[2].strip(), elems[3].strip());
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
            for (Customer c : super.getAll()) {
                bw.write(c.getID() + ", " + c.getName() + ", " + c.getEmail() + ", " + c.getPhone());
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
