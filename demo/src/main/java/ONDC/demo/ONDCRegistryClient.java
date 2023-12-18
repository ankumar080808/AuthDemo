package ONDC.demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ONDCRegistryClient {

    private static final String SUBSCRIBE_API_URL = "https://virtserver.swaggerhub.com/ONDC/ONDC-Registry-Onboarding/2.0.5/subscribe";

    public static void main(String[] args)
    {
        String requestBody = "{\"country\":\"IND\",\"city\":\"*\",\"type\":\"BPP\",\"subscriber_id\":\"https://www.vinculumgroup.com\",\"subscriber_url\":\"https://ondc.vineretail.com\",\"domain\":\"nic2004:52110\",\"signing_public_key\":\"1iC01Mc4uRzpe6BZ6zND+YkiOhFTVUpNhCjbBifLoiE=\",\"encr_public_key\":\"SFBW1Gzm8lccuVELluelR3B12kDfUUkkfJB7znbAqAQ= \",\"created\":\"2022-07-22T05:56:52.470Z\",\"valid_from\":\"2022-07-22T05:56:52.470Z\",\"valid_until\":\"2032-11-09T05:56:52.470Z\",\"updated\":\"2022-07-22T13:15:58.349Z\"}";
        
        try {
            URL url = new URL(SUBSCRIBE_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Write the request body to the connection output stream
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(requestBody);
            wr.flush();
            wr.close();

            // Check the response code
            int responseCode = conn.getResponseCode();
            System.out.println("Response code: " + responseCode);

            // Read the response body
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("Response body: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
