package ONDC.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.math.ec.rfc8032.Ed25519;

import com.fasterxml.jackson.databind.ObjectMapper;


public class OndcVLookup {
	
	private static final String VLOOKUP_ENDPOINT = "https://pilot-gateway-1.beckn.nsdl.co.in/ondc/vlookup";
	


	//private static final String  privateKey="hJ5sCmbe7s9Wateq6QAdBGloVSkLuLHWOXcRkzrMcVLthFldV4gnT9Vrnq9iDNPVSKuDqaercVjQwFlj0Ml+3Q==s";
    
	private static final String  privateKey="lTKY44iTfKqtxDdiWgDpMAgSQNKkVVhV49/6InrFCjfWILTUxzi5HOl7oFnrM0P5iSI6EVNVSk2EKNsGJ8uiIQ==";
	
	private static final  AppType appType = AppType.buyerApp;

	public static void main(String[] args) {
	
        vlookUp();
      
	}
	
   public static void vlookUp()
   {
	   VLookup lookup=initialize();
	   
	   String json=convertToJson(lookup);
	   
	   System.out.println(json);
	   
	   HttpClient httpClient=HttpClientBuilder.create().build();
       HttpPost request=new HttpPost(VLOOKUP_ENDPOINT);
	   StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
       request.setEntity(params);
      
       
       HttpResponse response;
       String responseString=null;
		
       try
		{
		  response = httpClient.execute(request);
		  HttpEntity entity = response.getEntity();
		  responseString = EntityUtils.toString(entity, "UTF-8");
		    
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
       System.out.println("V Lookup Response :: "+responseString);
	   
   }
   
   @SuppressWarnings("unused")
   private static VLookup initialize()
   {
	   VLookup lookup=new VLookup();
	   
	   
	   lookup.setSenderSubscriberId("vinculumgroup.com");  
	   lookup.setTimestamp(getCurrentTimeStamp());  
	   lookup.setRequestId(generateRequestNumber());
	   
	   SearchParameters params=new SearchParameters();
	   params.setSubscriberId("buyer-app.ondc.org");
	   params.setType(appType);
	   params.setCity("std:080");
	   params.setCountry("IND");
	   params.setDomain("nic2004:52110");
	   
	   lookup.setSearchParameters(params);
	   lookup.setSignature(generateSignature(lookup));

	   return lookup;

   }
   
   private static String getCurrentTimeStamp()
   {
	String rfc3339Format="" ;
	try 
	{
		 LocalDateTime dateTime = LocalDateTime.now();

	        // Convert the LocalDateTime to an epoch timestamp in milliseconds
	     long timestamp = dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();

	        // Convert the timestamp to RFC3339 format
	     rfc3339Format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	                .withZone(ZoneOffset.UTC).format(dateTime);        
	    System.out.println(rfc3339Format);
	 }catch(Exception e) {  
	   }
   	  return rfc3339Format;
   }
   
  @SuppressWarnings("unused")
  private static String generateSignature(VLookup lookup)
   {
	   
	   // domain|country|type|city|subscriber_id 
	   String domain=lookup.getSearchParameters().getDomain();
	   String country=lookup.getSearchParameters().getCountry();
	   Enum type=lookup.getSearchParameters().getType();
	   String city=lookup.getSearchParameters().getCity();
	   String subscriberId=lookup.getSearchParameters().getSubscriberId(); 
	   
	   String signStr=domain+"|"+country+"|"+type+"|"+city+"|"+subscriberId;
	   
	   byte sign[]=sign(privateKey.getBytes(),signStr.getBytes());
	   String signature = new String(sign, StandardCharsets.UTF_8);  
	   String signatureBase64 = Base64.getEncoder().encodeToString(sign);
	   
	  
		
	   return signatureBase64;
   }
  
  public static byte[] sign(byte[] privateKey,byte[] message) {
		// initialise signature variable
		byte[] signature = new byte[Ed25519.SIGNATURE_SIZE];
		
		// sign the received message with given private key
		Ed25519.sign(privateKey, 0, message, 0, message.length, signature, 0);
      return signature; 
	}
  
  @SuppressWarnings("unused")
  private static String  convertToJson(VLookup vlookUp)
  {
	  String json=null;
	  ObjectMapper objectMapper = new ObjectMapper();
      try
      {
          json = objectMapper.writeValueAsString(vlookUp);
          System.out.println(json);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return json;
  }
  
  @SuppressWarnings("unused")
  private static String generateRequestNumber()
  {  
	  LocalDateTime dateTime = LocalDateTime.now();

      // Generate a random number between 1000 and 9999
      Random random = new Random();
      int randomNumber = random.nextInt(9000) + 1000;

      // Combine the timestamp and random number to create the request number
      String requestNumber = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(dateTime) + randomNumber;

      // Print the request number
      System.out.println("requestNumber "+requestNumber);
      return requestNumber;
  }
   

}
