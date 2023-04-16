package Repository.FileRepository;

import Domain.Reservation;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDataBase extends FileRepos<String, Reservation> {


    private Connection conn = null;

    public ReservationDataBase(String filename) {
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
            ArrayList<Reservation> NewReservations = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * from reservations");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Reservation R = new Reservation(rs.getString("id"),
                        rs.getString("carId"),
                        rs.getString("customerID"),
                        rs.getString("date"));
                NewReservations.add(R);
            }
            rs.close();
            statement.close();
            for (Reservation R: NewReservations) {
                super.add(R.getID(), R);
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
                        "DELETE FROM reservations"
                );
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (Reservation R : super.getAll()) {
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO reservations VALUES (?,?,?,?)"
                );
                statement.setString(1, R.getID());
                statement.setString(2, R.getCarID());
                statement.setString(3, R.getCustomerID());
                statement.setString(4, R.getDate());
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