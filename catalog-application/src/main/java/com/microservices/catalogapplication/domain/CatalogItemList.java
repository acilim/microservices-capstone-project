package com.microservices.catalogapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CatalogItemList {

    private List<CatalogItem> catalogItems;

}
