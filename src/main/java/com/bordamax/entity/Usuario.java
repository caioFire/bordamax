package com.bordamax.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fire on 13/03/18.
 */
@Entity
@Table(schema = "public", name = "usuario")
public class Usuario implements Serializable , UserDetails{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "id_usuario")
    private Long id;

    @Column( name = "nome",length=25, nullable = false)
    private String nome;

    @Column( name = "identificacao", length=25, nullable = false)
    private String identificacao;

    @Column( name = "senha", length = 256, nullable = false)
    private String senha;

    @Column( name = "email", length = 25, nullable = false)
    private String email;

    @Column( name = "status")
    private Boolean status = true;

    @Column( name = "admin")
    private Boolean admin;

    @ManyToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST }, fetch = FetchType.LAZY)
    @JoinTable(name= "usuarios_roles", joinColumns = @JoinColumn(
            name = "usuario_id", referencedColumnName = "id_usuario"),
            inverseJoinColumns = @JoinColumn(
            name = "role_id", referencedColumnName = "id_role"))
    private List<Roles> roles;

    public void addRole(Roles role){
        if(this.roles == null){
            this.roles = new ArrayList<>();
        }
        this.roles.add(role);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean ativo) {
        this.status = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (Collection<? extends GrantedAuthority>) this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.identificacao;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
