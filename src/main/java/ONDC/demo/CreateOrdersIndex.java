package ONDC.demo;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;

import org.apache.http.HttpHost;

import java.io.IOException;

public class CreateOrdersIndex {
    public static void main(String[] args) {
        // Initialize the Elasticsearch client
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );

        // Create Index
        CreateIndexRequest request = new CreateIndexRequest("orders"); 
        request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
        request.mapping("field1", "type=keyword");
       // request.includeTypeName(true); // This is likely causing your issue, remove this line

        request.source(
                "{\n" +
                        "  \"mappings\": {\n" +
                        "    \"properties\": {\n" +
                        "      \"transaction_id\": {\"type\": \"keyword\"},\n" +
                        "      \"timestamp\": {\"type\": \"date\"}\n" +
                        "    }\n" +
                        "  }\n" +
                        "}", 
                XContentType.JSON
        );

        try {
            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
            boolean acknowledged = createIndexResponse.isAcknowledged();
            boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();

            System.out.println("Index has been created. Acknowledged: " + acknowledged + ", Shards Acknowledged: " + shardsAcknowledged);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
