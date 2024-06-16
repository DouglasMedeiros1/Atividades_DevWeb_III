package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelos.AdicionadorLinkEndereco;
import com.autobots.automanager.modelos.EnderecoAtualizador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {

    @Autowired
    private EnderecoRepositorio repositorio;
    @Autowired
    private AdicionadorLinkEndereco adicionadorLinkEndereco;

    @GetMapping("/enderecos")
    public ResponseEntity<List<Endereco>> obterEnderecos() {
        List<Endereco> enderecos = repositorio.findAll();
        adicionadorLinkEndereco.adicionarLink(enderecos);
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @GetMapping("/endereco/{id}")
    public ResponseEntity<Endereco> obterEndereco(@PathVariable Long id) {
        Endereco endereco = repositorio.findById(id).orElse(null);
        if (endereco == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            adicionadorLinkEndereco.adicionarLink(endereco);
            return new ResponseEntity<>(endereco, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarEndereco(@RequestBody Endereco endereco) {
        repositorio.save(endereco);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco atualizacao) {
        Endereco endereco = repositorio.findById(id).orElse(null);
        if (endereco == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            EnderecoAtualizador atualizador = new EnderecoAtualizador();
            atualizador.atualizar(endereco, atualizacao);
            repositorio.save(endereco);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        repositorio.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
