package com.bordamax.controller;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.Cliente;
import com.bordamax.entity.Localizacao;
import com.bordamax.repository.AmostraRepository;
import com.bordamax.repository.ClienteRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bordamax.filter.ClienteQuery.whereByCriterioCliente;

/**
 * Created by fire on 13/03/18.
 */

@RestController
@RequestMapping(value = "/cliente")
public class ClienteCtrl {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AmostraRepository amostraRepository;

    @GetMapping("getOne")
    public ResponseEntity<?> getOne(String nome) {
        return new ResponseEntity<>(clienteRepository.findFirstByNome(nome), HttpStatus.OK);
    }

    @GetMapping("getIndicador")
    public ResponseEntity<?> getIndicador() {
        return new ResponseEntity<>(clienteRepository.count(), HttpStatus.OK);
    }

    @GetMapping("getAllByStatus")
    public ResponseEntity<?> getAllByStatus(boolean status) {
        return new ResponseEntity<>(clienteRepository.findAllByStatus(status), HttpStatus.OK);
    }

    @GetMapping("getAllBy")
    public ResponseEntity<?> getAllBy(FiltroDto filtros) {
        Pageable pageable = new PageRequest(filtros.getPaginaAtual(), filtros.getTamanhoPagina(),
                filtros.getAscendente() ? Sort.Direction.ASC : Sort.Direction.DESC, filtros.getCampoOrderBy());
        Predicate predicate = whereByCriterioCliente(filtros);
        Page<Localizacao> lista = clienteRepository.findAll(predicate, pageable);
        return new ResponseEntity<>(lista.getContent(), HttpStatus.OK);
    }
    @PostMapping("new")
    public ResponseEntity<?> newUser(@RequestBody Cliente cliente) {
        String mensagem = "Registro cadastrado com sucesso!";
        Cliente c = clienteRepository.findFirstByNome(cliente.getNome());
        if(c != null){
            mensagem = "Já existe um registro cadastrado com esse nome!";
        } else{
            clienteRepository.save(cliente);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Cliente cliente) {
        String mensagem = "Registro alterado com sucesso!";
        Cliente c = clienteRepository.findFirstByNome(cliente.getNome());
        if(c != null && c.getId() != cliente.getId()){
            mensagem = "Já existe um registro cadastrado com esse nome!";
        } else{
            clienteRepository.save(cliente);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        String mensagem = "Registro excluido com sucesso!";
        if( amostraRepository.findFirstByCliente_Id(id) != null){
            mensagem = "Este registro não pode ser excluído porque está vinculado a amostras!";
        } else{
            clienteRepository.delete(id);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }
}
