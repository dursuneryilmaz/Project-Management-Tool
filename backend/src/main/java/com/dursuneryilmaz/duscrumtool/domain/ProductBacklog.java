package com.dursuneryilmaz.duscrumtool.domain;

import java.util.List;

public class ProductBacklog {
    private long id;
    private String publicId;
    private String productId;
    private Product product;
    private List<Task> taskList;
}
