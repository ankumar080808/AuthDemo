
package ONDC.demo.onsearch;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "gps",
    "address",
    "circle",
    "time"
})
@Generated("jsonschema2pojo")
public class Location implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("gps")
    private String gps;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("circle")
    private Circle circle;
    @JsonProperty("time")
    private Time__1 time;
    private final static long serialVersionUID = 939258309406284328L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Location() {
    }

    /**
     * 
     * @param address
     * @param id
     * @param gps
     * @param time
     * @param circle
     */
    public Location(String id, String gps, Address address, Circle circle, Time__1 time) {
        super();
        this.id = id;
        this.gps = gps;
        this.address = address;
        this.circle = circle;
        this.time = time;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("gps")
    public String getGps() {
        return gps;
    }

    @JsonProperty("gps")
    public void setGps(String gps) {
        this.gps = gps;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("circle")
    public Circle getCircle() {
        return circle;
    }

    @JsonProperty("circle")
    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    @JsonProperty("time")
    public Time__1 getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Time__1 time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Location.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("gps");
        sb.append('=');
        sb.append(((this.gps == null)?"<null>":this.gps));
        sb.append(',');
        sb.append("address");
        sb.append('=');
        sb.append(((this.address == null)?"<null>":this.address));
        sb.append(',');
        sb.append("circle");
        sb.append('=');
        sb.append(((this.circle == null)?"<null>":this.circle));
        sb.append(',');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null)?"<null>":this.time));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
