
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
    "id",
    "time",
    "descriptor",
    "@ondc/org/fssai_license_no",
    "ttl",
    "locations",
    "items",
    "fulfillments",
    "tags"
})
@Generated("jsonschema2pojo")
public class BppProvider implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("descriptor")
    private Descriptor descriptor;
    @JsonProperty("@ondc/org/fssai_license_no")
    private String ondcOrgFssaiLicenseNo;
    @JsonProperty("ttl")
    private String ttl;
    @JsonProperty("locations")
    private List<Location> locations = new ArrayList<Location>();
    @JsonProperty("items")
    private List<Item> items = new ArrayList<Item>();
    @JsonProperty("fulfillments")
    private List<Fulfillment> fulfillments = new ArrayList<Fulfillment>();
    @JsonProperty("tags")
    private List<Tag> tags = new ArrayList<Tag>();
    private final static long serialVersionUID = -7751972126463960143L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BppProvider() {
    }

    /**
     * 
     * @param fulfillments
     * @param locations
     * @param id
     * @param time
     * @param descriptor
     * @param ttl
     * @param items
     * @param ondcOrgFssaiLicenseNo
     * @param tags
     */
    public BppProvider(String id, Time time, Descriptor descriptor, String ondcOrgFssaiLicenseNo, String ttl, List<Location> locations, List<Item> items, List<Fulfillment> fulfillments, List<Tag> tags) {
        super();
        this.id = id;
        this.time = time;
        this.descriptor = descriptor;
        this.ondcOrgFssaiLicenseNo = ondcOrgFssaiLicenseNo;
        this.ttl = ttl;
        this.locations = locations;
        this.items = items;
        this.fulfillments = fulfillments;
        this.tags = tags;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("time")
    public Time getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Time time) {
        this.time = time;
    }

    @JsonProperty("descriptor")
    public Descriptor getDescriptor() {
        return descriptor;
    }

    @JsonProperty("descriptor")
    public void setDescriptor(Descriptor descriptor) {
        this.descriptor = descriptor;
    }

    @JsonProperty("@ondc/org/fssai_license_no")
    public String getOndcOrgFssaiLicenseNo() {
        return ondcOrgFssaiLicenseNo;
    }

    @JsonProperty("@ondc/org/fssai_license_no")
    public void setOndcOrgFssaiLicenseNo(String ondcOrgFssaiLicenseNo) {
        this.ondcOrgFssaiLicenseNo = ondcOrgFssaiLicenseNo;
    }

    @JsonProperty("ttl")
    public String getTtl() {
        return ttl;
    }

    @JsonProperty("ttl")
    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    @JsonProperty("locations")
    public List<Location> getLocations() {
        return locations;
    }

    @JsonProperty("locations")
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonProperty("fulfillments")
    public List<Fulfillment> getFulfillments() {
        return fulfillments;
    }

    @JsonProperty("fulfillments")
    public void setFulfillments(List<Fulfillment> fulfillments) {
        this.fulfillments = fulfillments;
    }

    @JsonProperty("tags")
    public List<Tag> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(BppProvider.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null)?"<null>":this.time));
        sb.append(',');
        sb.append("descriptor");
        sb.append('=');
        sb.append(((this.descriptor == null)?"<null>":this.descriptor));
        sb.append(',');
        sb.append("ondcOrgFssaiLicenseNo");
        sb.append('=');
        sb.append(((this.ondcOrgFssaiLicenseNo == null)?"<null>":this.ondcOrgFssaiLicenseNo));
        sb.append(',');
        sb.append("ttl");
        sb.append('=');
        sb.append(((this.ttl == null)?"<null>":this.ttl));
        sb.append(',');
        sb.append("locations");
        sb.append('=');
        sb.append(((this.locations == null)?"<null>":this.locations));
        sb.append(',');
        sb.append("items");
        sb.append('=');
        sb.append(((this.items == null)?"<null>":this.items));
        sb.append(',');
        sb.append("fulfillments");
        sb.append('=');
        sb.append(((this.fulfillments == null)?"<null>":this.fulfillments));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
