package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Credencial;
import com.autobots.automanager.modelos.AdicionadorLink;
import com.autobots.automanager.repositorios.RepositorioCredencial;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credenciais")
public class CredencialControle {

    private final RepositorioCredencial repositorioCredencial;
    private final AdicionadorLink<Credencial> adicionadorLinkCredencial;

    @Autowired
    public CredencialControle(RepositorioCredencial repositorioCredencial, AdicionadorLink<Credencial> adicionadorLinkCredencial) {
        this.repositorioCredencial = repositorioCredencial;
        this.adicionadorLinkCredencial = adicionadorLinkCredencial;
    }

    @GetMapping("/")
    public ResponseEntity<List<Credencial>> obterCredenciais() {
        List<Credencial> credenciais = repositorioCredencial.findAll();
        adicionadorLinkCredencial.adicionarLink(credenciais);
        return new ResponseEntity<>(credenciais, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credencial> obterCredencial(@PathVariable Long id) {
        Optional<Credencial> credencialOptional = repositorioCredencial.findById(id);
        if (credencialOptional.isPresent()) {
            adicionadorLinkCredencial.adicionarLink(credencialOptional.get());
            return new ResponseEntity<>(credencialOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Credencial> adicionarCredencial(@RequestBody Credencial credencial) {
        Credencial novaCredencial = repositorioCredencial.save(credencial);
        adicionadorLinkCredencial.adicionarLink(novaCredencial);
        return new ResponseEntity<>(novaCredencial, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Credencial> atualizarCredencial(@PathVariable Long id, @RequestBody Credencial credencialAtualizada) {
        Optional<Credencial> credencialOptional = repositorioCredencial.findById(id);
        if (credencialOptional.isPresent()) {
            Credencial credencialExistente = credencialOptional.get();
            Credencial credencialAtualizadaDB = repositorioCredencial.save(credencialExistente);
            adicionadorLinkCredencial.adicionarLink(credencialAtualizadaDB);
            return new ResponseEntity<>(credencialAtualizadaDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCredencial(@PathVariable Long id) {
        Optional<Credencial> credencialOptional = repositorioCredencial.findById(id);
        if (credencialOptional.isPresent()) {
            repositorioCredencial.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
