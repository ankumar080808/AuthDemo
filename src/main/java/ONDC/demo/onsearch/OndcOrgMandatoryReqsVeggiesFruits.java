
package ONDC.demo.onsearch;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "net_quantity"
})
@Generated("jsonschema2pojo")
public class OndcOrgMandatoryReqsVeggiesFruits implements Serializable
{

    @JsonProperty("net_quantity")
    private String netQuantity;
    private final static long serialVersionUID = -5126042723480994213L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OndcOrgMandatoryReqsVeggiesFruits() {
    }

    /**
     * 
     * @param netQuantity
     */
    public OndcOrgMandatoryReqsVeggiesFruits(String netQuantity) {
        super();
        this.netQuantity = netQuantity;
    }

    @JsonProperty("net_quantity")
    public String getNetQuantity() {
        return netQuantity;
    }

    @JsonProperty("net_quantity")
    public void setNetQuantity(String netQuantity) {
        this.netQuantity = netQuantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OndcOrgMandatoryReqsVeggiesFruits.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("netQuantity");
        sb.append('=');
        sb.append(((this.netQuantity == null)?"<null>":this.netQuantity));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
