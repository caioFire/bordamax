package com.bordamax.controller;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.Localizacao;
import com.bordamax.entity.Usuario;
import com.bordamax.repository.UsuarioRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bordamax.filter.UsuarioQuery.whereByCriterioUsuario;

/**
 * Created by fire on 13/03/18.
 */


@RestController
@RequestMapping(value = "/usuario")
public class UsuarioCtrl {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("getOne")
    public ResponseEntity<?> getOne(String nome) {
        return new ResponseEntity<>(usuarioRepository.findFirstByNome(nome), HttpStatus.OK);
    }

    @GetMapping("getIndicador")
    public ResponseEntity<?> getIndicador() {
        return new ResponseEntity<>(usuarioRepository.count(), HttpStatus.OK);
    }

    @GetMapping("getAllByStatus")
    public ResponseEntity<?> getAllByStatus(boolean status) {
        return new ResponseEntity<>(usuarioRepository.findAllByStatus(status), HttpStatus.OK);
    }

    @GetMapping("getAllBy")
    public ResponseEntity<?> getAllBy(FiltroDto filtros) {
        Pageable pageable = new PageRequest(filtros.getPaginaAtual(), filtros.getTamanhoPagina(),
                filtros.getAscendente() ? Sort.Direction.ASC : Sort.Direction.DESC, filtros.getCampoOrderBy());
        Predicate predicate = whereByCriterioUsuario(filtros);
        Page<Localizacao> lista = usuarioRepository.findAll(predicate, pageable);
        return new ResponseEntity<>(lista.getContent(), HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<?> newUser(@RequestBody Usuario usuario) {
        String mensagem = "Registro salvo com sucesso!";
        Usuario u = usuarioRepository.findFirstByNome(usuario.getNome());
        if( u != null){
            mensagem = "Já existe um registro cadastrado com esse nome!";
        }
        u = usuarioRepository.findFirstByIdentificacao(usuario.getIdentificacao());
        if(u != null){
            mensagem = "Já existe um registro cadastrado com esse login!";
        } else{
            usuarioRepository.save(usuario);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Usuario usuario) {
        String mensagem = "Registro alterado com sucesso!";
        Usuario u = usuarioRepository.findFirstByNome(usuario.getNome());
        if( u != null && u.getId() != usuario.getId()){
            mensagem = "Já existe um registro cadastrado com esse nome!";
        }
        u = usuarioRepository.findFirstByIdentificacao(usuario.getIdentificacao());
        if( u != null && u.getId() != usuario.getId()){
            mensagem = "Já existe um registro cadastrado com esse login!";
        } else{
            usuarioRepository.save(usuario);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        usuarioRepository.delete(id);
        return new ResponseEntity<>("{\"mensagem\":\"Usuário excluído com sucesso!\"}", HttpStatus.OK);
    }







}
