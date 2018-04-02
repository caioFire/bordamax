package com.bordamax.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by fire on 02/04/18.
 */


@Entity
@Table(schema = "public", name = "imagem")
public class Imagem {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "id_imagem")
    private Long id;

    @Column( name = "url_imagem",length=100)
    private String urlImagen;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id", scope=Amostra.class)
    @ManyToOne
    @JoinColumn(name = "id_amostra", referencedColumnName = "id_amostra")
    private Amostra amostra;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Amostra getAmostra() {
        return amostra;
    }

    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
    }
}
