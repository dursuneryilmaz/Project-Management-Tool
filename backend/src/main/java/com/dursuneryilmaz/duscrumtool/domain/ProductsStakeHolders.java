package com.dursuneryilmaz.duscrumtool.domain;

import javax.persistence.Table;

@Table(name = "products_stake_holders")
public class ProductsStakeHolders {
    private String productId;
    private String userId;
}
