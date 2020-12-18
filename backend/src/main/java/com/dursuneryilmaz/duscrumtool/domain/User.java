package com.dursuneryilmaz.duscrumtool.domain;

import java.util.List;

public class User {
    private Long id;
    private String publicId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Role> roleList;
}
