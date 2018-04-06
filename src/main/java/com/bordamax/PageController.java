package com.bordamax;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by fire on 13/03/18.
 */

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() { return "login";}

    @GetMapping("/home")
    public String home() { return "view/home";}

    @GetMapping("/usuarios")
    public String usuarios() {
        return "view/usuario/usuarios";
    }
    @GetMapping("/usuarioModal")
    public String newUsuario() { return "view/usuario/usuarioModal";}

    @GetMapping("/amostras")
    public String amostras() {
        return "view/amostra/amostras";
    }
    @GetMapping("/amostraModal")
    public String newAmostra() {
        return "view/amostra/amostraModal";
    }
    @GetMapping("/amostraViewModal")
    public String amostraViewModal() {return "view/amostra/amostraViewModal";}

    @GetMapping("/clientes")
    public String clientes() {return "view/cliente/clientes";}
    @GetMapping("/clienteModal")
    public String newCliente() { return "view/cliente/clienteModal";}


    @GetMapping("/localizacoes")
    public String localizacoes() {return "view/localizacao/localizacoes";}
    @GetMapping("/localizacaoModal")
    public String newLocalizacao() { return "view/localizacao/localizacaoModal";}




}
