package ONDC.demo;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.apache.http.HttpHost;

import java.io.IOException;

public class ElasticSearchIndexCreator {

    public static void createIndexWithMapping(RestHighLevelClient client) throws IOException {

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("transaction_id");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();
                builder.startObject("bap_id");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();
                builder.startObject("bpp_id");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();
                builder.startObject("timestamp");
                {
                    builder.field("type", "date");
                }
                builder.endObject();
                builder.startObject("object");
                {
                    builder.field("type", "object");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();

        String mappingJson = Strings.toString(builder);

        CreateIndexRequest request = new CreateIndexRequest("orders");
        request.mapping(mappingJson, XContentType.JSON);

        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();

        System.out.println("Index creation acknowledged: " + acknowledged);
        System.out.println("Shards acknowledged: " + shardsAcknowledged);
    }

    public static void main(String[] args) {
        try (RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")))) {
            createIndexWithMapping(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
