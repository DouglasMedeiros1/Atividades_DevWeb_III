package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Gerente;
import com.autobots.automanager.repositorios.RepositorioGerente;

@RestController
public class ControleGerente {
    @Autowired
    private RepositorioGerente repositorio;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/cadastrar-gerente")
    public ResponseEntity<?> cadastrarGerente(@RequestBody Gerente gerente) {
        repositorio.save(gerente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @GetMapping("/obter-gerentes")
    public ResponseEntity<List<Gerente>> obterGerentes() {
        return new ResponseEntity<>(repositorio.findAll(), HttpStatus.FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/atualizar-gerente/{id}")
    public ResponseEntity<?> atualizarGerente(@PathVariable Long id, @RequestBody Gerente gerenteAtualizado) {
        Optional<Gerente> gerenteOptional = repositorio.findById(id);
        if (gerenteOptional.isPresent()) {
            Gerente gerente = gerenteOptional.get();
            gerente.setNome(gerenteAtualizado.getNome());
            repositorio.save(gerente);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/deletar-gerente/{id}")
    public ResponseEntity<?> deletarGerente(@PathVariable Long id) {
        Optional<Gerente> gerenteOptional = repositorio.findById(id);
        if (gerenteOptional.isPresent()) {
            repositorio.delete(gerenteOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
