package Repository.FileRepository;

import Domain.Car;

import java.sql.*;
import java.util.ArrayList;

public class CarDataBase extends FileRepos<String, Car>{
    public CarDataBase(String filename) {
        super(filename);
    }

    private Connection conn = null;

    private void openConnection(){

        try {
            if(conn == null || conn.isClosed())
                conn = DriverManager.getConnection(filename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try {
            conn.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void readFromFile() {
        try {
            openConnection();
            ArrayList<Car> NewCars = new ArrayList<>();

            PreparedStatement statement = conn.prepareStatement(
                    "SELECT * from cars");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Car C = new Car(rs.getString("id"),
                        rs.getString("model"),
                        rs.getString("prodYr"),
                        rs.getString("price"));
                NewCars.add(C);
            }
            rs.close();
            statement.close();
            for (Car C: NewCars) {
                super.add(C.getID(),C);
            }
            closeConnection();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeConnection();
        }
    }

    @Override
    protected void writeToFile() {
        try{
            openConnection();
            try{
                PreparedStatement statement = conn.prepareStatement(
                        "DELETE FROM cars"
                );
                statement.executeUpdate();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            for (Car C: super.getAll()) {
                PreparedStatement statement = conn.prepareStatement(
                        "INSERT INTO cars VALUES (?,?,?,?)"
                );
                statement.setString(1,C.getID());
                statement.setString(2, C.getModel());
                statement.setString(3,C.getProductionYear());
                statement.setString(4,C.getPrice());
                statement.executeUpdate();
                statement.close();
            }

            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }
}
