package com.dursuneryilmaz.duscrumtool.domain;

import java.io.Serializable;
import java.util.Date;

public class Sprint implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 8625539504296577658L;
    private Long id;
    private String publicId;
    private String productId;
    private Product product;
    private SprintBacklog backlog;
    private Date startDate;
    private Date endDate;
}
