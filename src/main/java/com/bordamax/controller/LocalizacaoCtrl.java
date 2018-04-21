package com.bordamax.controller;

import com.bordamax.dto.FiltroDto;
import com.bordamax.entity.Localizacao;
import com.bordamax.repository.AmostraRepository;
import com.bordamax.repository.LocalizacaoRepository;
//import com.mysema.query.types.Predicate;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bordamax.filter.LocalizacaoQuery.whereByCriterioLocalizacao;

/**
 * Created by fire on 13/03/18.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/localizacao")
public class LocalizacaoCtrl {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private AmostraRepository amostraRepository;

    @GetMapping("getOne")
    public ResponseEntity<?> getOne(String nome) {
        return new ResponseEntity<>(localizacaoRepository.findFirstByNome(nome), HttpStatus.OK);
    }

    @GetMapping("getIndicador")
    public ResponseEntity<?> getIndicador() {
        return new ResponseEntity<>(localizacaoRepository.count(), HttpStatus.OK);
    }

    @GetMapping("getAllByStatus")
    public ResponseEntity<?> getAllByStatus(boolean status) {
        return new ResponseEntity<>(localizacaoRepository.findAllByStatus(status), HttpStatus.OK);
    }

    @GetMapping("getAllBy")
    public ResponseEntity<?> getAllBy(FiltroDto filtros) {
        Pageable pageable = new PageRequest(filtros.getPaginaAtual(), filtros.getTamanhoPagina(),
                filtros.getAscendente() ? Sort.Direction.ASC : Sort.Direction.DESC, filtros.getCampoOrderBy());
        Predicate predicate = whereByCriterioLocalizacao(filtros);
        Page<Localizacao> lista = localizacaoRepository.findAll(predicate, pageable);
        return new ResponseEntity<>(lista.getContent(), HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity<?> newLocalizacao(@RequestBody Localizacao localizacao) {
        String mensagem = "Registro cadastrado com sucesso!";
        if(localizacaoRepository.findFirstByNome(localizacao.getNome()) != null){
            mensagem = "Já existe um registro cadastrado com esse nome!";
        } else{
            localizacaoRepository.save(localizacao);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Localizacao localizacao) {
        String mensagem = "Registro alterado com sucesso!";
        Localizacao local = localizacaoRepository.findFirstByNome(localizacao.getNome());
        if( local != null  && local.getId() != localizacao.getId()){
            mensagem = "Já existe um registro cadastrado com esse nome!";
        } else{
            localizacaoRepository.save(localizacao);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<?> delete(@RequestBody Long id) {
        String mensagem = "Registro excluido com sucesso!";
        if( amostraRepository.findFirstByLocalizacao_Id(id) != null){
            mensagem = "Este registro não pode ser excluído porque está vinculado a amostras!";
        } else{
            localizacaoRepository.delete(id);
        }
        return new ResponseEntity<>("{\"mensagem\":\""+mensagem+"\"}", HttpStatus.OK);
    }
}
