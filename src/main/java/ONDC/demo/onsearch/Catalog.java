
package ONDC.demo.onsearch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bpp/fulfillments",
    "bpp/descriptor",
    "bpp/providers"
})
@Generated("jsonschema2pojo")
public class Catalog implements Serializable
{

    @JsonProperty("bpp/fulfillments")
    private List<BppFulfillment> bppFulfillments = new ArrayList<BppFulfillment>();
    @JsonProperty("bpp/descriptor")
    private BppDescriptor bppDescriptor;
    @JsonProperty("bpp/providers")
    private List<BppProvider> bppProviders = new ArrayList<BppProvider>();
    private final static long serialVersionUID = -4984374151375348865L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Catalog() {
    }

    /**
     * 
     * @param bppDescriptor
     * @param bppFulfillments
     * @param bppProviders
     */
    public Catalog(List<BppFulfillment> bppFulfillments, BppDescriptor bppDescriptor, List<BppProvider> bppProviders) {
        super();
        this.bppFulfillments = bppFulfillments;
        this.bppDescriptor = bppDescriptor;
        this.bppProviders = bppProviders;
    }

    @JsonProperty("bpp/fulfillments")
    public List<BppFulfillment> getBppFulfillments() {
        return bppFulfillments;
    }

    @JsonProperty("bpp/fulfillments")
    public void setBppFulfillments(List<BppFulfillment> bppFulfillments) {
        this.bppFulfillments = bppFulfillments;
    }

    @JsonProperty("bpp/descriptor")
    public BppDescriptor getBppDescriptor() {
        return bppDescriptor;
    }

    @JsonProperty("bpp/descriptor")
    public void setBppDescriptor(BppDescriptor bppDescriptor) {
        this.bppDescriptor = bppDescriptor;
    }

    @JsonProperty("bpp/providers")
    public List<BppProvider> getBppProviders() {
        return bppProviders;
    }

    @JsonProperty("bpp/providers")
    public void setBppProviders(List<BppProvider> bppProviders) {
        this.bppProviders = bppProviders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Catalog.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bppFulfillments");
        sb.append('=');
        sb.append(((this.bppFulfillments == null)?"<null>":this.bppFulfillments));
        sb.append(',');
        sb.append("bppDescriptor");
        sb.append('=');
        sb.append(((this.bppDescriptor == null)?"<null>":this.bppDescriptor));
        sb.append(',');
        sb.append("bppProviders");
        sb.append('=');
        sb.append(((this.bppProviders == null)?"<null>":this.bppProviders));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
