package com.dursuneryilmaz.duscrumtool.domain;

import javax.persistence.Table;

@Table(name = "products_managers")
public class ProductsManagers {
    private String productId;
    private String userId;
}
