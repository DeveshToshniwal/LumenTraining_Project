package com.example.project.model.auth;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleTypes name;

    public Role() {

    }

    public Role(RoleTypes name) {
        this.name = name;
    }

    public RoleTypes getName() {
        return name;
    }

    public void setName(RoleTypes name) {
        this.name = name;
    }
}
