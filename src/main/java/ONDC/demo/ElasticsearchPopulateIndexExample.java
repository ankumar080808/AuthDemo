package ONDC.demo;


import java.io.IOException;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class ElasticsearchPopulateIndexExample {
    private static final String INDEX_NAME = "products";

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
        for (int i = 1; i <= 50; i++) {
            String documentId = String.valueOf(i);
            String documentJson = "{\n" +
                    "  \"item_id\": \"123" + i + "\",\n" +
                    "  \"item_code\": \"ABC123" + i + "\",\n" +
                    "  \"item_category_id\": \"456" + i + "\",\n" +
                    "  \"message\": {\n" +
                    "    \"intent\": {\n" +
                    "      \"item\": {\n" +
                    "        \"descriptor\": {\n" +
                    "          \"name\": \"Product Name " + i + "\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"category\": {\n" +
                    "        \"id\": \"789" + i + "\"\n" +
                    "      },\n" +
                    "      \"fulfillment\": {\n" +
                    "        \"type\": \"Type " + i + "\",\n" +
                    "        \"end\": {\n" +
                    "          \"location\": {\n" +
                    "            \"gps\": \"40.7128,-74.0060\",\n" +
                    "            \"address\": {\n" +
                    "              \"area_code\": \"12345\"\n" +
                    "            }\n" +
                    "          }\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"payment\": {\n" +
                    "        \"@ondc/org/buyer_app_finder_fee_type\": \"Type " + i + "\",\n" +
                    "        \"@ondc/org/buyer_app_finder_fee_amount\": 100\n" +
                    "      }\n" +
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


