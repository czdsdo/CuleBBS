package com.example.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cule_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true,nullable = false)
    private String name;
    private  String description;
    @Transient
    private  Integer selsected;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<AdminUser> adminUsers=new HashSet<>();
    @JsonIgnore
    @JoinTable(name="cule_role_permission",
    joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "permissions_id",referencedColumnName = "id")})
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Permission>permissions=new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSelsected() {
        return selsected;
    }

    public void setSelsected(Integer selsected) {
        this.selsected = selsected;
    }

    public Set<AdminUser> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(Set<AdminUser> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{"+
                "id="+id+
                ",name="+name+
                ",description="+description+
                ",selected="+selsected+
                "}";
    }
}
