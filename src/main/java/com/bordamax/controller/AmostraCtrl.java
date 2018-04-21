package com.bordamax.controller;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.Amostra;
import com.bordamax.repository.AmostraRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bordamax.filter.AmostraQuery.whereByCriterioAmostra;

/**
 * Created by fire on 13/03/18.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/amostra")
public class AmostraCtrl {

    @Autowired
    private AmostraRepository amostraRepository;

    //Find do aplicativo
    @GetMapping("getAPP")
    public ResponseEntity<?> getAPP( FiltroDto filtro ) {
        Predicate predicate = whereByCriterioAmostra(filtro);
        Iterable<Amostra> lista = amostraRepository.findAll(predicate);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("getOne")
    public ResponseEntity<?> getOne(String codigo) {
        return new ResponseEntity<>(amostraRepository.findFirstByCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping("getIndicador")
    public ResponseEntity<?> getIndicador() {
        return new ResponseEntity<>(amostraRepository.count(), HttpStatus.OK);
    }

    @GetMapping("getAllByStatusAndPortfolio")
    public ResponseEntity<?> getAllByStatusAndPortfolio(boolean status, boolean portfolio) {
        return new ResponseEntity<>(amostraRepository.findAllByStatusAndPortfolio(status, portfolio), HttpStatus.OK);
    }

    @GetMapping("getAllBy")
    public ResponseEntity<?> getAllBy(FiltroDto filtros) {
        Pageable pageable = new PageRequest(filtros.getPaginaAtual(), filtros.getTamanhoPagina(),
                filtros.getAscendente() ? Sort.Direction.ASC : Sort.Direction.DESC, filtros.getCampoOrderBy());
        Predicate predicate = whereByCriterioAmostra(filtros);
        Page<Amostra> lista = amostraRepository.findAll(predicate, pageable);
        return new ResponseEntity<>(lista.getContent(), HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<?> newUser(@RequestBody Amostra amostra) {
        String mensagem = "Registro cadastrado com sucesso!";
        Amostra a = amostraRepository.findFirstByCodigo(amostra.getCodigo());
        if(a != null){
            mensagem = "Já existe um registro cadastrado com esse nome!";
        } else {
            amostraRepository.save(amostra);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);

    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Amostra amostra) {
        String mensagem = "Registro alterado com sucesso!";
        Amostra a = amostraRepository.findFirstByCodigo(amostra.getCodigo());
        if(a != null && a.getId() != amostra.getId()){
            mensagem = "Já existe um registro cadastrado com esse nome!";
        } else {
            if(amostra.getUrlImagem().contains("bordamax/")) {
                String[] url = amostra.getUrlImagem().split("bordamax/");
                amostra.setUrlImagem(url[1]);
            }
            amostraRepository.save(amostra);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        amostraRepository.delete(id);
        return new ResponseEntity<>("{\"mensagem\":\"Registro excluido com sucesso!\"}", HttpStatus.OK);
    }
}

