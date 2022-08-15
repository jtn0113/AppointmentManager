package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class customerDAO {
    /**
     * Returns observable list of all customers
     * @return customersObservableList
     * @throws SQLException
     */
    public static ObservableList<Customers> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM customers";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Customers> customersObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");
            Customers customer = new Customers(id, name, address, postalCode, phone, divisionId);
            customersObservableList.add(customer);
        }
        return customersObservableList;
    }

    /**
     * Returns all customer id's
     * @return customersObservableList
     * @throws SQLException
     */
    public static ObservableList<Integer> getAllCustomerId() throws SQLException {
        String query = "SELECT Customer_ID FROM customers";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Integer> allCustomerId = FXCollections.observableArrayList();

        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            allCustomerId.add(customerID);
        }
        return allCustomerId;
    }

    /**
     * Returns all customer names
     * @return customersObservableList
     * @throws SQLException
     */
    public static ObservableList<String> getAllCustomerNames() throws SQLException {
        String query = "SELECT Customer_Name FROM customers";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<String> allCustomerNames = FXCollections.observableArrayList();

        while (rs.next()) {
            String customerName = rs.getString("Customer_Name");

            allCustomerNames.add(customerName);
        }
        return allCustomerNames;
    }

    /**
     * Returns customer id from customer name
     * @param customerName
     * @return customerId
     * @throws SQLException
     */
    public static int customerNameToId(String customerName) throws SQLException {
        String query = "SELECT Customer_ID FROM customers WHERE Customer_Name = " + "'" + customerName + "'";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        rs.next();
        int customerId = rs.getInt("Customer_ID");
        return customerId;
    }
}

