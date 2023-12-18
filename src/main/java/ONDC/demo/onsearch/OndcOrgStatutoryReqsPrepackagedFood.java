
package ONDC.demo.onsearch;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "nutritional_info",
    "additives_info",
    "brand_owner_FSSAI_license_no",
    "other_FSSAI_license_no",
    "importer_FSSAI_license_no"
})
@Generated("jsonschema2pojo")
public class OndcOrgStatutoryReqsPrepackagedFood implements Serializable
{

    @JsonProperty("nutritional_info")
    private String nutritionalInfo;
    @JsonProperty("additives_info")
    private String additivesInfo;
    @JsonProperty("brand_owner_FSSAI_license_no")
    private String brandOwnerFSSAILicenseNo;
    @JsonProperty("other_FSSAI_license_no")
    private String otherFSSAILicenseNo;
    @JsonProperty("importer_FSSAI_license_no")
    private String importerFSSAILicenseNo;
    private final static long serialVersionUID = 75502358362826602L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OndcOrgStatutoryReqsPrepackagedFood() {
    }

    /**
     * 
     * @param nutritionalInfo
     * @param importerFSSAILicenseNo
     * @param additivesInfo
     * @param otherFSSAILicenseNo
     * @param brandOwnerFSSAILicenseNo
     */
    public OndcOrgStatutoryReqsPrepackagedFood(String nutritionalInfo, String additivesInfo, String brandOwnerFSSAILicenseNo, String otherFSSAILicenseNo, String importerFSSAILicenseNo) {
        super();
        this.nutritionalInfo = nutritionalInfo;
        this.additivesInfo = additivesInfo;
        this.brandOwnerFSSAILicenseNo = brandOwnerFSSAILicenseNo;
        this.otherFSSAILicenseNo = otherFSSAILicenseNo;
        this.importerFSSAILicenseNo = importerFSSAILicenseNo;
    }

    @JsonProperty("nutritional_info")
    public String getNutritionalInfo() {
        return nutritionalInfo;
    }

    @JsonProperty("nutritional_info")
    public void setNutritionalInfo(String nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    @JsonProperty("additives_info")
    public String getAdditivesInfo() {
        return additivesInfo;
    }

    @JsonProperty("additives_info")
    public void setAdditivesInfo(String additivesInfo) {
        this.additivesInfo = additivesInfo;
    }

    @JsonProperty("brand_owner_FSSAI_license_no")
    public String getBrandOwnerFSSAILicenseNo() {
        return brandOwnerFSSAILicenseNo;
    }

    @JsonProperty("brand_owner_FSSAI_license_no")
    public void setBrandOwnerFSSAILicenseNo(String brandOwnerFSSAILicenseNo) {
        this.brandOwnerFSSAILicenseNo = brandOwnerFSSAILicenseNo;
    }

    @JsonProperty("other_FSSAI_license_no")
    public String getOtherFSSAILicenseNo() {
        return otherFSSAILicenseNo;
    }

    @JsonProperty("other_FSSAI_license_no")
    public void setOtherFSSAILicenseNo(String otherFSSAILicenseNo) {
        this.otherFSSAILicenseNo = otherFSSAILicenseNo;
    }

    @JsonProperty("importer_FSSAI_license_no")
    public String getImporterFSSAILicenseNo() {
        return importerFSSAILicenseNo;
    }

    @JsonProperty("importer_FSSAI_license_no")
    public void setImporterFSSAILicenseNo(String importerFSSAILicenseNo) {
        this.importerFSSAILicenseNo = importerFSSAILicenseNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OndcOrgStatutoryReqsPrepackagedFood.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("nutritionalInfo");
        sb.append('=');
        sb.append(((this.nutritionalInfo == null)?"<null>":this.nutritionalInfo));
        sb.append(',');
        sb.append("additivesInfo");
        sb.append('=');
        sb.append(((this.additivesInfo == null)?"<null>":this.additivesInfo));
        sb.append(',');
        sb.append("brandOwnerFSSAILicenseNo");
        sb.append('=');
        sb.append(((this.brandOwnerFSSAILicenseNo == null)?"<null>":this.brandOwnerFSSAILicenseNo));
        sb.append(',');
        sb.append("otherFSSAILicenseNo");
        sb.append('=');
        sb.append(((this.otherFSSAILicenseNo == null)?"<null>":this.otherFSSAILicenseNo));
        sb.append(',');
        sb.append("importerFSSAILicenseNo");
        sb.append('=');
        sb.append(((this.importerFSSAILicenseNo == null)?"<null>":this.importerFSSAILicenseNo));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
