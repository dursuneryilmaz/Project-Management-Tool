package com.dursuneryilmaz.duscrumtool.domain;

import javax.persistence.Table;

@Table(name = "products_developers")
public class ProductDevelopers {
    private String productId;
    private String userId;
}
