package ONDC.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class ElasticSearchIndexing {

    public static void main(String[] args) {
        // Elasticsearch configuration
        String hostname = "localhost";
        int port = 9200;
        String scheme = "http";
        String indexName = "products";

        // Create Elasticsearch client
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostname, port, scheme)));

        try {
            // Sample data
            List<XContentBuilder> data = Arrays.asList(
            		createSampleItemData("1", "ABC123", "category1", "Item 1", "SYM1", "Short description 1",
                            "Long description 1", Arrays.asList("image1.jpg", "image2.jpg"), "10", "100", "USD",
                            "50", "500"),
        createSampleItemData("2", "DEF456", "category2", "Item 2", "SYM2", "Short description 2",
                            "Long description 2", Arrays.asList("image3.jpg", "image4.jpg"), "5", "50", "EUR",
                            "20", "200"),
        createSampleItemData("3", "GHI789", "category3", "Item 3", "SYM3", "Short description 3",
                            "Long description 3", Arrays.asList("image5.jpg", "image6.jpg"), "15", "150", "GBP",
                            "40", "400"),
        createSampleItemData("4", "JKL012", "category1", "Item 4", "SYM4", "Short description 4",
                            "Long description 4", Arrays.asList("image7.jpg", "image8.jpg"), "8", "80", "CAD",
                            "30", "300"),
        createSampleItemData("5", "MNO345", "category2", "Item 5", "SYM5", "Short description 5",
                            "Long description 5", Arrays.asList("image9.jpg", "image10.jpg"), "12", "120", "USD",
                            "60", "600"),
        createSampleItemData("6", "PQR678", "category3", "Item 6", "SYM6", "Short description 6",
                            "Long description 6", Arrays.asList("image11.jpg", "image12.jpg"), "7", "70", "EUR",
                            "25", "250"),
        createSampleItemData("7", "STU901", "category1", "Item 7", "SYM7", "Short description 7",
                            "Long description 7", Arrays.asList("image13.jpg", "image14.jpg"), "9", "90", "GBP",
                            "45", "450"),
        createSampleItemData("8", "VWX234", "category2", "Item 8", "SYM8", "Short description 8",
                            "Long description 8", Arrays.asList("image15.jpg", "image16.jpg"), "3", "30", "CAD",
                            "15", "150"),
        createSampleItemData("9", "YZA567", "category3", "Item 9", "SYM9", "Short description 9",
                            "Long description 9", Arrays.asList("image17.jpg", "image18.jpg"), "6", "60", "USD",
                            "35", "350"),
        createSampleItemData("10", "BCD890", "category1", "Item 10", "SYM10", "Short description 10",
                            "Long description 10", Arrays.asList("image19.jpg", "image20.jpg"), "11", "110", "EUR",
                            "55", "550"),
        createSampleItemData("11", "EFG123", "category2", "Item 11", "SYM11", "Short description 11",
                            "Long description 11", Arrays.asList("image21.jpg", "image22.jpg"), "4", "40", "GBP",
                            "18", "180"),
        createSampleItemData("12", "HIJ456", "category3", "Item 12", "SYM12", "Short description 12",
                            "Long description 12", Arrays.asList("image23.jpg", "image24.jpg"), "14", "140", "CAD",
                            "75", "750"),
        createSampleItemData("13", "KLM789", "category1", "Item 13", "SYM13", "Short description 13",
                            "Long description 13", Arrays.asList("image25.jpg", "image26.jpg"), "2", "20", "USD",
                            "10", "100"),
        createSampleItemData("14", "NOP012", "category2", "Item 14", "SYM14", "Short description 14",
                            "Long description 14", Arrays.asList("image27.jpg", "image28.jpg"), "13", "130", "EUR",
                            "65", "650"),
        createSampleItemData("15", "QRS345", "category3", "Item 15", "SYM15", "Short description 15",
                            "Long description 15", Arrays.asList("image29.jpg", "image30.jpg"), "1", "10", "GBP",
                            "5", "50"),
        createSampleItemData("16", "TUV678", "category1", "Item 16", "SYM16", "Short description 16",
                            "Long description 16", Arrays.asList("image31.jpg", "image32.jpg"), "17", "170", "CAD",
                            "85", "850"),
        createSampleItemData("17", "WXY901", "category2", "Item 17", "SYM17", "Short description 17",
                            "Long description 17", Arrays.asList("image33.jpg", "image34.jpg"), "16", "160", "USD",
                            "80", "800"),
        createSampleItemData("18", "ZAB234", "category3", "Item 18", "SYM18", "Short description 18",
                            "Long description 18", Arrays.asList("image35.jpg", "image36.jpg"), "19", "190", "EUR",
                            "95", "950"),
        createSampleItemData("19", "CDE567", "category1", "Item 19", "SYM19", "Short description 19",
                            "Long description 19", Arrays.asList("image37.jpg", "image38.jpg"), "20", "200", "GBP",
                            "100", "1000"),
        createSampleItemData("20", "FGH890", "category2", "Item 20", "SYM20", "Short description 20",
                            "Long description 20", Arrays.asList("image39.jpg", "image40.jpg"), "18", "180", "CAD",
                            "90", "900"),
        createSampleItemData("21", "IJK123", "category3", "Item 21", "SYM21", "Short description 21",
                            "Long description 21", Arrays.asList("image41.jpg", "image42.jpg"), "22", "220", "USD",
                            "110", "1100"),
        createSampleItemData("22", "LMN456", "category1", "Item 22", "SYM22", "Short description 22",
                            "Long description 22", Arrays.asList("image43.jpg", "image44.jpg"), "23", "230", "EUR",
                            "115", "1150"),
        createSampleItemData("23", "OPQ789", "category2", "Item 23", "SYM23", "Short description 23",
                            "Long description 23", Arrays.asList("image45.jpg", "image46.jpg"), "24", "240", "GBP",
                            "120", "1200"),
        createSampleItemData("24", "RST012", "category3", "Item 24", "SYM24", "Short description 24",
                            "Long description 24", Arrays.asList("image47.jpg", "image48.jpg"), "25", "250", "CAD",
                            "125", "1250"),
        createSampleItemData("25", "UVW345", "category1", "Item 25", "SYM25", "Short description 25",
                            "Long description 25", Arrays.asList("image49.jpg", "image50.jpg"), "26", "260", "USD",
                            "130", "1300"),
        createSampleItemData("26", "XYZ678", "category2", "Item 26", "SYM26", "Short description 26",
                            "Long description 26", Arrays.asList("image51.jpg", "image52.jpg"), "27", "270", "EUR",
                            "135", "1350"),
        createSampleItemData("27", "ABC901", "category3", "Item 27", "SYM27", "Short description 27",
                            "Long description 27", Arrays.asList("image53.jpg", "image54.jpg"), "28", "280", "GBP",
                            "140", "1400"),
        createSampleItemData("28", "DEF234", "category1", "Item 28", "SYM28", "Short description 28",
                            "Long description 28", Arrays.asList("image55.jpg", "image56.jpg"), "29", "290", "CAD",
                            "145", "1450"),
        createSampleItemData("29", "GHI567", "category2", "Item 29", "SYM29", "Short description 29",
                            "Long description 29", Arrays.asList("image57.jpg", "image58.jpg"), "30", "300", "USD",
                            "150", "1500"),
        createSampleItemData("30", "JKL890", "category3", "Item 30", "SYM30", "Short description 30",
                            "Long description 30", Arrays.asList("image59.jpg", "image60.jpg"), "31", "310", "EUR",
                            "155", "1550"),
        createSampleItemData("31", "MNO123", "category1", "Item 31", "SYM31", "Short description 31",
                            "Long description 31", Arrays.asList("image61.jpg", "image62.jpg"), "32", "320", "GBP",
                            "160", "1600"),
        createSampleItemData("32", "PQR456", "category2", "Item 32", "SYM32", "Short description 32",
                            "Long description 32", Arrays.asList("image63.jpg", "image64.jpg"), "33", "330", "CAD",
                            "165", "1650"),
        createSampleItemData("33", "STU789", "category3", "Item 33", "SYM33", "Short description 33",
                            "Long description 33", Arrays.asList("image65.jpg", "image66.jpg"), "34", "340", "USD",
                            "170", "1700"),
        createSampleItemData("34", "VWX012", "category1", "Item 34", "SYM34", "Short description 34",
                            "Long description 34", Arrays.asList("image67.jpg", "image68.jpg"), "35", "350", "EUR",
                            "175", "1750"),
        createSampleItemData("35", "YZA345", "category2", "Item 35", "SYM35", "Short description 35",
                            "Long description 35", Arrays.asList("image69.jpg", "image70.jpg"), "36", "360", "GBP",
                            "180", "1800"),
        createSampleItemData("36", "BCD678", "category3", "Item 36", "SYM36", "Short description 36",
                            "Long description 36", Arrays.asList("image71.jpg", "image72.jpg"), "37", "370", "CAD",
                            "185", "1850"),
        createSampleItemData("37", "EFG901", "category1", "Item 37", "SYM37", "Short description 37",
                            "Long description 37", Arrays.asList("image73.jpg", "image74.jpg"), "38", "380", "USD",
                            "190", "1900"),
        createSampleItemData("38", "HIJ234", "category2", "Item 38", "SYM38", "Short description 38",
                            "Long description 38", Arrays.asList("image75.jpg", "image76.jpg"), "39", "390", "EUR",
                            "195", "1950"),
        createSampleItemData("39", "KLM567", "category3", "Item 39", "SYM39", "Short description 39",
                            "Long description 39", Arrays.asList("image77.jpg", "image78.jpg"), "40", "400", "GBP",
                            "200", "2000"),
        createSampleItemData("40", "NOP890", "category1", "Item 40", "SYM40", "Short description 40",
                            "Long description 40", Arrays.asList("image79.jpg", "image80.jpg"), "41", "410", "CAD",
                            "205", "2050"),
        createSampleItemData("41", "QRS123", "category2", "Item 41", "SYM41", "Short description 41",
                            "Long description 41", Arrays.asList("image81.jpg", "image82.jpg"), "42", "420", "USD",
                            "210", "2100"),
        createSampleItemData("42", "TUV456", "category3", "Item 42", "SYM42", "Short description 42",
                            "Long description 42", Arrays.asList("image83.jpg", "image84.jpg"), "43", "430", "EUR",
                            "215", "2150"),
        createSampleItemData("43", "WXY789", "category1", "Item 43", "SYM43", "Short description 43",
                            "Long description 43", Arrays.asList("image85.jpg", "image86.jpg"), "44", "440", "GBP",
                            "220", "2200"),
        createSampleItemData("44", "ZAB012", "category2", "Item 44", "SYM44", "Short description 44",
                            "Long description 44", Arrays.asList("image87.jpg", "image88.jpg"), "45", "450", "CAD",
                            "225", "2250"),
        createSampleItemData("45", "CDE345", "category3", "Item 45", "SYM45", "Short description 45",
                            "Long description 45", Arrays.asList("image89.jpg", "image90.jpg"), "46", "460", "USD",
                            "230", "2300"),
        createSampleItemData("46", "FGH678", "category1", "Item 46", "SYM46", "Short description 46",
                            "Long description 46", Arrays.asList("image91.jpg", "image92.jpg"), "47", "470", "EUR",
                            "235", "2350"),
        createSampleItemData("47", "IJK901", "category2", "Item 47", "SYM47", "Short description 47",
                            "Long description 47", Arrays.asList("image93.jpg", "image94.jpg"), "48", "480", "GBP",
                            "240", "2400"),
        createSampleItemData("48", "LMN234", "category3", "Item 48", "SYM48", "Short description 48",
                            "Long description 48", Arrays.asList("image95.jpg", "image96.jpg"), "49", "490", "CAD",
                            "245", "2450"),
        createSampleItemData("49", "OPQ567", "category1", "Item 49", "SYM49", "Short description 49",
                            "Long description 49", Arrays.asList("image97.jpg", "image98.jpg"), "50", "500", "USD",
                            "250", "2500"),
        createSampleItemData("50", "RST890", "category2", "Item 50", "SYM50", "Short description 50",
                            "Long description 50", Arrays.asList("image99.jpg", "image100.jpg"), "51", "510", "EUR",
                            "255", "2550"));

                    // Add more sample data here...
            

            // Bulk request for multiple documents
            BulkRequest bulkRequest = new BulkRequest();
            for (XContentBuilder item : data) {
                IndexRequest indexRequest = new IndexRequest(indexName)
                        .source(item);
                bulkRequest.add(indexRequest);
            }

            // Execute bulk request
            BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

            // Process response
            if (bulkResponse.hasFailures()) {
                System.out.println("Failed to insert data into Elasticsearch.");
            } else {
                System.out.println("Data inserted successfully into Elasticsearch.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the client connection
            try {
                client.close();
            } catch (IOException e) {
                System.out.println("Error closing the Elasticsearch client: " + e.getMessage());
            }
        }
    }

    // Helper method to create a sample item data
    private static XContentBuilder createSampleItemData(String itemId, String itemCode, String categoryId,
                                                        String itemName, String itemSymbol, String itemShortDesc, String itemLongDesc, List<String> itemImages,
                                                        String itemCount, String itemMaxAvailableCount, String itemCurrency, String itemValue,
                                                        String itemMaxValue) throws IOException {
        XContentBuilder itemData = XContentFactory.jsonBuilder();
        itemData.startObject()
                .field("item_id", itemId)
                .field("item_code", itemCode)
                .field("item_category_id", categoryId)
                .field("item_name", itemName)
                .field("item_symbol", itemSymbol)
                .field("item_short_desc", itemShortDesc)
                .field("item_long_desc", itemLongDesc)
                .field("item_images", itemImages.toArray())
                .field("item_count", itemCount)
                .field("item_max_available_count", itemMaxAvailableCount)
                .field("item_currency", itemCurrency)
                .field("item_value", itemValue)
                .field("item_max_value", itemMaxValue)
                .field("item_location_id", "location_id_value")
                .field("item_recommended", false)
                .field("item_ondc_org_returnable", true)
                .field("item_ondc_org_cancellable", true)
                .field("item_ondc_org_seller_pickup_return", true)
                .field("item_ondc_org_time_to_ship", 3)
                .field("item_ondc_org_available_on_cod", false)
                .field("item_ondc_org_contact_details_consumer_care", "consumer_care_value")
                .field("item_statutory_reqs_packaged_commodities_manufacturer_or_packer_name", "manufacturer_packer_name_value")
                .field("item_statutory_reqs_packaged_commodities_manufacturer_or_packer_address", "manufacturer_packer_address_value")
                .field("item_statutory_reqs_packaged_commodities_common_or_generic_name_of_commodity", "common_generic_name_value")
                .field("item_statutory_reqs_packaged_commodities_net_quantity_or_measure_of_commodity_in_pkg", "net_quantity_value")
                .field("item_statutory_reqs_packaged_commodities_month_year_of_manufacture_packing_import", "month_year_value")
                .field("item_prepackaged_food_nutritional_info", "nutritional_info_value")
                .field("item_prepackaged_food_additives_info", "additives_info_value")
                .field("item_prepackaged_food_brand_owner_FSSAI_license_no", "FSSAI_license_no_value")
                .field("item_prepackaged_food_other_FSSAI_license_no", "other_FSSAI_license_no_value")
                .field("item_reqs_veggies_fruits_net_quantity", "veggies_fruits_net_quantity_value")
                .field("items_tags_veg", "veg_tag_value")
                .field("item_tags_non_veg", "non_veg_tag_value")
                .field("bpp_fulfillments_id", "fulfillments_id_value")
                .field("bpp_fulfillments_type", "fulfillments_type_value")
                .field("bpp_descriptor_name", "descriptor_name_value")
                .field("bpp_descriptor_symbol", "descriptor_symbol_value")
                .field("bpp_descriptor_short_desc", "descriptor_short_desc_value")
                .field("bpp_descriptor_long_desc", "descriptor_long_desc_value")
                .field("bpp_descriptor_images", "descriptor_images_value")
                .field("bpp_providers_id", "providers_id_value")
                .field("bpp_providers_time_label", "providers_time_label_value")
                .field("bpp_providers_time_timestamp", "providers_time_timestamp_value")
                .field("bpp_providers_descriptor_name", "providers_descriptor_name_value")
                .field("bpp_providers_descriptor_symbol", "providers_descriptor_symbol_value")
                .field("bpp_providers_descriptor_short_desc", "providers_descriptor_short_desc_value")
                .field("bpp_providers_descriptor_long_desc", "providers_descriptor_long_desc_value")
                .field("bpp_providers_descriptor_images", "providers_descriptor_images_value")
                .field("bpp_providers_ondc_org_fssai_license_no", "providers_ondc_org_fssai_license_no_value")
                .field("bpp_providers_ttl", 5)
                .field("bpp_providers_locations_id", "locations_id_value")
                .field("bpp_providers_locations_gps", "locations_gps_value")
                .field("bpp_providers_locations_address_locality", "locations_address_locality_value")
                .field("bpp_providers_locations_address_street", "locations_address_street_value")
                .field("bpp_providers_locations_address_city", "locations_address_city_value")
                .field("bpp_providers_locations_address_area_code", "locations_address_area_code_value")
                .field("bpp_providers_locations_address_state", "locations_address_state_value")
                .field("bpp_providers_locations_circle_gps", "locations_circle_gps_value")
                .field("bpp_providers_locations_radius_unit", "locations_radius_unit_value")
                .field("bpp_providers_locations_radius_value", 10)
                .field("bpp_providers_locations_time_days", "locations_time_days_value")
                .field("bpp_providers_locations_time_schedule_holidays", "locations_time_schedule_holidays_value")
                .field("bpp_providers_locations_time_schedule_frequency", "locations_time_schedule_frequency_value")
                .field("bpp_providers_locations_time_schedule_frequency_times", "locations_time_schedule_frequency_times_value")
                .field("bpp_providers_locations_time_schedule_frequency_range_start", "locations_time_schedule_frequency_range_start_value")
                .field("bpp_providers_locations_time_schedule_frequency_range_end", "locations_time_schedule_frequency_range_end_value")
                .field("fulfillments_contact_phone", "contact_phone_value")
                .field("fulfillments_contact_email", "contact_email_value")
                .field("fulfillments_tags_code", "tags_code_value")
                .field("fulfillments_tags_list", "tags_list_value")
                .endObject();

        return itemData;
    }
}
