
package ONDC.demo.onsearch;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "veg",
    "non_veg"
})
@Generated("jsonschema2pojo")
public class Tags implements Serializable
{

    @JsonProperty("veg")
    private String veg;
    @JsonProperty("non_veg")
    private String nonVeg;
    private final static long serialVersionUID = -1322030418797866102L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tags() {
    }

    /**
     * 
     * @param veg
     * @param nonVeg
     */
    public Tags(String veg, String nonVeg) {
        super();
        this.veg = veg;
        this.nonVeg = nonVeg;
    }

    @JsonProperty("veg")
    public String getVeg() {
        return veg;
    }

    @JsonProperty("veg")
    public void setVeg(String veg) {
        this.veg = veg;
    }

    @JsonProperty("non_veg")
    public String getNonVeg() {
        return nonVeg;
    }

    @JsonProperty("non_veg")
    public void setNonVeg(String nonVeg) {
        this.nonVeg = nonVeg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Tags.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("veg");
        sb.append('=');
        sb.append(((this.veg == null)?"<null>":this.veg));
        sb.append(',');
        sb.append("nonVeg");
        sb.append('=');
        sb.append(((this.nonVeg == null)?"<null>":this.nonVeg));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
