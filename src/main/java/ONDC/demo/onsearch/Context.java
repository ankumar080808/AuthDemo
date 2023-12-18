
package ONDC.demo.onsearch;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "domain",
    "country",
    "city",
    "action",
    "core_version",
    "bap_id",
    "bap_uri",
    "bpp_id",
    "bpp_uri",
    "transaction_id",
    "message_id",
    "timestamp"
})
@Generated("jsonschema2pojo")
public class Context implements Serializable
{

    @JsonProperty("domain")
    private String domain;
    @JsonProperty("country")
    private String country;
    @JsonProperty("city")
    private String city;
    @JsonProperty("action")
    private String action;
    @JsonProperty("core_version")
    private String coreVersion;
    @JsonProperty("bap_id")
    private String bapId;
    @JsonProperty("bap_uri")
    private String bapUri;
    @JsonProperty("bpp_id")
    private String bppId;
    @JsonProperty("bpp_uri")
    private String bppUri;
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("timestamp")
    private String timestamp;
    private final static long serialVersionUID = -7492119647724935066L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Context() {
    }

    /**
     * 
     * @param country
     * @param bppUri
     * @param bppId
     * @param city
     * @param domain
     * @param action
     * @param messageId
     * @param bapUri
     * @param coreVersion
     * @param bapId
     * @param transactionId
     * @param timestamp
     */
    public Context(String domain, String country, String city, String action, String coreVersion, String bapId, String bapUri, String bppId, String bppUri, String transactionId, String messageId, String timestamp) {
        super();
        this.domain = domain;
        this.country = country;
        this.city = city;
        this.action = action;
        this.coreVersion = coreVersion;
        this.bapId = bapId;
        this.bapUri = bapUri;
        this.bppId = bppId;
        this.bppUri = bppUri;
        this.transactionId = transactionId;
        this.messageId = messageId;
        this.timestamp = timestamp;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("core_version")
    public String getCoreVersion() {
        return coreVersion;
    }

    @JsonProperty("core_version")
    public void setCoreVersion(String coreVersion) {
        this.coreVersion = coreVersion;
    }

    @JsonProperty("bap_id")
    public String getBapId() {
        return bapId;
    }

    @JsonProperty("bap_id")
    public void setBapId(String bapId) {
        this.bapId = bapId;
    }

    @JsonProperty("bap_uri")
    public String getBapUri() {
        return bapUri;
    }

    @JsonProperty("bap_uri")
    public void setBapUri(String bapUri) {
        this.bapUri = bapUri;
    }

    @JsonProperty("bpp_id")
    public String getBppId() {
        return bppId;
    }

    @JsonProperty("bpp_id")
    public void setBppId(String bppId) {
        this.bppId = bppId;
    }

    @JsonProperty("bpp_uri")
    public String getBppUri() {
        return bppUri;
    }

    @JsonProperty("bpp_uri")
    public void setBppUri(String bppUri) {
        this.bppUri = bppUri;
    }

    @JsonProperty("transaction_id")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transaction_id")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("message_id")
    public String getMessageId() {
        return messageId;
    }

    @JsonProperty("message_id")
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Context.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("domain");
        sb.append('=');
        sb.append(((this.domain == null)?"<null>":this.domain));
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("action");
        sb.append('=');
        sb.append(((this.action == null)?"<null>":this.action));
        sb.append(',');
        sb.append("coreVersion");
        sb.append('=');
        sb.append(((this.coreVersion == null)?"<null>":this.coreVersion));
        sb.append(',');
        sb.append("bapId");
        sb.append('=');
        sb.append(((this.bapId == null)?"<null>":this.bapId));
        sb.append(',');
        sb.append("bapUri");
        sb.append('=');
        sb.append(((this.bapUri == null)?"<null>":this.bapUri));
        sb.append(',');
        sb.append("bppId");
        sb.append('=');
        sb.append(((this.bppId == null)?"<null>":this.bppId));
        sb.append(',');
        sb.append("bppUri");
        sb.append('=');
        sb.append(((this.bppUri == null)?"<null>":this.bppUri));
        sb.append(',');
        sb.append("transactionId");
        sb.append('=');
        sb.append(((this.transactionId == null)?"<null>":this.transactionId));
        sb.append(',');
        sb.append("messageId");
        sb.append('=');
        sb.append(((this.messageId == null)?"<null>":this.messageId));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null)?"<null>":this.timestamp));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
