package ONDC.demo.onsearch;


import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "currency",
    "value",
    "maximum_value"
})
@Generated("jsonschema2pojo")
public class Price implements Serializable
{

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("value")
    private String value;
    @JsonProperty("maximum_value")
    private String maximumValue;
    private final static long serialVersionUID = 8439497903845825683L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Price() {
    }

    /**
     * 
     * @param currency
     * @param value
     * @param maximumValue
     */
    public Price(String currency, String value, String maximumValue) {
        super();
        this.currency = currency;
        this.value = value;
        this.maximumValue = maximumValue;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("maximum_value")
    public String getMaximumValue() {
        return maximumValue;
    }

    @JsonProperty("maximum_value")
    public void setMaximumValue(String maximumValue) {
        this.maximumValue = maximumValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Price.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
        sb.append(',');
        sb.append("maximumValue");
        sb.append('=');
        sb.append(((this.maximumValue == null)?"<null>":this.maximumValue));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
