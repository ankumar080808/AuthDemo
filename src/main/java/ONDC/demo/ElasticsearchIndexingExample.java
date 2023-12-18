package ONDC.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class ElasticsearchIndexingExample {
    public static void main(String[] args) {
        // Create a RestClient instance
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();

        try {
            // Index 5 dummy data entries
            for (int i = 1; i <= 5; i++) {
                String documentId = String.valueOf(i);
                String jsonDocument = "{\n" +
                        "  \"item\": \"I" + i + "\",\n" +
                        "  \"item_descriptor\": \"Atta\",\n" +
                        "  \"city\": \"std:080" + i + "\",\n" +
                        "  \"domain\": \"ONDC:RET10" + i + "\",\n" +
                        "  \"category\": \"Packaged Commodities\",\n" +
                        "  \"area_code\": \"560001" + i + "\",\n" +
                        "  \"object\": {\n" +
                        "    \"id\": \"I" + i + "\",\n" +
                        "    \"descriptor\": {\n" +
                        "      \"name\": \"Atta\",\n" +
                        "      \"code\": \"1:XXXXXXXXXXXXX\",\n" +
                        "      \"symbol\": \"https://abc.com/images/07.png\",\n" +
                        "      \"short_desc\": \"Ashirwad Atta 5kg\",\n" +
                        "      \"long_desc\": \"Ashirwad Atta 5kg\",\n" +
                        "      \"images\": [\n" +
                        "        \"https://abc.com/images/07.png\"\n" +
                        "      ]\n" +
                        "    },\n" +
                        "    \"quantity\": {\n" +
                        "      \"available\": {\n" +
                        "        \"count\": \"1\"\n" +
                        "      },\n" +
                        "      \"maximum\": {\n" +
                        "        \"count\": \"2\"\n" +
                        "      }\n" +
                        "    },\n" +
                        "    \"price\": {\n" +
                        "      \"currency\": \"INR\",\n" +
                        "      \"value\": \"170.0\",\n" +
                        "      \"maximum_value\": \"180.0\"\n" +
                        "    },\n" +
                        "    \"category_id\": \"Packaged Commodities\",\n" +
                        "    \"fulfillment_id\": \"1\",\n" +
                        "    \"location_id\": \"L" + i + "\",\n" +
                        "    \"recommended\": true,\n" +
                        "    \"@ondc/org/returnable\": true,\n" +
                        "    \"@ondc/org/cancellable\": true,\n" +
                        "    \"@ondc/org/return_window\": \"P7D\",\n" +
                        "    \"@ondc/org/seller_pickup_return\": false,\n" +
                        "    \"@ondc/org/time_to_ship\": \"PT45M\",\n" +
                        "    \"@ondc/org/available_on_cod\": false,\n" +
                        "    \"@ondc/org/contact_details_consumer_care\": \"Ramesh,ramesh@abc.com,18004254444\",\n" +
                        "    \"@ondc/org/statutory_reqs_packaged_commodities\": {\n" +
                        "      \"manufacturer_or_packer_name\": \"ITC\",\n" +
                        "      \"manufacturer_or_packer_address\": \"ITC Quality Care Cell,P.O Box No.592,Bangalore-560005\",\n" +
                        "      \"common_or_generic_name_of_commodity\": \"Ashirwad Atta\",\n" +
                        "      \"net_quantity_or_measure_of_commodity_in_pkg\": \"5kg\",\n" +
                        "      \"month_year_of_manufacture_packing_import\": \"01/2023\",\n" +
                        "      \"imported_product_country_of_origin\": \"IND\"\n" +
                        "    },\n" +
                        "    \"@ondc/org/statutory_reqs_prepackaged_food\": {\n" +
                        "      \"nutritional_info\": \"Energy(KCal)-(per 100kg) 420,(per serving 50g)250;Protein(g)-(per 100kg) 12,(per serving 50g) 6\",\n" +
                        "      \"additives_info\": \"Preservatives,Artificial Colours\",\n" +
                        "      \"brand_owner_FSSAI_license_no\": \"12345678901234\",\n" +
                        "      \"other_FSSAI_license_no\": \"12345678901234\",\n" +
                        "      \"importer_FSSAI_license_no\": \"12345678901234\",\n" +
                        "      \"imported_product_country_of_origin\": \"IND\"\n" +
                        "    },\n" +
                        "    \"@ondc/org/mandatory_reqs_veggies_fruits\": {\n" +
                        "      \"net_quantity\": \"100g\"\n" +
                        "    },\n" +
                        "    \"tags\": {\n" +
                        "      \"veg\": \"yes\",\n" +
                        "      \"non_veg\": \"no\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}";

                // Create an HTTP PUT request
                Request request = new Request("PUT", "/products/_doc/" + documentId);
                request.setEntity(new NStringEntity(jsonDocument, ContentType.APPLICATION_JSON));

                // Send the request and retrieve the response
                Response response = restClient.performRequest(request);

                // Get the status code and response body
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                // Handle the response
                if (statusCode == 201) {
                    System.out.println("Document indexed successfully! Response: " + responseBody);
                } else {
                    System.out.println("Failed to index document! Response: " + responseBody);
                }
            }
        } catch (IOException e) {
            System.out.println("Error indexing documents: " + e.getMessage());
        } finally {
            // Close the RestClient
            try {
                restClient.close();
            } catch (IOException e) {
                System.out.println("Error closing the RestClient: " + e.getMessage());
            }
        }
    }
}


