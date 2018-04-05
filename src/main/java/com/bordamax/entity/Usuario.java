package com.bordamax.entity;

import com.bordamax.enuns.TipoUsuario;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by fire on 13/03/18.
 */
@Entity
@Table(schema = "public", name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "id_usuario")
    private Long id;

    @Column( name = "nome",length=50, nullable = false)
    private String nome;

    @Column( name = "identificacao", length=20, nullable = false)
    private String identificacao;

    @Column( name = "senha", length = 256, nullable = false)
    private String senha;

    @Column( name = "email", length = 50, nullable = false)
    private String email;

    @Column( name = "status", nullable = false)
    private Boolean status = true;

    @Column( name = "admin", nullable = false)
    private Boolean admin = true;

    @Column( name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
