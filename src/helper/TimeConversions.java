package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeConversions {

    /**
     * Combines date and time from user input
     * @param date
     * @param time
     * @return
     */
    public static LocalDateTime dateTime(LocalDate date, String time) {
        try {
            LocalTime newTime = LocalTime.parse(time);
            LocalDateTime dateTime = LocalDateTime.of(date, newTime);

            return dateTime;
        } catch (DateTimeParseException e) {
            Alerts.errorAlert("Enter Valid Date");
        }
        return null;
    }

    /**
     * Converts UTC to Local Time
     * @param utcDateTime
     * @return
     */
    public static LocalDateTime utcToLocal(LocalDateTime utcDateTime) {
        ZonedDateTime localTime = utcDateTime.atZone(ZoneId.of("UTC"));
        LocalDateTime localTimeLDT = LocalDateTime.ofInstant(localTime.toInstant(), ZoneId.systemDefault());
        return localTimeLDT;
    }

    /**
     * Converts Local to UTC Time
     * @param localDateTime
     * @return
     */
    public static LocalDateTime localToUtc(LocalDateTime localDateTime) {
        ZonedDateTime utcTime = localDateTime.atZone(ZoneId.of(String.valueOf(ZoneId.systemDefault())));
        LocalDateTime utcTimeLDT = LocalDateTime.ofInstant(utcTime.toInstant(), ZoneId.of("UTC"));
        return utcTimeLDT;
    }

    /**
     * Converts UTC to EST Time
     * @param utcDateTime
     * @return
     */
    public static LocalDateTime utcToEst(LocalDateTime utcDateTime) {
        ZonedDateTime estTime = utcDateTime.atZone(ZoneId.of("UTC"));
        LocalDateTime estTimeLDT = LocalDateTime.ofInstant(estTime.toInstant(), ZoneId.of("US/Eastern"));
        return estTimeLDT;
    }
}
