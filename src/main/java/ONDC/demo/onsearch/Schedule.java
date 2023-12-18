
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
    "holidays",
    "frequency",
    "times"
})
@Generated("jsonschema2pojo")
public class Schedule implements Serializable
{

    @JsonProperty("holidays")
    private List<String> holidays = new ArrayList<String>();
    @JsonProperty("frequency")
    private String frequency;
    @JsonProperty("times")
    private List<String> times = new ArrayList<String>();
    private final static long serialVersionUID = -5725168036747237007L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Schedule() {
    }

    /**
     * 
     * @param times
     * @param holidays
     * @param frequency
     */
    public Schedule(List<String> holidays, String frequency, List<String> times) {
        super();
        this.holidays = holidays;
        this.frequency = frequency;
        this.times = times;
    }

    @JsonProperty("holidays")
    public List<String> getHolidays() {
        return holidays;
    }

    @JsonProperty("holidays")
    public void setHolidays(List<String> holidays) {
        this.holidays = holidays;
    }

    @JsonProperty("frequency")
    public String getFrequency() {
        return frequency;
    }

    @JsonProperty("frequency")
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @JsonProperty("times")
    public List<String> getTimes() {
        return times;
    }

    @JsonProperty("times")
    public void setTimes(List<String> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Schedule.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("holidays");
        sb.append('=');
        sb.append(((this.holidays == null)?"<null>":this.holidays));
        sb.append(',');
        sb.append("frequency");
        sb.append('=');
        sb.append(((this.frequency == null)?"<null>":this.frequency));
        sb.append(',');
        sb.append("times");
        sb.append('=');
        sb.append(((this.times == null)?"<null>":this.times));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
