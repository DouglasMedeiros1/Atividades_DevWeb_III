package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.CredencialCodigoBarra;
import com.autobots.automanager.modelos.AdicionadorLink;
import com.autobots.automanager.repositorios.RepositorioCredencialCodigoBarra;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credenciais-codigobarra")
public class CredencialCodigoBarraControle {

    private final RepositorioCredencialCodigoBarra repositorioCredencialCodigoBarra;
    private final AdicionadorLink<CredencialCodigoBarra> adicionadorLinkCredencialCodigoBarra;

    @Autowired
    public CredencialCodigoBarraControle(RepositorioCredencialCodigoBarra repositorioCredencialCodigoBarra, AdicionadorLink<CredencialCodigoBarra> adicionadorLinkCredencialCodigoBarra) {
        this.repositorioCredencialCodigoBarra = repositorioCredencialCodigoBarra;
        this.adicionadorLinkCredencialCodigoBarra = adicionadorLinkCredencialCodigoBarra;
    }

    @GetMapping("/")
    public ResponseEntity<List<CredencialCodigoBarra>> obterCredenciaisCodigoBarra() {
        List<CredencialCodigoBarra> credenciaisCodigoBarra = repositorioCredencialCodigoBarra.findAll();
        adicionadorLinkCredencialCodigoBarra.adicionarLink(credenciaisCodigoBarra);
        return new ResponseEntity<>(credenciaisCodigoBarra, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredencialCodigoBarra> obterCredencialCodigoBarra(@PathVariable Long id) {
        Optional<CredencialCodigoBarra> credencialCodigoBarraOptional = repositorioCredencialCodigoBarra.findById(id);
        if (credencialCodigoBarraOptional.isPresent()) {
            adicionadorLinkCredencialCodigoBarra.adicionarLink(credencialCodigoBarraOptional.get());
            return new ResponseEntity<>(credencialCodigoBarraOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CredencialCodigoBarra> adicionarCredencialCodigoBarra(@RequestBody CredencialCodigoBarra credencialCodigoBarra) {
        CredencialCodigoBarra novaCredencialCodigoBarra = repositorioCredencialCodigoBarra.save(credencialCodigoBarra);
        adicionadorLinkCredencialCodigoBarra.adicionarLink(novaCredencialCodigoBarra);
        return new ResponseEntity<>(novaCredencialCodigoBarra, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredencialCodigoBarra> atualizarCredencialCodigoBarra(@PathVariable Long id, @RequestBody CredencialCodigoBarra credencialCodigoBarraAtualizada) {
        Optional<CredencialCodigoBarra> credencialCodigoBarraOptional = repositorioCredencialCodigoBarra.findById(id);
        if (credencialCodigoBarraOptional.isPresent()) {
            CredencialCodigoBarra credencialCodigoBarraExistente = credencialCodigoBarraOptional.get();
            CredencialCodigoBarra credencialCodigoBarraAtualizadaDB = repositorioCredencialCodigoBarra.save(credencialCodigoBarraExistente);
            adicionadorLinkCredencialCodigoBarra.adicionarLink(credencialCodigoBarraAtualizadaDB);
            return new ResponseEntity<>(credencialCodigoBarraAtualizadaDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCredencialCodigoBarra(@PathVariable Long id) {
        Optional<CredencialCodigoBarra> credencialCodigoBarraOptional = repositorioCredencialCodigoBarra.findById(id);
        if (credencialCodigoBarraOptional.isPresent()) {
            repositorioCredencialCodigoBarra.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
