package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class contactsDAO {

    /**
     * @return allContactsObservableList
     * @throws SQLException
     */
    public static ObservableList<Contacts> getAllContacts() throws SQLException {
        String query = "SELECT * FROM contacts";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Contacts> allContactsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int contact_id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            Contacts contact = new Contacts(contact_id, name, email);
            allContactsObservableList.add(contact);
        }
        return allContactsObservableList;
    }

    public static ObservableList<String> getAllContactNames() throws SQLException {
        String query = "SELECT Contact_Name FROM contacts";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<String> allContactNames = FXCollections.observableArrayList();

        while (rs.next()) {
            String contactName = rs.getString("Contact_Name");

            allContactNames.add(contactName);
        }
        return allContactNames;
    }

    /**
     * @param contactID
     * @return contactName
     * @throws SQLException
     */
    public static String contactIdToName(int contactID) throws SQLException {
        String query = "SELECT Contact_Name FROM contacts WHERE Contact_ID = " + "'" + contactID + "'";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        rs.next();
        String contactName = rs.getString("Contact_Name");
        return contactName;
    }

    /**
     *
     * @param contactName
     * @return contactId
     * @throws SQLException
     */
    public static int contactNameToId(String contactName) throws SQLException {
        String query = "SELECT Contact_ID FROM contacts WHERE Contact_Name = " + "'" + contactName + "'";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        rs.next();
        int contactId = rs.getInt("Contact_ID");
        return contactId;
    }
}
