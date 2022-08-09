package helper;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class FileLogger {
    public static void logLogin(String username, Boolean successful) throws IOException {
        String fileName = "./login_activity.txt";
        FileWriter fw = new FileWriter(fileName, true);
        fw.write("Login Attempt: " + new Timestamp(System.currentTimeMillis()) + "\n" +
                   "Username attempted: " + username + "\n" +
                    "Successful? " + successful + "\n\n");
        fw.close();
    }
}
