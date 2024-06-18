package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Vendedor;
import com.autobots.automanager.repositorios.RepositorioVendedor;

@RestController
public class ControleVendedor {
    @Autowired
    private RepositorioVendedor repositorio;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PostMapping("/cadastrar-vendedor")
    public ResponseEntity<?> cadastrarVendedor(@RequestBody Vendedor vendedor) {
        repositorio.save(vendedor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/obter-vendedores")
    public ResponseEntity<List<Vendedor>> obterVendedores() {
        return new ResponseEntity<>(repositorio.findAll(), HttpStatus.FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/atualizar-vendedor/{id}")
    public ResponseEntity<?> atualizarVendedor(@PathVariable Long id, @RequestBody Vendedor vendedorAtualizado) {
        Optional<Vendedor> vendedorOptional = repositorio.findById(id);
        if (vendedorOptional.isPresent()) {
            Vendedor vendedor = vendedorOptional.get();
            vendedor.setNome(vendedorAtualizado.getNome());
            repositorio.save(vendedor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @DeleteMapping("/deletar-vendedor/{id}")
    public ResponseEntity<?> deletarVendedor(@PathVariable Long id) {
        Optional<Vendedor> vendedorOptional = repositorio.findById(id);
        if (vendedorOptional.isPresent()) {
            repositorio.delete(vendedorOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
