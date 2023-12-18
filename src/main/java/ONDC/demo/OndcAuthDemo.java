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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.rfc8032.Ed25519;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;


public class OndcAuthDemo {
	
	/** Sample Json */
	//private static final String json="{ Id: 78912, Quantity: 1,Price: 18.00}";
	
	/*private static final String json = "{\"context\":{\"domain\":\"nic2004:52110\",\"action\":\"search\",\"country\":\"IND\",\"city\":\"std:080\","
			+ "\"core_version\":\"1.1.0\",\"bap_id\":\"buyerapp.com\",\"bap_uri\":\"https://buyerapp.com/ondc\",\"transaction_id\":"
			+ "\"3df395a9-c196-4678-a4d1-5eaf4f7df8dc\",\"message_id\":\"1655281254860\",\"timestamp\":\"2023-02-03T08:00:00.000Z\","
			+ "\"ttl\":\"PT30S\"},\"message\":{\"intent\":{\"item\":{\"descriptor\":{\"name\":\"CC2_1\"}},"
			+ "\"fulfillment\":{\"type\":\"Delivery\",\"end\":{\"location\":{\"gps\":\"12.974002,77.613458\","
			+ "\"address\":{\"area_code\":\"560001\"}}}},\"payment\":{\"@ondc/org/buyer_app_finder_fee_type\":\"percent\",\"@ondc/org/buyer_app_finder_fee_amount\":\"3\"}}}}";
	*/
	
	private static final String json="{\"context\": {\"domain\": \"nic2004:52110\",\"country\": \"IND\",\"city\": \"std:011\",\"action\": \"on_search\",\"core_version\": \"1.1.0\",\"bap_id\": \"buyerapp.com\",\"bap_uri\": \"https://buyerapp.com/ondc\",\"bpp_id\": \"sellerapp.com\",\"bpp_uri\": \"https://sellerapp.com/ondc\",\"transaction_id\": \"9fdb667c-76c6-456a-9742-ba9caa5eb765\",\"message_id\": \"1655281254860\",\"timestamp\": \"2023-04-25T08:00:37.245Z\"},\"message\": {\"catalog\": {\"bpp/fulfillments\": [{\"id\":1}, {\"id\":2}, {\"id\":3}],\"bpp/descriptor\": {\"name\": \"ABC store\",\"symbol\": \"https://abc.com/images/1-shop-img\",\"short_desc\": \"Online eCommerce Store\",\"long_desc\": \"Online eCommerce Store\",\"images\": [\"https://abc.com/images/shop-img\"]},\"bpp/providers\": [{\"id\":\"P1\"}],\"bpp/locations\": [{\"id\":\"L1\"}],\"bpp/items\": [{\"id\":\"I1\"}]}}}";
     
		
	/** The Constant BLAKE2B_512. */
	private static final String BLAKE2B_512="BLAKE2B-512";
	private static final String subscriberId = "vinculumgroup.com";
	private static final String ukId = "292";
	private static final String algorithm = "ed25519";
	private static final long created = Instant.now().getEpochSecond();
	private static final long expires = created + 3600; // set expiration time to 1 hour from creation
	private static final String headers = "(created) (expires) digest";
	
    private static final String LOOKUP_ENDPOINT = "https://pilot-gateway-1.beckn.nsdl.co.in/lookup";
    private static  CryptoKeyPair signingKeyPair;

    private static String signatureBase64 ;
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		System.out.println("*****Ondc Auth Demo****");
		
		/** Initialize LazySodium **/
		LazySodiumJava lazySodium = new LazySodiumJava(new SodiumJava());
		
		/** Create Private/public key pairs, for signing (ed25519) & encryption (X25519) **/
		signingKeyPair=generateSigningKeyPair();
		
		//System.out.println("Private Key : "+signingKeyPair.getPrivateKey());
		//System.out.println("Public Key : "+signingKeyPair.getPublicKey());
		
		byte jsonBytes[]=json.getBytes();
		
		/** Sign the private key **/
		byte[] signature=sign(signingKeyPair.getPrivateKey(), json.getBytes());
		
		//System.out.println("signature1  "+signature);
		
		/** Verify the signature using Public key from registry ie: lookup **/
		
		boolean verificationResult=verify(signature, jsonBytes, signingKeyPair.getPublicKey());
		
		//System.out.println("verificationResult.."+verificationResult);
		
	  
		try 
		{
			/** Create Blake hash **/
			byte blake2bhash[]=generateBlake2bHash(json);
			
			
			signatureBase64 = Base64.getEncoder().encodeToString(blake2bhash);
			
			//System.out.println("Before ::: signatureBase64  "+signatureBase64);
			
			/** Sign the private key **/
			byte signedSignatureBytes[]=sign(signingKeyPair.getPrivateKey(), signatureBase64.getBytes());
			
			String signedSignature = Base64.getEncoder().encodeToString(signedSignatureBytes);
			
				
			//commented generated signature , but this one works signature case
			String authHeaderValue = String.format("Signature keyId=\"%s|%s|%s\",algorithm=\"%s\",created=\"%d\",expires=\"%d\",headers=\"%s\",signature=\"Base64(%s)\"",
				        subscriberId, ukId, algorithm, algorithm, created, expires, headers, signedSignature);
			
			//testing copy paste signature case
			//String authHeaderValue="Signature keyId=\"vinculumgroup.com|292|ed25519\",algorithm=\"ed25519\",created=\"1683523636\",expires=\"1683527631\",headers=\"(created) (expires) digest\",signature=\"Base64(mCSOaCSPWOzLg9SFN004KTfxa+uhk5+d0HbO/DmuAmkZtQZYIQjXksZPOO6ZHVg1VFddlzG4PmI+YkoBCK8+Bg==)\"";
			
			
			System.out.println("\n\n\n");
			System.out.println(authHeaderValue);
			
			
			/** Call Auth header for verification **/
			verifyRequestSignature(authHeaderValue , json);      
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
		
		publicKey=lookup.getSigningPublicKey().getBytes();
        
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
	 * @param signature
	 * @param message
	 * @param publicKey
	 * @return
	 */
	public static boolean verify(byte[] signature,byte[] message, byte[] publicKey) {
		//verify the given signature with 
		return Ed25519.verify(signature, 0, publicKey, 0, message, 0, message.length);
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
		
    	
    	boolean verificationResult=verify(signatureDecoded, signatureBase64.getBytes(), signingKeyPair.getPublicKey());
		
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
     * This method is to consume the lookup response in Look up object 
     */
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
 
  
}