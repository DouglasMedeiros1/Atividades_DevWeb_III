package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Administrador;
import com.autobots.automanager.repositorios.RepositorioAdministrador;

@RestController
public class ControleAdministrador {
    @Autowired
    private RepositorioAdministrador repositorio;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/cadastrar-administrador")
    public ResponseEntity<?> cadastrarAdministrador(@RequestBody Administrador administrador) {
        repositorio.save(administrador);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/obter-administradores")
    public ResponseEntity<List<Administrador>> obterAdministradores() {
        return new ResponseEntity<>(repositorio.findAll(), HttpStatus.FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/atualizar-administrador/{id}")
    public ResponseEntity<?> atualizarAdministrador(@PathVariable Long id, @RequestBody Administrador administradorAtualizado) {
        Optional<Administrador> administradorOptional = repositorio.findById(id);
        if (administradorOptional.isPresent()) {
            Administrador administrador = administradorOptional.get();
            administrador.setNome(administradorAtualizado.getNome());
            repositorio.save(administrador);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/deletar-administrador/{id}")
    public ResponseEntity<?> deletarAdministrador(@PathVariable Long id) {
        Optional<Administrador> administradorOptional = repositorio.findById(id);
        if (administradorOptional.isPresent()) {
            repositorio.delete(administradorOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
