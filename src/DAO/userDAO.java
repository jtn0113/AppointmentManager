package DAO;

import helper.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
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
}
