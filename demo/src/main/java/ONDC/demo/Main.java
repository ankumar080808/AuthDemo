package ONDC.demo;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        // Get the current LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.now();

        // Convert the LocalDateTime to an epoch timestamp in milliseconds
        long timestamp = dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();

        // Convert the timestamp to RFC3339 format
        String rfc3339Format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .withZone(ZoneOffset.UTC).format(dateTime);
               

        // Print the timestamp in RFC3339 format
        System.out.println(rfc3339Format);
    }
}
