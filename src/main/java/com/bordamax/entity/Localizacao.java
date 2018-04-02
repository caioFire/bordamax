package com.bordamax.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fire on 13/03/18.
 */

@Entity
@Table(schema = "public", name = "localizacao")
public class Localizacao implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "id_localizacao")
    private Long id;

    @Column( name = "nome",length=50, nullable = false)
    private String nome;

    @Column( name = "status", nullable = false)
    private Boolean status = true;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean ativo) {
        this.status = ativo;
    }
}
