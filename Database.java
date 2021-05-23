package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection connection;
    private Statement statement;

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public void openConnectionAndCreateTables() {
        try {
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(true);
            statement = connection.createStatement();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }


//        try {
//            statement.executeUpdate("DROP TABLE IF EXISTS CUSTOMER");
//            statement.executeUpdate("DROP TABLE IF EXISTS CAR");
//            statement.executeUpdate("DROP TABLE IF EXISTS COMPANY");
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }

        try {

            String createCompanyTableSQL = "CREATE TABLE COMPANY (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR UNIQUE NOT NULL );";
            statement.executeUpdate(createCompanyTableSQL);


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        try {

            String createCarTableSQL = "CREATE TABLE CAR (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR UNIQUE NOT NULL, " +
                    "COMPANY_ID INT NOT NULL," +
                    "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID)" +
                    "REFERENCES COMPANY(ID));";
            statement.executeUpdate(createCarTableSQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        try {
            String createCustomerTableSQL = "CREATE TABLE customer (" +
                    "ID INT PRIMARY KEY AUTO_INCREMENT," +
                    "NAME VARCHAR UNIQUE NOT NULL," +
                    "RENTED_CAR_ID INT," +
                    "CONSTRAINT fk_rented_car_id FOREIGN KEY (RENTED_CAR_ID)" +
                    "REFERENCES CAR(ID));";
            statement.executeUpdate(createCustomerTableSQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
    }

    private List<Customer> parseCustomerResultSet(ResultSet resultSet) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            int rented_car_id = resultSet.getInt("RENTED_CAR_ID");
            if (resultSet.wasNull()) {
                customers.add(new Customer(resultSet.getInt("ID"),
                        resultSet.getString("NAME")));
            } else {
                customers.add(new Customer(resultSet.getInt("ID"),
                        rented_car_id,
                        resultSet.getString("NAME")));
            }
        }
        return customers;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM customer;";
            ResultSet resultSet = statement.executeQuery(sql);
            customers = parseCustomerResultSet(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return customers;
    }

    public void addCustomer(Customer customer) {
        try {
            String addCarSQL = String.format("INSERT INTO customer (NAME) VALUES ('%s');", customer.getName());
            statement.executeUpdate(addCarSQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private List<Car> parseCarResultSet(ResultSet resultSet) throws SQLException {
        List<Car> cars = new ArrayList<>();
        while (resultSet.next()) {
            cars.add(new Car(resultSet.getInt("ID"),
                    resultSet.getInt("COMPANY_ID"),
                    resultSet.getString("NAME")));
        }
        return cars;
    }

    public Company getCompanyById(int id) {
        try {
            String sql = "SELECT * FROM COMPANY WHERE id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Company(id, resultSet.getString("name"));
            } else {
                throw new SQLException("Company with such id doesn't exist");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public Car getCarById(int id) {
        try {
            String sql = "SELECT * FROM CAR WHERE id = " + id + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Car(id, resultSet.getInt("company_id"), resultSet.getString("name"));
            } else {
                throw new SQLException("Car with such id doesn't exist");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public void returnCar(Customer customer) {
        try {
            customer.setRentedCarId(-1);
            String sql = String.format("UPDATE CUSTOMER SET rented_car_id = NULL WHERE id = %s", customer.getId());
            statement.executeUpdate(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void rentCar(Customer customer, Car car) {
        try {
            String sql = String.format("UPDATE CUSTOMER SET rented_car_id = %d WHERE id = %s", car.getId(), customer.getId());
            statement.executeUpdate(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<Car> getAllNotRentedCarsInCompany(Company company) {
        List<Car> cars = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CAR " +
                    "LEFT OUTER JOIN CUSTOMER ON CAR.ID = CUSTOMER.RENTED_CAR_ID " +
                    "WHERE COMPANY_ID = " + company.getId() + " " +
                    "AND CUSTOMER.RENTED_CAR_ID is NULL;";

            ResultSet resultSet = statement.executeQuery(sql);
            cars = parseCarResultSet(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return cars;
    }

    public List<Car> getAllNotRentedCars() {
        List<Car> cars = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CAR " +
                    "INNER JOIN CUSTOMER ON CAR.ID <> CUSTOMER.RENTED_CAR_ID;";
            ResultSet resultSet = statement.executeQuery(sql);
            cars = parseCarResultSet(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return cars;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CAR;";
            ResultSet resultSet = statement.executeQuery(sql);
            cars = parseCarResultSet(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return cars;
    }

    public List<Car> getAllCarsInCompany(Company company) {
        List<Car> cars = new ArrayList<>();
        try {
            String sql = "SELECT * FROM CAR " +
                    "WHERE COMPANY_ID = " + company.getId() + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            cars = parseCarResultSet(resultSet);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return cars;
    }

    public void addCar(Car car) {
        try {
            String addCarSQL = String.format("INSERT INTO CAR (NAME, COMPANY_ID) VALUES ('%s', %d);", car.getName(), car.getCompany_id());
            statement.executeUpdate(addCarSQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = new ArrayList<>();
        try {
            String sql = "SELECT * FROM COMPANY;";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                companies.add(new Company(resultSet.getInt("ID"),
                        resultSet.getString("NAME")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return companies;
    }

    public void addCompany(Company company) {
        try {
            String addCompanySQL = String.format("INSERT INTO COMPANY (NAME) VALUES ('%s');", company.getName());
            statement.executeUpdate(addCompanySQL);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
