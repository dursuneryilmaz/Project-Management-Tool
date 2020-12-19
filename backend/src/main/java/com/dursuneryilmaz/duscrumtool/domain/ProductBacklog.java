package com.dursuneryilmaz.duscrumtool.domain;

import java.io.Serializable;
import java.util.List;

public class ProductBacklog  implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1169418847857694261L;
    private long id;
    private String publicId;
    private String productId;
    private Product product;
    private List<Task> taskList;
}
