package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;
import java.time.LocalDateTime;


/**
 * This class creates the Customer database methods.
 * @author Isam Elder
 */
public class CustomerDaoImpl  {

    private Connection connection;

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * <p><b>
     * RUNTIME ERROR: While creating the searchCustomerByID method, I struggled to have the search results reset when the search field was set to blank. I attempted several different logical arguments to reset the tables when the search field was blank, but no combination of if or try or for loop strategies worked. At best, these failed strategies simply did nothing to refresh the tables or, at worst, simply added even more errors to the problem I was trying to fix. After meeting with course instructors and discussing with classmates, I settled on the currently implemented solution of setting an On Key Typed KeyEvent that takes action to get all parts or products when the search field isEmpty().
     * </b></p>*
     * This is the method to search parts by exact customer ID. When this method is called, it parses the current customers in the database and returns an exact match if one exists.
     * @param customerID the part id to be searched for
     * @return The customer matching the search ID criteria (only if a part is found).
     */
    public Customer searchCustomerByID(int customerID) throws SQLException {
        String query = "SELECT * FROM customers WHERE Customer_ID = ?";
        //PreparedStatement preparedStatement = JDBC.connection.prepareStatement(query);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Customer customer = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve customer details from the result set
                int customerid=resultSet.getInt("Customer_ID");
                String customerNameG=resultSet.getString("Customer_Name");
                String address=resultSet.getString("Address");
                String postalCode=resultSet.getString("Postal_Code");
                String phone=resultSet.getString("Phone");
                Timestamp createDate=resultSet.getTimestamp("Create_Date");
                LocalDateTime createDateCalendar=createDate.toLocalDateTime();
                String createdBy=resultSet.getString("Created_By");
                Timestamp lastUpdate=resultSet.getTimestamp("Last_Update");
                LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
                String lastUpdateby=resultSet.getString("Last_Updated_By");
                int Division_ID=resultSet.getInt("Division_ID");
                // Create a Customer object
                customer = new Customer(customerid, customerNameG, address, postalCode, phone, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby, Division_ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Close result set, prepared statement, and connection
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return customer;
    }

    /**
     * This is the method to search customers by exact or partial part name (it is overloaded to do so). When this method is called, it parses the current customers in the database and returns an exact or partial match if one or more exist.
     * @param customerName the part name to be searched for
     * @return The customer name matching the search text criteria, either exact matches or if the string is found at any point within part names (only if at least one part name or part of a part name is found).
     */
    public ObservableList<Customer> searchCustomerByName(String customerName) throws SQLException {
        String query = "SELECT * FROM customers WHERE Customer_Name LIKE ?";
        //PreparedStatement preparedStatement = JDBC.connection.prepareStatement(query);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //Customer customer = null;
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + customerName + "%");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve customer details from the result set
                int customerid=resultSet.getInt("Customer_ID");
                String customerNameG=resultSet.getString("Customer_Name");
                String address=resultSet.getString("Address");
                String postalCode=resultSet.getString("Postal_Code");
                String phone=resultSet.getString("Phone");
                Timestamp createDate=resultSet.getTimestamp("Create_Date");
                LocalDateTime createDateCalendar=createDate.toLocalDateTime();
                String createdBy=resultSet.getString("Created_By");
                Timestamp lastUpdate=resultSet.getTimestamp("Last_Update");
                LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
                String lastUpdateby=resultSet.getString("Last_Updated_By");
                int Division_ID=resultSet.getInt("Division_ID");
                // Create a Customer object
                Customer customer = new Customer(customerid, customerNameG, address, postalCode, phone, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby, Division_ID);
                // Add the customer object to the list of customers
                customers.add(customer);
                //customer = new Customer(customerid, customerNameG, address, postalCode, phone, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby, Division_ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // Close result set, prepared statement, and connection
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return customers;
    }

    /**
     * This is the method to create an ObservableList to get all customers. This method executes an SQL query to find all customers and then adds the customers to the ObservableList allCustomers.
     * @return the ObservableList allCustomers
     * @throws SQLException
     * @throws Exception
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException, Exception{
        ObservableList<Customer> allCustomers=FXCollections.observableArrayList();
        String sqlStatement="select * from Customers";
        Query.makeQuery(sqlStatement);
        ResultSet result=Query.getResult();
        while(result.next()){
            int customerid=result.getInt("Customer_ID");
            String customerNameG=result.getString("Customer_Name");
            String address=result.getString("Address");
            String postalCode=result.getString("Postal_Code");
            String phone=result.getString("Phone");
            Timestamp createDate=result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar=createDate.toLocalDateTime();
            String createdBy=result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
            String lastUpdateby=result.getString("Last_Updated_By");
            int Division_ID=result.getInt("Division_ID");
            Customer customerResult= new Customer(customerid, customerNameG, address, postalCode, phone, createDateCalendar, createdBy, lastUpdateCalendar, lastUpdateby, Division_ID);
            allCustomers.add(customerResult);
        }
        return allCustomers;
    }

    /**
     * This is the method to add a customer. The method executes an SQL query to insert a new Customer into the Customers table of the database using the data inputted on the CustomersAdd screen.
     * @param customerName the name of the customer to add
     * @param customerAddress the address of the customer to add
     * @param postalCode the postal code of the customer to add
     * @param customerPhone the phone number of the customer to add
     * @param createDate the create date of the customer to add
     * @param createdBy the user creating the customer to add
     * @param lastUpdate the last updated time of the customer to add
     * @param lastUpdateBy the last user to update the customer to add
     * @param Division_ID the division ID of the customer to add
     */
    public static void addCustomer(String customerName, String customerAddress, String postalCode, String customerPhone, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy, int Division_ID) {
        try {
            String sqlca = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlca);

            psti.setString(1, customerName);
            psti.setString(2, customerAddress);
            psti.setString(3, postalCode);
            psti.setString(4, customerPhone);
            psti.setTimestamp(5, createDate);
            psti.setString(6, createdBy);
            psti.setTimestamp(7, lastUpdate);
            psti.setString(8, lastUpdateBy);
            psti.setInt(9, Division_ID);

            psti.execute();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * This is the method to modify a Customer. The method executes an SQL query to update an existing Customer in the Customers table of the database using the data inputted on the CustomersModify screen.
     * @param customerID the ID of the customer to modify
     * @param customerName the name of the customer to modify
     * @param customerAddress the address of the customer to modify
     * @param postalCode the postal code of the customer to modify
     * @param customerPhone the phone number of the customer to modify
     * @param lastUpdate the last updated time of the customer to modify
     * @param lastUpdateBy the last user to update the customer to modify
     * @param Division_ID the division ID of the customer to modify
     */
    public static void modifyCustomer(int customerID, String customerName, String customerAddress, String postalCode, String customerPhone, Timestamp lastUpdate, String lastUpdateBy, int Division_ID) {
        try {
            String sqlcm = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlcm);

            psti.setString(1, customerName);
            psti.setString(2, customerAddress);
            psti.setString(3, postalCode);
            psti.setString(4, customerPhone);
            psti.setTimestamp(5, lastUpdate);
            psti.setString(6, lastUpdateBy);
            psti.setInt(7, Division_ID);
            psti.setInt(8, customerID);

            psti.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to delete a customer. The method executes an SQL query to delete an existing Customer in the Customers table of the database with the selected Customer ID.
     * @param customerID the ID of the customer to delete
     */
    public static void deleteCustomer(int customerID) {
        try {
            String sqlcd = "DELETE FROM customers WHERE Customer_ID = ?";
            PreparedStatement psti = JDBC.connection.prepareStatement(sqlcd);

            psti.setInt(1, customerID);

            psti.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This is the method to get a Customer object from a Customer ID. The method executes an SQL query to select the customer from the Customers table with the selected Customer ID.
     * @param customerID the customer ID to get the customer object for
     * @return the Customer getCustomerFromCustomerIDResult
     * @throws SQLException
     */
    public static Customer getCustomerFromCustomerID(int customerID) throws SQLException {
        String sqlStatement = "select * from customers where Customer_ID = " + customerID;
        Query.makeQuery(sqlStatement);
        Customer getCustomerFromCustomerIDResult;
        ResultSet result = Query.getResult();
        while (result.next()) {
            int Customer_ID = result.getInt("Customer_ID");
            String Customer_Name = result.getString("Customer_Name");
            String Address = result.getString("Address");
            String Postal_Code = result.getString("Postal_Code");
            String Phone = result.getString("Phone");
            Timestamp createDate=result.getTimestamp("Create_Date");
            LocalDateTime createDateCalendar=createDate.toLocalDateTime();
            String Created_By = result.getString("Created_By");
            Timestamp lastUpdate=result.getTimestamp("Last_Update");
            LocalDateTime lastUpdateCalendar=lastUpdate.toLocalDateTime();
            String Last_Updated_By = result.getString("Last_Updated_By");
            int Division_ID = result.getInt("Division_ID");

            getCustomerFromCustomerIDResult = new Customer(Customer_ID, Customer_Name, Address, Postal_Code, Phone, createDateCalendar, Created_By, lastUpdateCalendar, Last_Updated_By, Division_ID);
            return getCustomerFromCustomerIDResult;
        }
        return null;
    }

    /**
     * This is the method to count the number of customers with the selected Country ID. The method executes an SQL query to count the numbers of customers where the Division ID is found by using the selected Country ID and then matching the Country ID to its range of FirstLevelDivision IDs.
     * @param Country_ID the country ID of which to count customers for
     * @return the number of customers with the selected Country ID
     * @throws SQLException
     */
    public static int countCustomers(int Country_ID) throws SQLException {
        String sqlStatement = "SELECT COUNT(*) AS customerCountry FROM customers WHERE Division_ID IN (SELECT Division_ID FROM First_Level_Divisions WHERE Country_ID = " + Country_ID + ")";
        Query.makeQuery(sqlStatement);
        int countCustomersResult = 0;
        ResultSet result = Query.getResult();
        while(result.next()) {
            countCustomersResult = result.getInt("customerCountry");

            return countCustomersResult;
        }
        return countCustomersResult;
    }
}
