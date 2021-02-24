package com.microservices.productapplication.domain;

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
