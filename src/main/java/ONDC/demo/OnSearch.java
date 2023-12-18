package ONDC.demo;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.rfc8032.Ed25519;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OnSearch {

  private static final String LOOKUP_ENDPOINT = "https://pilot-gateway-1.beckn.nsdl.co.in/lookup";
  private static final String PAYLOAD = "{\"context\": {\"domain\": \"nic2004:52110\",\"country\": \"IND\",\"city\": \"std:011\",\"action\": \"on_search\",\"core_version\": \"1.1.0\",\"bap_id\": \"buyerapp.com\",\"bap_uri\": \"https://buyerapp.com/ondc\",\"bpp_id\": \"sellerapp.com\",\"bpp_uri\": \"https://sellerapp.com/ondc\",\"transaction_id\": \"9fdb667c-76c6-456a-9742-ba9caa5eb765\",\"message_id\": \"1655281254860\",\"timestamp\": \"2023-04-25T08:00:37.245Z\"},\"message\": {\"catalog\": {\"bpp/fulfillments\": [{\"id\":1}, {\"id\":2}, {\"id\":3}],\"bpp/descriptor\": {\"name\": \"ABC store\",\"symbol\": \"https://abc.com/images/1-shop-img\",\"short_desc\": \"Online eCommerce Store\",\"long_desc\": \"Online eCommerce Store\",\"images\": [\"https://abc.com/images/shop-img\"]},\"bpp/providers\": [{\"id\":\"P1\"}],\"bpp/locations\": [{\"id\":\"L1\"}],\"bpp/items\": [{\"id\":\"I1\"}]}}}";
  /** The Constant BLAKE2B_512. */
  private static final String BLAKE2B_512="BLAKE2B-512";
  private static final String subscriberId = "vinculumgroup.com";
  private static final String ukId = "292";
  private static final String algorithm = "ed25519";
  private static final long created = Instant.now().getEpochSecond();
  private static final long expires = created + 3600; // set expiration time to 1 hour from creation
  private static final String headers = "(created) (expires) digest";
  private static  CryptoKeyPair signingKeyPair;
  private static String signatureBase64 ;
	

  public static void main(String[] args) {
	  CloseableHttpClient httpClient = null;
      CloseableHttpResponse response = null;
	  try 
	  	{
	  	  
		  String jsonSignature="{\"context\":{\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"std:011\",\"action\":\"on_search\",\"core_version\":\"1.1.0\",\"bap_id\":\"buyerapp.com\",\"bap_uri\":\"https://buyerapp.com/ondc\",\"bpp_id\":\"sellerapp.com\",\"bpp_uri\":\"https://sellerapp.com/ondc\",\"transaction_id\":\"9fdb667c-76c6-456a-9742-ba9caa5eb765\",\"message_id\":\"1655281254860\",\"timestamp\":\"2023-02-03T08:00:30.000Z\"},\"message\":{\"catalog\":{\"bpp/fulfillments\":[{\"id\":\"1\",\"type\":\"Delivery\"},{\"id\":\"2\",\"type\":\"Self-Pickup\"},{\"id\":\"3\",\"type\":\"DeliveryandSelf-Pickup\"}],\"bpp/descriptor\":{\"name\":\"ABCstore\",\"symbol\":\"https://abc.com/images/1-shop-img\",\"short_desc\":\"OnlineeCommerceStore\",\"long_desc\":\"OnlineeCommerceStore\",\"images\":[\"https://abc.com/images/shop-img\"]},\"bpp/providers\":[{\"id\":\"P1\",\"time\":{\"label\":\"disable\",\"timestamp\":\"2023-02-03T08:00:30.000Z\"},\"descriptor\":{\"name\":\"ABCstore\",\"symbol\":\"https://abc.com/images/shop-img\",\"short_desc\":\"ABCstore\",\"long_desc\":\"ABCstore_\",\"images\":[\"https://abc.com/images/18275/18275-1-shop-img\"]},\"@ondc/org/fssai_license_no\":\"12345678901234\",\"ttl\":\";P1D\",\"locations\":[{\"id\":\"L1\",\"gps\":\"12.967555,77.749666\",\"address\":{\"locality\":\"Jayanagar4thBlock\",\"street\":\"Jayanagar4thBlock\",\"city\":\"Bengaluru\",\"area_code\":\"560076\",\"state\":\"KA\"},\"circle\":{\"gps\":\"12.967555,77.749666\",\"radius\":{\"unit\":\"km\",\"value\":\"3\"}},\"time\":{\"days\":\"1,2,3,4,5,6,7\",\"schedule\":{\"holidays\":[\"2022-08-15\",\"2022-08-19\"],\"frequency\":\"PT4H\",\"times\":[\"1100\",\"1900\"]},\"range\":{\"start\":\"1100\",\"end\":\"2100\"}}}],\"items\":[{\"id\":\"I1\",\"descriptor\":{\"name\":\"Atta\",\"code\":\"1:XXXXXXXXXXXXX\",\"symbol\":\"https://abc.com/images/07.png\",\"short_desc\":\"AshirwadAtta5kg\",\"long_desc\":\"AshirwadAtta5kg\",\"images\":[\"https://abc.com/images/07.png\"]},\"quantity\":{\"available\":{\"count\":\"1\"},\"maximum\":{\"count\":\"2\"}},\"price\":{\"currency\":\"INR\",\"value\":\"170.0\",\"maximum_value\":\"180.0\"},\"category_id\":\"PackagedCommodities\",\"fulfillment_id\":\"1\",\"location_id\":\"L1\",\"recommended\":true,\"@ondc/org/returnable\":true,\"@ondc/org/cancellable\":true,\"@ondc/org/return_window\":\"P7D\",\"@ondc/org/seller_pickup_return\":false,\"@ondc/org/time_to_ship\":\"PT45M\",\"@ondc/org/available_on_cod\":false,\"@ondc/org/contact_details_consumer_care\":\"Ramesh,ramesh@abc.com,18004254444\",\"@ondc/org/statutory_reqs_packaged_commodities\":{\"manufacturer_or_packer_name\":\"ITC\",\"manufacturer_or_packer_address\":\"ITCQualityCareCell,P.OBoxNo.592,Bangalore-560005\",\"common_or_generic_name_of_commodity\":\"AshirwadAtta\",\"net_quantity_or_measure_of_commodity_in_pkg\":\"5kg\",\"month_year_of_manufacture_packing_import\":\"01/2023\",\"imported_product_country_of_origin\":\"IND\"},\"@ondc/org/statutory_reqs_prepackaged_food\":{\"nutritional_info\":\"Energy(KCal)-(per100kg)420,(perserving50g)250;Protein(g)-(per100kg)12,(perserving50g)6\",\"additives_info\":\"Preservatives,ArtificialColours\",\"brand_owner_FSSAI_license_no\":\"12345678901234\",\"other_FSSAI_license_no\":\"12345678901234\",\"importer_FSSAI_license_no\":\"12345678901234\",\"imported_product_country_of_origin\":\"IND\"},\"@ondc/org/mandatory_reqs_veggies_fruits\":{\"net_quantity\":\"100g\"},\"tags\":{\"veg\":\"yes\",\"non_veg\":\"no\"}}],\"fulfillments\":[{\"contact\":{\"phone\":\"9886098860\",\"email\":\"abc@xyz.com\"}}],\"tags\":[{\"code\":\"serviceability\",\"list\":[{\"code\":\"location\",\"value\":\"L1\"},{\"code\":\"category\",\"value\":\"Eggs,Meat&Fish\"},{\"code\":\"type\",\"value\":\"10\"},{\"code\":\"val\",\"value\":\"3\"},{\"code\":\"unit\",\"value\":\"km\"}]},{\"code\":\"serviceability\",\"list\":[{\"code\":\"location\",\"value\":\"L1\"},{\"code\":\"category\",\"value\":\"KitchenAccessories\"},{\"code\":\"type\",\"value\":\"12\"},{\"code\":\"val\",\"value\":\"IND\"},{\"code\":\"unit\",\"value\":\"country\"}]}]}]}}}";
		  
	  	 //String jsonSignature= "https://pilot-gateway-1.beckn.nsdl.co.in/lookup";
		  
	  	  /** Create Priv8ate/public key pairs, for signing (ed25519) & encryption (X25519) **/
		  signingKeyPair=generateSigningKeyPair();
			
		  byte blake2bhash[]=generateBlake2bHash(jsonSignature);
			
		   signatureBase64 = Base64.getEncoder().encodeToString(blake2bhash);
			
	  	  /* Sign the private key **/
		   byte signedSignatureBytes[]=sign(signingKeyPair.getPrivateKey(), signatureBase64.getBytes());
			 
		   
		   String signedSignature = Base64.getEncoder().encodeToString(signedSignatureBytes);
		  
			
			//commented generated signature , but this one works signature case
		   String authHeaderValue = String.format("Signature keyId=\"%s|%s|%s\",algorithm=\"%s\",created=\"%d\",expires=\"%d\",headers=\"%s\",signature=\"Base64(%s)\"",
				        subscriberId, ukId, algorithm, algorithm, created, expires, headers, signedSignature);
			
		    verifyRequestSignature(authHeaderValue , jsonSignature);      
			
	        System.out.println("authHeaderValue--- " + authHeaderValue + "\n\n\n");
 

            httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost("https://pilot-gateway-1.beckn.nsdl.co.in/on_search");
             
            System.out.println("authHeaderValue--- " + authHeaderValue + "\n\n\n");

            StringEntity params = new StringEntity(authHeaderValue, ContentType.APPLICATION_JSON);

            request.setEntity(params);

            response = httpClient.execute(request);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            System.out.println("Lookup Response :: " + responseString);
	       } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (httpClient != null) {
	                    httpClient.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        
	        }
	       
	       
	     
  }
  
  
  /**
   * Method top call Ondc look up Api
   * @return
   */
  public static String lookup()
  {
  	
    String responseString=null;
    
    try 
  	{
  	 String jsonSignature="{\"subscriber_id\":\"vinculumgroup.com\",\"type\":\"BPP\",\"domain\":\"nic2004:52110\",\"country\":\"IND\",\"city\":\"*\"}";
       ////String jsonSignature = "vinculumgroup.com|292|ed25519";

       HttpClient httpClient = HttpClientBuilder.create().build();
       HttpPost request = new HttpPost(LOOKUP_ENDPOINT);         
       //System.out.println("jsonSignature--- "+jsonSignature);      
       StringEntity params = new StringEntity(jsonSignature, ContentType.APPLICATION_JSON);
       request.setEntity(params);     
       HttpResponse response = httpClient.execute(request);
       HttpEntity entity = response.getEntity();
       responseString = EntityUtils.toString(entity, "UTF-8");
       //System.out.println("Lookup Response :: "+responseString);
       
      }catch(Exception e)
      {
      	e.printStackTrace();
      }
   
    return responseString;
   
  }
  
  /**
	 * 
	 * @param privateKey
	 * @param message
	 * @return
	 */
	public static byte[] sign(byte[] privateKey,byte[] message) {
		// initialise signature variable
		byte[] signature = new byte[Ed25519.SIGNATURE_SIZE];
		
		// sign the received message with given private key
		Ed25519.sign(privateKey, 0, message, 0, message.length, signature, 0);
      return signature; 
	}
	

	/**
	 * 
	 * @return
	 */
	public static CryptoKeyPair generateSigningKeyPair() {

		// generate ed25519 keys
		SecureRandom RANDOM = new SecureRandom();
		
	 	byte[] privateKey = new byte[Ed25519.SECRET_KEY_SIZE]; //32 Byte or 256 bits
	 	
        byte[] publicKey = new byte[Ed25519.PUBLIC_KEY_SIZE];  //32 Byte or 256 bits
        
        /** Fetch  Public key by calling  lookup API    **/
        String response=lookup();
		
		List<Lookup> lookupList=consume(response);
		
		Lookup lookup=lookupList.get(0);
		
		publicKey="1iC01Mc4uRzpe6BZ6zND+YkiOhFTVUpNhCjbBifLoiE=".getBytes();
        
		/** vinculum private key **/
        privateKey="lTKY44iTfKqtxDdiWgDpMAgSQNKkVVhV49/6InrFCjfWILTUxzi5HOl7oFnrM0P5iSI6EVNVSk2EKNsGJ8uiIQ==".getBytes();;
       
        // generate private key using secure random
        RANDOM.nextBytes(privateKey);
        
        // generate public key 
        Ed25519.generatePublicKey(privateKey, 0, publicKey, 0);
        
        // store generated key pair and return the same
        CryptoKeyPair signingKeyPair=new CryptoKeyPair(publicKey,privateKey) ;
    	return signingKeyPair;
	}
	
	 private static List<Lookup> consume(String json)
	    {
	    	List<Lookup> list=new ArrayList<Lookup>();
	    	
	    	// create an ObjectMapper instance
	        ObjectMapper mapper = new ObjectMapper();
	        // convert the JSON string into a JsonNode object
	        JsonNode root=null;
	        
			try 
			{
				root = mapper.readTree(json);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
	        // iterate over the array elements
	        for (JsonNode node : root) {
	        	Lookup lookup=new Lookup();
	        	
	            int brId = node.get("br_id").asInt();
	            String subscriberId = node.get("subscriber_id").asText();
	            String subscriberUrl = node.get("subscriber_url").asText();
	            String type = node.get("type").asText();
	            String domain = node.get("domain").asText();
	            String city = node.get("city").asText();
	            String country = node.get("country").asText();
	            String signingPublicKey = node.get("signing_public_key").asText();
	            String encrPublicKey = node.get("encr_public_key").asText();
	            String validFrom = node.get("valid_from").asText();
	            String validUntil = node.get("valid_until").asText();
	            String status = node.get("status").asText();
	            String created = node.get("created").asText();
	            String updated = node.get("updated").asText();
	            String ukId = node.get("ukId").asText();
	            
	            lookup.setBrId(brId);
	            lookup.setSubscriberId(subscriberId);
	            lookup.setSubscriberUrl(subscriberUrl);
	            lookup.setType(type);
	            lookup.setDomain(domain);
	            lookup.setCity(city);
	            lookup.setCountry(country);
	            lookup.setSigningPublicKey(signingPublicKey);
	            lookup.setEncrPublicKey(encrPublicKey);
	            lookup.setValidFrom(validFrom);
	            lookup.setValidUntil(validUntil);
	            lookup.setStatus(status);
	            lookup.setCreated(created);
	            lookup.setUpdated(updated);
	            lookup.setUkId(ukId);
	            list.add(lookup);
	                        // do something with the values
	            System.out.printf("br_id=%d, subscriber_id=%s, subscriber_url=%s, type=%s, domain=%s, city=%s, country=%s, signing_public_key=%s, encr_public_key=%s, valid_from=%s, valid_until=%s, status=%s, created=%s, updated=%s, ukId=%s%n",
	                    brId, subscriberId, subscriberUrl, type, domain, city, country, signingPublicKey, encrPublicKey, validFrom, validUntil, status, created, updated, ukId);
	        }
	        return list;
	    }

	 
	 /** Method to verify Authentication  header
		 * 
		 * @param authHeader
		 * @param jsonPayload
		 * @param digest
		 * @throws Exception
		 */
	    public static void verifyRequestSignature(String authHeader, String jsonPayload) throws Exception {
	       
	    	if (authHeader == null || authHeader.isEmpty()) {
	    	    // handle empty or null auth header
	    		throw new Exception("Invalid request signature");
	    	}
	    	System.out.println("\n\n\n");
	    	
	    	
	    	System.out.println(authHeader);
	    	System.out.println("\n\n\n");
			
	    	   
	    	/** Extract the digest from the encoded signature **/
	    	String signature = authHeader.substring(authHeader.indexOf("signature=") + 10);
	    	
	    	/**Remove Base64() from signature value **/
	    	signature = signature.replaceAll("Base64\\(|\\)", ""); 
	    	
	    	// Get ukid  from  auth header
	    	String ukid=getUkId(authHeader);
	    	
	    	signature= signature.substring(1, signature.length() - 1);
	    	
	 
	    	
	    	String signatureUTF8 = new String(signature.getBytes(StandardCharsets.UTF_8));	
	    	
	    	//System.out.println("Signature - "+signatureUTF8);
	    	
	    	byte[] signatureDecoded = Base64.getDecoder().decode(signatureUTF8);
	    	
	    	//System.out.println("After ::: signatureDecoded - "+signatureUTF8);
	    	
	    	/** Create Blake hash **/
			byte blake2bhash[]=generateBlake2bHash(jsonPayload);
			
			
			signatureBase64 = Base64.getEncoder().encodeToString(blake2bhash);
			
	    	
	    	boolean verificationResult=verify(signatureDecoded, signatureBase64.getBytes(),signingKeyPair.getPublicKey() );
			
	    	System.out.println("verificationResult - "+verificationResult);
	    
	    	//byte[] signatureDecoded2 = Base64.getDecoder().decode(blake2bhash);
	    	
	    
	        // Compare generated digest(ie input json digest ) with decoded digest
	       // boolean isValidSignature = MessageDigest.isEqual(signatureDecoded2, signatureBase64.getBytes());
	       
	      
	       if (verificationResult)
	       {
		     System.out.println("**** Authentication Sucess **** ");      
	       }
	       else
	       {
	    	   // Throw HTTP error 401 if verification fails
	          throw new Exception("Invalid request signature");
	       }
	    }
	    
	    /**
		 * 
		 * @param req
		 * @return
		 * @throws Exception
		 */
		public static byte[] generateBlake2bHash(String req) throws Exception {
			if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
				Security.addProvider(new BouncyCastleProvider());
			}
			MessageDigest digest = MessageDigest.getInstance(BLAKE2B_512, BouncyCastleProvider.PROVIDER_NAME);
			digest.reset();
			digest.update(req.getBytes(StandardCharsets.UTF_8));
			return digest.digest();
		}
		
		/**
	     * This method is to fetch ukid from Auth Header
	     */
	    private static  String getUkId(String authHeader) 
	    {
	    	String number=null;
	    	Pattern pattern = Pattern.compile("keyId=\"([^\"]+)\"");
	    	Matcher matcher = pattern.matcher(authHeader);
	    	if (matcher.find())
	    	{
	    	    String keyId = matcher.group(1);
	    	    System.out.println(keyId);
	    	    String[] parts = keyId.split("\\|");
	     	    number = parts[1];
	     	    System.out.println(number);     
	    	}	
	 	    return number;
	    }
	    
	    /**
		 * 
		 * @param signature
		 * @param message
		 * @param publicKey
		 * @return
		 */
		public static boolean verify(byte[] signature,byte[] message, byte[] publicKey) {
			//verify the given signature with 
			return Ed25519.verify(signature, 0, publicKey, 0, message, 0, message.length);
		}

}