package ONDC.demo;

public class Lookup implements java.io.Serializable{

	private static final long serialVersionUID = 1359655867397629544L;
	
	private int brId;
	private String subscriberId;
	private String subscriberUrl;
	private String type;
	private String domain;
	private String city;
	private String country;
	private String signingPublicKey;
	private String encrPublicKey;
	private String validFrom;
	private String validUntil;
	private String status;
	private String created;
	private String updated;
	private String ukId;
	
	
	
	public int getBrId() {
		return brId;
	}
	public void setBrId(int brId) {
		this.brId = brId;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getSubscriberUrl() {
		return subscriberUrl;
	}
	public void setSubscriberUrl(String subscriberUrl) {
		this.subscriberUrl = subscriberUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSigningPublicKey() {
		return signingPublicKey;
	}
	public void setSigningPublicKey(String signingPublicKey) {
		this.signingPublicKey = signingPublicKey;
	}
	public String getEncrPublicKey() {
		return encrPublicKey;
	}
	public void setEncrPublicKey(String encrPublicKey) {
		this.encrPublicKey = encrPublicKey;
	}
	public String getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}
	public String getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(String validUntil) {
		this.validUntil = validUntil;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUkId() {
		return ukId;
	}
	public void setUkId(String ukId) {
		this.ukId = ukId;
	}
	
	
	
}
