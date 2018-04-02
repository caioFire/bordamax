package com.bordamax.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fire on 13/03/18.
 */

@Entity
@Table(schema = "public", name = "amostra")
public class Amostra implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column( name = "id_amostra")
    private Long id;

    @Column( name = "codigo",length=20, nullable = false, unique = true)
    private String codigo;

    @Column( name = "descricao",length=50)
    private String descricao;

    @Column( name = "url_imagem",length=50)
    private String urlImagem;

    @Column( name = "portfolio", nullable = false)
    private Boolean portfolio = false;

    @Column( name = "status", nullable = false)
    private Boolean status = true;

    @ManyToOne
    @Basic(optional = false)
    @JoinColumn(name = "id_localizacao")
    private Localizacao localizacao;

    @ManyToOne
    @Basic(optional = false)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Boolean portfolio) {
        this.portfolio = portfolio;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean ativo) {
        this.status = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
