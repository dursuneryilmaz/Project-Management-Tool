package com.dursuneryilmaz.duscrumtool.domain;

import javax.persistence.*;

@Table(name = "users_roles")
public class UsersRoles {
    private String userId;
    private String roleId;
}
