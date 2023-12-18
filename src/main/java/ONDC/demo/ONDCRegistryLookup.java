package ONDC.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ONDCRegistryLookup {

    
    private static final String LOOKUP_ENDPOINT = "https://pilot-gateway-1.beckn.nsdl.co.in/lookup";
   
    public static void main(String[] args) throws IOException
    {
        
       // String jsonSignature="{\"subscriber_id\":\"vinculumgroup.com\",\"type\":\"BPP\",\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"*\"}";
        ////String jsonSignature = "vinculumgroup.com|292|ed25519";
    	String jsonSignature="{\"subscriber_id\":\"*\",\"type\":\"BPP\",\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"*\"}";
    	
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(LOOKUP_ENDPOINT);
        
        System.out.println("jsonSignature--- "+jsonSignature);
       
        StringEntity params = new StringEntity(jsonSignature, ContentType.APPLICATION_JSON);
        request.setEntity(params);
       
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        System.out.println("Lookup Response :: "+responseString);
    }
}
