package com.bordamax.dto;

import com.bordamax.entity.Amostra;
import com.bordamax.entity.Cliente;
import com.bordamax.entity.Localizacao;
import com.bordamax.entity.Usuario;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by fire on 25/04/18.
 */
public class ResponseDto {

    private Long qtdeRegistros;
    private List<Amostra> listaAmostras = new ArrayList<>();
    private List<Cliente> listaCliente = new ArrayList<>();
    private List<Usuario> listaUsuario = new ArrayList<>();
    private List<Localizacao> listaLocalizacao = new ArrayList<>();


    public Long getQtdeRegistros() {
        return qtdeRegistros;
    }

    public void setQtdeRegistros(Long qtdeRegistros) {
        this.qtdeRegistros = qtdeRegistros;
    }

    public List<Amostra> getListaAmostras() {
        return listaAmostras;
    }

    public void setListaAmostras(List<Amostra> listaAmostras) {
        this.listaAmostras = listaAmostras;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<Localizacao> getListaLocalizacao() {
        return listaLocalizacao;
    }

    public void setListaLocalizacao(List<Localizacao> listaLocalizacao) {
        this.listaLocalizacao = listaLocalizacao;
    }
}
