
package ONDC.demo.onsearch;

import java.io.Serializable;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "manufacturer_or_packer_name",
    "manufacturer_or_packer_address",
    "common_or_generic_name_of_commodity",
    "net_quantity_or_measure_of_commodity_in_pkg",
    "month_year_of_manufacture_packing_import"
})
@Generated("jsonschema2pojo")
public class OndcOrgStatutoryReqsPackagedCommodities implements Serializable
{

    @JsonProperty("manufacturer_or_packer_name")
    private String manufacturerOrPackerName;
    @JsonProperty("manufacturer_or_packer_address")
    private String manufacturerOrPackerAddress;
    @JsonProperty("common_or_generic_name_of_commodity")
    private String commonOrGenericNameOfCommodity;
    @JsonProperty("net_quantity_or_measure_of_commodity_in_pkg")
    private String netQuantityOrMeasureOfCommodityInPkg;
    @JsonProperty("month_year_of_manufacture_packing_import")
    private String monthYearOfManufacturePackingImport;
    private final static long serialVersionUID = 2992440212834356429L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OndcOrgStatutoryReqsPackagedCommodities() {
    }

    /**
     * 
     * @param commonOrGenericNameOfCommodity
     * @param netQuantityOrMeasureOfCommodityInPkg
     * @param manufacturerOrPackerAddress
     * @param monthYearOfManufacturePackingImport
     * @param manufacturerOrPackerName
     */
    public OndcOrgStatutoryReqsPackagedCommodities(String manufacturerOrPackerName, String manufacturerOrPackerAddress, String commonOrGenericNameOfCommodity, String netQuantityOrMeasureOfCommodityInPkg, String monthYearOfManufacturePackingImport) {
        super();
        this.manufacturerOrPackerName = manufacturerOrPackerName;
        this.manufacturerOrPackerAddress = manufacturerOrPackerAddress;
        this.commonOrGenericNameOfCommodity = commonOrGenericNameOfCommodity;
        this.netQuantityOrMeasureOfCommodityInPkg = netQuantityOrMeasureOfCommodityInPkg;
        this.monthYearOfManufacturePackingImport = monthYearOfManufacturePackingImport;
    }

    @JsonProperty("manufacturer_or_packer_name")
    public String getManufacturerOrPackerName() {
        return manufacturerOrPackerName;
    }

    @JsonProperty("manufacturer_or_packer_name")
    public void setManufacturerOrPackerName(String manufacturerOrPackerName) {
        this.manufacturerOrPackerName = manufacturerOrPackerName;
    }

    @JsonProperty("manufacturer_or_packer_address")
    public String getManufacturerOrPackerAddress() {
        return manufacturerOrPackerAddress;
    }

    @JsonProperty("manufacturer_or_packer_address")
    public void setManufacturerOrPackerAddress(String manufacturerOrPackerAddress) {
        this.manufacturerOrPackerAddress = manufacturerOrPackerAddress;
    }

    @JsonProperty("common_or_generic_name_of_commodity")
    public String getCommonOrGenericNameOfCommodity() {
        return commonOrGenericNameOfCommodity;
    }

    @JsonProperty("common_or_generic_name_of_commodity")
    public void setCommonOrGenericNameOfCommodity(String commonOrGenericNameOfCommodity) {
        this.commonOrGenericNameOfCommodity = commonOrGenericNameOfCommodity;
    }

    @JsonProperty("net_quantity_or_measure_of_commodity_in_pkg")
    public String getNetQuantityOrMeasureOfCommodityInPkg() {
        return netQuantityOrMeasureOfCommodityInPkg;
    }

    @JsonProperty("net_quantity_or_measure_of_commodity_in_pkg")
    public void setNetQuantityOrMeasureOfCommodityInPkg(String netQuantityOrMeasureOfCommodityInPkg) {
        this.netQuantityOrMeasureOfCommodityInPkg = netQuantityOrMeasureOfCommodityInPkg;
    }

    @JsonProperty("month_year_of_manufacture_packing_import")
    public String getMonthYearOfManufacturePackingImport() {
        return monthYearOfManufacturePackingImport;
    }

    @JsonProperty("month_year_of_manufacture_packing_import")
    public void setMonthYearOfManufacturePackingImport(String monthYearOfManufacturePackingImport) {
        this.monthYearOfManufacturePackingImport = monthYearOfManufacturePackingImport;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OndcOrgStatutoryReqsPackagedCommodities.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("manufacturerOrPackerName");
        sb.append('=');
        sb.append(((this.manufacturerOrPackerName == null)?"<null>":this.manufacturerOrPackerName));
        sb.append(',');
        sb.append("manufacturerOrPackerAddress");
        sb.append('=');
        sb.append(((this.manufacturerOrPackerAddress == null)?"<null>":this.manufacturerOrPackerAddress));
        sb.append(',');
        sb.append("commonOrGenericNameOfCommodity");
        sb.append('=');
        sb.append(((this.commonOrGenericNameOfCommodity == null)?"<null>":this.commonOrGenericNameOfCommodity));
        sb.append(',');
        sb.append("netQuantityOrMeasureOfCommodityInPkg");
        sb.append('=');
        sb.append(((this.netQuantityOrMeasureOfCommodityInPkg == null)?"<null>":this.netQuantityOrMeasureOfCommodityInPkg));
        sb.append(',');
        sb.append("monthYearOfManufacturePackingImport");
        sb.append('=');
        sb.append(((this.monthYearOfManufacturePackingImport == null)?"<null>":this.monthYearOfManufacturePackingImport));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
