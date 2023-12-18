package ONDC.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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
	
	private static final String json = "{\n" +
	        "  \"context\": {\n" +
	        "    \"domain\": \"nic2004:52110\",\n" +
	        "    \"action\": \"search\",\n" +
	        "    \"country\": \"IND\",\n" +
	        "    \"city\": \"std:011\",\n" +
	        "    \"core_version\": \"1.1.0\",\n" +
	        "    \"bap_id\": \"buyer-app.ondc.org\",\n" +
	        "    \"bap_uri\": \"https://buyerapp.com/ondc\",\n" +
	        "    \"transaction_id\": \"3df395a9-c196-4678-a4d1-5eaf4f7df8dc\",\n" +
	        "    \"message_id\": \"1655281254860\",\n" +
	        "    \"timestamp\": \"2023-02-03T08:00:00.000Z\",\n" +
	        "    \"ttl\": \"PT30S\"\n" +
	        "  },\n" +
	        "  \"message\": {\n" +
	        "    \"intent\": {\n" +
	        "      \"fulfillment\": {\n" +
	        "        \"type\": \"Delivery\"\n" +
	        "      },\n" +
	        "      \"payment\": {\n" +
	        "        \"@ondc/org/buyer_app_finder_fee_type\": \"percent\",\n" +
	        "        \"@ondc/org/buyer_app_finder_fee_amount\": \"3\"\n" +
	        "      }\n" +
	        "    }\n" +
	        "  }\n" +
	        "}";

		
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
		
		System.out.println("Private Key : "+signingKeyPair.getPrivateKey());
		System.out.println("Public Key : "+signingKeyPair.getPublicKey());
		
		byte jsonBytes[]=json.getBytes();
		
		/** Sign the private key **/
		byte[] signature=sign(signingKeyPair.getPrivateKey(), json.getBytes());
		
		System.out.println("signature1  "+signature);
		
		/** Verify the signature using Public key from registry ie: lookup **/
		
		boolean verificationResult=verify(signature, jsonBytes, signingKeyPair.getPublicKey());
		
		System.out.println("verificationResult.."+verificationResult);
		
	  
		try 
		{
			/** Create Blake hash **/
			byte blake2bhash[]=generateBlake2bHash(json);
			
			
			signatureBase64 = Base64.getEncoder().encodeToString(blake2bhash);
			
			System.out.println("signatureBase64 String "+signatureBase64);
			
			/** Sign the private key **/
			byte signedSignatureBytes[]=sign(signingKeyPair.getPrivateKey(), signatureBase64.getBytes());
			
			String signedSignature = Base64.getEncoder().encodeToString(signedSignatureBytes);
			
				
			String authHeaderValue = String.format("Signature keyId=\"%s|%s|%s\",algorithm=\"%s\",created=\"%d\",expires=\"%d\",headers=\"%s\",signature=\"Base64(%s)\"",
				        subscriberId, ukId, algorithm, algorithm, created, expires, headers, signedSignature);
			
			
			System.out.println("authHeaderValue:   "+authHeaderValue);
			
			/** Call Auth header for verification **/
			verifyRequestSignature(authHeaderValue , json,blake2bhash,signature);      
			
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
    public static void verifyRequestSignature(String authHeader, String jsonPayload,  byte digest[],byte oldsignature[]) throws Exception {
       
    	   
    	/** Extract the digest from the encoded signature **/
    	String signature = authHeader.substring(authHeader.indexOf("signature=") + 10);
    	
    	/**Remove Base64() from signature value **/
    	signature = signature.replaceAll("Base64\\(|\\)", ""); 
    	
    	// Get ukid  from  auth header
    	String ukid=getUkId(authHeader);
    	
    	signature= signature.substring(1, signature.length() - 1);
    	
    	String signatureUTF8 = new String(signature.getBytes(StandardCharsets.UTF_8));	
    	
    	System.out.println("Signature - "+signatureUTF8);
    	
    	byte[] signatureDecoded = Base64.getDecoder().decode(signatureUTF8);
    	
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
         System.out.println("jsonSignature--- "+jsonSignature);      
         StringEntity params = new StringEntity(jsonSignature, ContentType.APPLICATION_JSON);
         request.setEntity(params);     
         HttpResponse response = httpClient.execute(request);
         HttpEntity entity = response.getEntity();
         responseString = EntityUtils.toString(entity, "UTF-8");
         System.out.println("Lookup Response :: "+responseString);
         
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