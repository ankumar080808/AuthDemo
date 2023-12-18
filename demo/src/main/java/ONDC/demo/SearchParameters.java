
package ONDC.demo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;



public class SearchParameters implements Serializable
{
	 
    private final static long serialVersionUID = -5587765818182034834L;
    
    @JsonProperty("domain")
    private String domain;
    
    @JsonProperty("subscriber_id")
    private String subscriberId;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("type")
    private Enum type;
    
    @JsonProperty("city")
    private String city;
  
    
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

   

    public Enum getType() {
		return type;
	}

	public void setType(Enum type) {
		this.type = type;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

	@Override
	public String toString() {
		return "SearchParameters [domain=" + domain + ", subscriberId=" + subscriberId + ", country=" + country
				+ ", type=" + type + ", city=" + city + "]";
	}

   

}
