package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {

    /**
     *
     * @param username
     * @param password
     * @return boolean
     * @throws SQLException
     */
    public static boolean loginCorrect(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE User_Name = " + "'" + username + "'" + "AND Password = " + "'" + password + "'";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return all user ID's
     * @throws SQLException
     */
    public static ObservableList<Integer> getAllUserId() throws SQLException {
        String query = "SELECT User_ID FROM users";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Integer> allUserId = FXCollections.observableArrayList();

        while (rs.next()) {
            int userID = rs.getInt("User_ID");
            allUserId.add(userID);
        }
        return allUserId;
    }
}
