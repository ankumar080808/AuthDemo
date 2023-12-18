package ONDC.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Example {
    public static void main(String[] args) {
        // Get the current LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.now();

        // Generate a random number between 1000 and 9999
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;

        // Combine the timestamp and random number to create the request number
        String requestNumber = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(dateTime) + randomNumber;

        // Print the request number
        System.out.println(requestNumber);
    }
}
