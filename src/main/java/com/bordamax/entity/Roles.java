package com.bordamax.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "roles")
public class Roles implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "id_role")
    private Long id;

    @Column( name = "role",length=50, nullable = false)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return this.role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}