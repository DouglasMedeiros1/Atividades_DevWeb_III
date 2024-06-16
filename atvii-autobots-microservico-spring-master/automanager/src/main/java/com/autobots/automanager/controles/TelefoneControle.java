package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.AdicionadorLinkTelefone;
import com.autobots.automanager.modelos.TelefoneAtualizador;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

    @Autowired
    private TelefoneRepositorio repositorio;
    @Autowired
    private AdicionadorLinkTelefone adicionadorLinkTelefone;

    @GetMapping("/telefones")
    public ResponseEntity<List<Telefone>> obterTelefones() {
        List<Telefone> telefones = repositorio.findAll();
        if (telefones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        adicionadorLinkTelefone.adicionarLink(telefones);
        return new ResponseEntity<>(telefones, HttpStatus.OK);
    }

    @GetMapping("/telefone/{id}")
    public ResponseEntity<Telefone> obterTelefone(@PathVariable long id) {
        Optional<Telefone> telefone = repositorio.findById(id);
        if (telefone.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adicionadorLinkTelefone.adicionarLink(telefone.get());
        return new ResponseEntity<>(telefone.get(), HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Telefone> adicionarTelefone(@RequestBody Telefone telefone) {
        repositorio.save(telefone);
        adicionadorLinkTelefone.adicionarLink(telefone);
        return new ResponseEntity<>(telefone, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Telefone> atualizarTelefone(@PathVariable long id, @RequestBody Telefone atualizacao) {
        Optional<Telefone> telefoneExistente = repositorio.findById(id);
        if (telefoneExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Telefone telefone = telefoneExistente.get();
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);
        repositorio.save(telefone);
        adicionadorLinkTelefone.adicionarLink(telefone);
        return new ResponseEntity<>(telefone, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable long id) {
        Optional<Telefone> telefone = repositorio.findById(id);
        if (telefone.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repositorio.delete(telefone.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
