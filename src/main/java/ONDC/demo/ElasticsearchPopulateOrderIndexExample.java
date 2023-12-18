package ONDC.demo;


import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticsearchPopulateOrderIndexExample {
    private static final String INDEX_NAME = "orders";

    public static void main(String[] args) {
        RestHighLevelClient client = null;

        try {
            // Set up the Elasticsearch client
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http")));

            // Populate the fields for the index
            populateIndex(client, INDEX_NAME);

            System.out.println("Index populated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void populateIndex(RestHighLevelClient client, String indexName) throws IOException {
        for (int i = 1; i <= 1; i++) {
            String documentId = String.valueOf(i);
            String documentJson = "{\n" +
                    //"  \"transaction_id\": \"123" + i + "\",\n" +
                    //"  \"bap_id\": \"ABC123" + i + "\",\n" +
                    //"  \"bpp_id\": \"456" + i + "\",\n" +
                   // "  \"timestamp\": \"456" + i + "\",\n" +
                    "  \"object\": {\n" +
                    "    \"order\": {\n" +
                    "      \"provider\": {\n" +
                    "        \"id\":\"P1\",\n" +
                    "        \"locations\":[\n" +
                    "          {\n" +
                    "            \"id\":\"L1\"\n" +
                    "          }\n" +
                    "        ]\n" +
                    "      },\n" +
                    "      \"items\":[\n" +
                    "        {\n" +
                    "          \"id\":\"I1\",\n" +
                    "          \"fulfillment_id\":\"F1\",\n" +
                    "          \"quantity\":{\n" +
                    "            \"count\":1\n" +
                    "          }\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"billing\":{\n" +
                    "        \"name\":\"ONDC buyer\",\n" +
                    "        \"address\":{\n" +
                    "          \"name\":\"My House #\",\n" +
                    "          \"door\":\"B005 aaspire heights\",\n" +
                    "          \"building\":\"My building name\",\n" +
                    "          \"locality\":\"My Street name\",\n" +
                    "          \"city\":\"Bengaluru\",\n" +
                    "          \"state\":\"Karnataka\",\n" +
                    "          \"country\":\"IND\",\n" +
                    "          \"area_code\":\"560037\"\n" +
                    "        },\n" +
                    "        \"tax_number\":\"XXXXXXXXXXXXXXX\",\n" +
                    "        \"email\":\"nobody@nomail.com\",\n" +
                    "        \"phone\":\"9886098860\",\n" +
                    "        \"created_at\":\"2023-02-03T09:00:00.000Z\",\n" +
                    "        \"updated_at\":\"2023-02-03T09:00:00.000Z\"\n" +
                    "      },\n" +
                    "      \"fulfillments\":[\n" +
                    "        {\n" +
                    "          \"id\":\"F1\",\n" +
                    "          \"type\":\"Delivery\",\n" +
                    "          \"provider_id\":\"sellerapp.com/logistics\",\n" +
                    "          \"end\":{\n" +
                    "            \"location\":{\n" +
                    "              \"gps\":\"12.9492953,77.7019878\",\n" +
                    "              \"address\":{\n" +
                    "                \"name\":\"My House #\",\n" +
                    "                \"building\":\"My building name\",\n" +
                    "                \"locality\":\"My Street name\",\n" +
                    "                \"city\":\"Bengaluru\",\n" +
                    "                \"state\":\"Karnataka\",\n" +
                    "                \"country\":\"IND\",\n" +
                    "                \"area_code\":\"560037\"\n" +
                    "              }\n" +
                    "            },\n" +
                    "            \"contact\":{\n" +
                    "              \"phone\":\"9886098860\"\n" +
                    "            }\n" +
                    "          }\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";
            
            IndexRequest request = new IndexRequest(indexName);
            request.id(documentId);
            request.source(documentJson, XContentType.JSON);
            
            try 
            {
	            client = new RestHighLevelClient(
	                    RestClient.builder(new HttpHost("localhost", 9200, "http")));
	            RequestOptions options = RequestOptions.DEFAULT;
	            client.index(request, options);
            }catch(Exception e) {
            	e.printStackTrace();
            }
        }
    }
}


