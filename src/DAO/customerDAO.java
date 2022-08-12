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
     * @return
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
}

