package com.dursuneryilmaz.duscrumtool.domain;

import java.util.Date;

public class Sprint {
    private Long id;
    private String publicId;
    private String productId;
    private Product product;
    private SprintBacklog backlog;
    private Date startDate;
    private Date endDate;
}
