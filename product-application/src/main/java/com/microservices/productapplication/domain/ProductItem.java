package com.microservices.productapplication.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductItem {

    @JsonProperty("uniq_id")
    private String id;

    private String sku;

    @JsonProperty("name_title")
    private String nameAndTitle;

    private String description;

    @JsonProperty("list_price")
    private String listPrice;

    @JsonProperty("sale_price")
    private String salePrice;

    private String category;

    @JsonProperty("category_tree")
    private String categoryTree;

    @JsonProperty("average_product_rating")
    private String averageProductRating;

    @JsonProperty("product_url")
    private String productUrl;

    @JsonProperty("product_image_urls")
    private String productImageUrls;

    private String brand;

    @JsonProperty("total_number_reviews")
    private String totalNumberOfReviews;

    @JsonProperty("Reviews")
    private String reviews;

}
