package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryConversions {

    /**
     * Takes division name and converts it to division ID
     * @param divisionName
     * @return
     * @throws SQLException
     */
    public static int divisionNameToId(String divisionName) throws SQLException {
        String query = "SELECT Division_ID FROM first_level_divisions WHERE Division = " + "'" + divisionName + "'";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        rs.next();
        int divisionID = rs.getInt("Division_ID");

        return divisionID;
    }

    /**
     * Takes division ID and converts it to division name
     * @param divisionId
     * @return
     * @throws SQLException
     */
    public static String divisionIdToName(int divisionId) throws SQLException {
        String query = "SELECT Division FROM first_level_divisions WHERE Division_ID = " + "'" + divisionId + "'";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        rs.next();
        String divisionName = rs.getString("Division");
        return divisionName;
    }

    /**
     * Takes division ID and converts it to country ID
     * @param divisionId
     * @return
     * @throws SQLException
     */
    public static int divisionIdToCountryId(int divisionId) throws SQLException {
        String query = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = " + "'" + divisionId + "'";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        rs.next();
        int countryID = rs.getInt("Country_ID");

        return countryID;
    }

}
