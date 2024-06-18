package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Telefone;
import com.autobots.automanager.modelos.AdicionadorLinkTelefone;
import com.autobots.automanager.repositorios.RepositorioTelefone;

import java.util.List;

@RestController
@RequestMapping("/telefones")
public class TelefoneControle {

    private final RepositorioTelefone repositorioTelefone;
    private final AdicionadorLinkTelefone adicionadorLinkTelefone;

    @Autowired
    public TelefoneControle(RepositorioTelefone repositorioTelefone, AdicionadorLinkTelefone adicionadorLinkTelefone) {
        this.repositorioTelefone = repositorioTelefone;
        this.adicionadorLinkTelefone = adicionadorLinkTelefone;
    }

    @GetMapping("/")
    public ResponseEntity<List<Telefone>> obterTelefones() {
        List<Telefone> telefones = repositorioTelefone.findAll();
        adicionadorLinkTelefone.adicionarLink(telefones);
        return new ResponseEntity<>(telefones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> obterTelefone(@PathVariable Long id) {
        Telefone telefone = repositorioTelefone.findById(id).orElse(null);
        if (telefone != null) {
            adicionadorLinkTelefone.adicionarLink(telefone);
            return new ResponseEntity<>(telefone, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Telefone> adicionarTelefone(@RequestBody Telefone telefone) {
        Telefone novoTelefone = repositorioTelefone.save(telefone);
        adicionadorLinkTelefone.adicionarLink(novoTelefone);
        return new ResponseEntity<>(novoTelefone, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Telefone> atualizarTelefone(@PathVariable Long id, @RequestBody Telefone telefoneAtualizado) {
        Telefone telefoneExistente = repositorioTelefone.findById(id).orElse(null);
        if (telefoneExistente != null) {
            telefoneExistente.setDdd(telefoneAtualizado.getDdd());
            telefoneExistente.setNumero(telefoneAtualizado.getNumero());
            Telefone telefoneAtualizadoEntity = repositorioTelefone.save(telefoneExistente);
            adicionadorLinkTelefone.adicionarLink(telefoneAtualizadoEntity);
            return new ResponseEntity<>(telefoneAtualizadoEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Long id) {
        Telefone telefone = repositorioTelefone.findById(id).orElse(null);
        if (telefone != null) {
            repositorioTelefone.delete(telefone);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
