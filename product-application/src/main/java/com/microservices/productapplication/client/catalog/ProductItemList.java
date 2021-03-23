package com.microservices.productapplication.client.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ProductItemList {

    @JsonProperty("catalogItems")
    private List<ProductItem> productItems;

}
