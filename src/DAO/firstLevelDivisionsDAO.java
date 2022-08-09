package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class firstLevelDivisionsDAO {
    /**
     * Returns observable list of all first level divisions
     * @return
     * @throws SQLException
     */
    public static ObservableList<Divisions> getAllDivisions() throws SQLException {
        String query = "SELECT * FROM first_level_divisions";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Divisions> allDivisionsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int division_id = rs.getInt("Division_ID");
            String name = rs.getString("Division");
            int country_id = rs.getInt("Country_ID");

            Divisions division = new Divisions(division_id, name, country_id);
            allDivisionsObservableList.add(division);
        }
        return allDivisionsObservableList;
    }

    /**
     * Returns observable list of all divisions from country US
     * @return
     * @throws SQLException
     */
    public static ObservableList<String> getUsDivisions() throws SQLException {
        String query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 1";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<String> usDivisionsOL = FXCollections.observableArrayList();

        while (rs.next()) {
            String division = rs.getString("Division");
            usDivisionsOL.add(division);
        }
        return usDivisionsOL;
    }

    /**
     * Returns observable list of all divisions from country UK
     * @return
     * @throws SQLException
     */
    public static ObservableList<String> getUkDivisions() throws SQLException {
        String query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 2";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<String> ukDivisionsOL = FXCollections.observableArrayList();

        while (rs.next()) {
            String division = rs.getString("Division");
            ukDivisionsOL.add(division);
        }
        return ukDivisionsOL;
    }

    /**
     * Returns observable list of all divisions from country Canada
     * @return
     * @throws SQLException
     */
    public static ObservableList<String> getCanadaDivisions() throws SQLException {
        String query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 3";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<String> canadaDivisionsOL = FXCollections.observableArrayList();

        while (rs.next()) {
            String division = rs.getString("Division");
            canadaDivisionsOL.add(division);
        }
        return canadaDivisionsOL;
    }

}
