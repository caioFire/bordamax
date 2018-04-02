package com.bordamax.dto;

/**
 * Created by fire on 28/03/18.
 */
public class FiltroDto {

    private String codigo;
    private String nome;
    private String cliente;
    private String localizacao;
    private String login;
    private Boolean status;
    private Boolean portfolio;


    private int paginaAtual;
    private Boolean ascendente;
    private String campoOrderBy;
    private int  tamanhoPagina;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Boolean portfolio) {
        this.portfolio = portfolio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public Boolean getAscendente() {
        return ascendente;
    }

    public void setAscendente(Boolean ascendente) {
        this.ascendente = ascendente;
    }

    public String getCampoOrderBy() {
        return campoOrderBy;
    }

    public void setCampoOrderBy(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }

    public int getTamanhoPagina() {
        return tamanhoPagina;
    }

    public void setTamanhoPagina(int tamanhoPagina) {
        this.tamanhoPagina = tamanhoPagina;
    }
}
