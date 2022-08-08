package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryConversions {

    public static int divisionNameToId(String divisionName) throws SQLException {
        String query = "SELECT Division_ID FROM first_level_divisions WHERE Division = " + "'" + divisionName + "'";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        rs.next();
        int divisionID = rs.getInt("Division_ID");

        return divisionID;
    }

}
