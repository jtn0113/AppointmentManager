package DAO;

import helper.JDBC;
import helper.TimeConversions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.MonthCount;
import model.TypeCount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class appointmentsDAO {

    /**
     * Returns all appointments
     * @return appointmentsObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllAppointments() throws SQLException {
        String query = "SELECT * FROM appointments";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = TimeConversions.utcToLocal(rs.getTimestamp("Start").toLocalDateTime());
            LocalDateTime end = TimeConversions.utcToLocal(rs.getTimestamp("End").toLocalDateTime());
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String contactName = contactsDAO.contactIdToName(rs.getInt("Contact_ID"));
            Appointments appointment = new Appointments(id, title, description, location, type, start, end, customerID, userID, contactName);
            appointmentsObservableList.add(appointment);
        }
        return appointmentsObservableList;
    }

    /**
     * Returns current week appointments
     * @return appointmentsObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointments> getWeekAppointments() throws SQLException {
        String query = "SELECT * FROM appointments WHERE WEEK(NOW())=WEEK(Start)";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = TimeConversions.utcToLocal(rs.getTimestamp("Start").toLocalDateTime());
            LocalDateTime end = TimeConversions.utcToLocal(rs.getTimestamp("End").toLocalDateTime());
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String contactName = contactsDAO.contactIdToName(rs.getInt("Contact_ID"));
            Appointments appointment = new Appointments(id, title, description, location, type, start, end, customerID, userID, contactName);
            appointmentsObservableList.add(appointment);

        }
        return appointmentsObservableList;
    }

    /**
     * Returns appointments for current month
     * @return appointmentsObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointments> getMonthAppointments() throws SQLException {
        String query = "SELECT * FROM appointments WHERE MONTH(NOW())=MONTH(Start)";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = TimeConversions.utcToLocal(rs.getTimestamp("Start").toLocalDateTime());
            LocalDateTime end = TimeConversions.utcToLocal(rs.getTimestamp("End").toLocalDateTime());
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String contactName = contactsDAO.contactIdToName(rs.getInt("Contact_ID"));
            Appointments appointment = new Appointments(id, title, description, location, type, start, end, customerID, userID, contactName);
            appointmentsObservableList.add(appointment);

        }
        return appointmentsObservableList;
    }

    /**
     * Returns appointments for selected contact
     * @param contactID
     * @return appointmentsObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllContactsAppointments(int contactID) throws SQLException {
        String query = "SELECT * FROM appointments WHERE Contact_ID = " + contactID;

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = TimeConversions.utcToLocal(rs.getTimestamp("Start").toLocalDateTime());
            LocalDateTime end = TimeConversions.utcToLocal(rs.getTimestamp("End").toLocalDateTime());
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String contactName = contactsDAO.contactIdToName(rs.getInt("Contact_ID"));
            Appointments appointment = new Appointments(id, title, description, location, type, start, end, customerID, userID, contactName);
            appointmentsObservableList.add(appointment);

        }
        return appointmentsObservableList;
    }

    /**
     * Returns appointments for selected customer
     * @param customersID
     * @return appointmentsObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointments> getAllCustomerAppointments(int customersID) throws SQLException {
        String query = "SELECT * FROM appointments WHERE Customer_ID = " + customersID;

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointments> appointmentsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime start = TimeConversions.utcToLocal(rs.getTimestamp("Start").toLocalDateTime());
            LocalDateTime end = TimeConversions.utcToLocal(rs.getTimestamp("End").toLocalDateTime());
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String contactName = contactsDAO.contactIdToName(rs.getInt("Contact_ID"));
            Appointments appointment = new Appointments(id, title, description, location, type, start, end, customerID, userID, contactName);
            appointmentsObservableList.add(appointment);

        }
        return appointmentsObservableList;
    }

    /**
     *
     * @param customerID
     * @return boolean
     * @throws SQLException
     */
    public static boolean customerHasAppointments(int customerID) throws SQLException {
        String query = "SELECT * FROM appointments WHERE Customer_ID = " + customerID;

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
     * @param customersID
     * @param appointmentID
     * @param startTime
     * @param endTime
     * @return boolean
     * @throws SQLException
     */
    public static boolean customerOverlappingAppointmentsUpdate(int customersID, String appointmentID, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        String query = "SELECT * FROM appointments WHERE Customer_ID = " + customersID + " AND Appointment_ID != " + Integer.parseInt(appointmentID);
        startTime = TimeConversions.utcToLocal(startTime);
        endTime = TimeConversions.utcToLocal(endTime);

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            LocalDateTime start = TimeConversions.utcToLocal(rs.getTimestamp("Start").toLocalDateTime());
            LocalDateTime end = TimeConversions.utcToLocal(rs.getTimestamp("End").toLocalDateTime());
            if ((startTime.isAfter(start) && startTime.isBefore(end)) || (endTime.isAfter(start) && endTime.isBefore(end)) || (startTime.isBefore(start) && endTime.isAfter(end)) || (startTime.isEqual(start)) || (endTime.isEqual(end))) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param customersID
     * @param startTime
     * @param endTime
     * @return boolean
     * @throws SQLException
     */
    public static boolean customerOverlappingAppointments(int customersID, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        String query = "SELECT * FROM appointments WHERE Customer_ID = " + customersID;
        startTime = TimeConversions.utcToLocal(startTime);
        endTime = TimeConversions.utcToLocal(endTime);

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            LocalDateTime start = TimeConversions.utcToLocal(rs.getTimestamp("Start").toLocalDateTime());
            LocalDateTime end = TimeConversions.utcToLocal(rs.getTimestamp("End").toLocalDateTime());
            if ((startTime.isAfter(start) && startTime.isBefore(end)) || (endTime.isAfter(start) && endTime.isBefore(end)) || (startTime.isBefore(start) && endTime.isAfter(end)) || (startTime.isEqual(start)) || (endTime.isEqual(end))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all type of appointments with count
     * @return appointmentsObservableList
     * @throws SQLException
     */
    public static ObservableList<TypeCount> getAllTypes() throws SQLException {
        String query = "SELECT Type, COUNT(Type) FROM appointments GROUP BY Type";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<TypeCount> appointmentsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            String type = rs.getString("Type");
            int count = rs.getInt("COUNT(Type)");

            TypeCount typeCount = new TypeCount(type, count);
            appointmentsObservableList.add(typeCount);
        }
        return appointmentsObservableList;
    }

    /**
     * Returns all months of appointments with count
     * @return appointmentsObservableList
     * @throws SQLException
     */
    public static ObservableList<MonthCount> getAllMonths() throws SQLException {
        String query = "SELECT EXTRACT(month FROM Start) \"Month\", COUNT(*) FROM appointments GROUP BY EXTRACT(month FROM Start)";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        ObservableList<MonthCount> appointmentsObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int month = rs.getInt("Month");
            int count = rs.getInt("COUNT(*)");

            MonthCount monthCount = new MonthCount(Month.of(month), count);
            appointmentsObservableList.add(monthCount);
        }
        return appointmentsObservableList;
    }

}
