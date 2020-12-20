package com.dursuneryilmaz.duscrumtool.domain;

import javax.persistence.Table;

@Table(name = "products_owners")
public class ProductsOwners {
    private String productId;
    private String userId;
}
