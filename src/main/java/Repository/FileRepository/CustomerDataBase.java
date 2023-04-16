package Repository.FileRepository;

import Domain.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDataBase extends FileRepos<String, Customer> {


    private Connection conn = null;

    public CustomerDataBase(String filename) {
        super(filename);
    }

    private void openConnection() {

        try {
            if (conn == null || conn.isClosed())
                conn = DriverManager.getConnection(filename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void readFromFile() {
        try {
            openConnection();
            ArrayList<Customer> NewCustomers = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * from customers");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer C = new Customer(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"));
                NewCustomers.add(C);
            }
            rs.close();
            statement.close();
            for (Customer C : NewCustomers) {
                super.add(C.getID(), C);
            }
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    protected void writeToFile() {
        try {
            openConnection();
            try {
                PreparedStatement statement = conn.prepareStatement(
                        "DELETE FROM customers"
                );
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (Customer C : super.getAll()) {
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO customers VALUES (?,?,?,?)"
                );
                statement.setString(1, C.getID());
                statement.setString(2, C.getName());
                statement.setString(3, C.getEmail());
                statement.setString(4, C.getPhone());
                statement.executeUpdate();
                statement.close();
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}