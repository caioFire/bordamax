package com.bordamax.entity;

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

    @Column( name = "status", nullable = false)
    private Boolean status = true;

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
}
