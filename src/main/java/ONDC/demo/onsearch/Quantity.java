
package ONDC.demo.onsearch;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "available",
    "maximum"
})
@Generated("jsonschema2pojo")
public class Quantity implements Serializable
{

    @JsonProperty("available")
    private Available available;
    @JsonProperty("maximum")
    private Maximum maximum;
    private final static long serialVersionUID = -5731019974303588687L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Quantity() {
    }

    /**
     * 
     * @param available
     * @param maximum
     */
    public Quantity(Available available, Maximum maximum) {
        super();
        this.available = available;
        this.maximum = maximum;
    }

    @JsonProperty("available")
    public Available getAvailable() {
        return available;
    }

    @JsonProperty("available")
    public void setAvailable(Available available) {
        this.available = available;
    }

    @JsonProperty("maximum")
    public Maximum getMaximum() {
        return maximum;
    }

    @JsonProperty("maximum")
    public void setMaximum(Maximum maximum) {
        this.maximum = maximum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Quantity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("available");
        sb.append('=');
        sb.append(((this.available == null)?"<null>":this.available));
        sb.append(',');
        sb.append("maximum");
        sb.append('=');
        sb.append(((this.maximum == null)?"<null>":this.maximum));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
