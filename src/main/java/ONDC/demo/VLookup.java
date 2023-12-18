
package ONDC.demo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


public class VLookup implements Serializable
{
	private final static long serialVersionUID = 5747381978596048346L;

	@JsonProperty("sender_subscriber_id")
    private String senderSubscriberId;
	
	@JsonProperty("request_id")
    private String requestId;
	
	@JsonProperty("timestamp")
    private String timestamp;
	
	@JsonProperty("search_parameters")
    private SearchParameters searchParameters;
    
    @JsonProperty("signature")
    private String signature;
   
   
    public String getSenderSubscriberId() {
        return senderSubscriberId;
    }

    public void setSenderSubscriberId(String senderSubscriberId) {
        this.senderSubscriberId = senderSubscriberId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public SearchParameters getSearchParameters() {
        return searchParameters;
    }

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    

}
