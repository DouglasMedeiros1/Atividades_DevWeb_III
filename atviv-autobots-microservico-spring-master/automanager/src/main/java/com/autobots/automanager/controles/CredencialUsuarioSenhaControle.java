package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.CredencialUsuarioSenha;
import com.autobots.automanager.modelos.AdicionadorLink;
import com.autobots.automanager.repositorios.RepositorioCredencialUsuarioSenha;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credenciais-usuariosenha")
public class CredencialUsuarioSenhaControle {

    private final RepositorioCredencialUsuarioSenha repositorioCredencialUsuarioSenha;
    private final AdicionadorLink<CredencialUsuarioSenha> adicionadorLinkCredencialUsuarioSenha;

    @Autowired
    public CredencialUsuarioSenhaControle(RepositorioCredencialUsuarioSenha repositorioCredencialUsuarioSenha, AdicionadorLink<CredencialUsuarioSenha> adicionadorLinkCredencialUsuarioSenha) {
        this.repositorioCredencialUsuarioSenha = repositorioCredencialUsuarioSenha;
        this.adicionadorLinkCredencialUsuarioSenha = adicionadorLinkCredencialUsuarioSenha;
    }

    @GetMapping("/")
    public ResponseEntity<List<CredencialUsuarioSenha>> obterCredenciaisUsuarioSenha() {
        List<CredencialUsuarioSenha> credenciaisUsuarioSenha = repositorioCredencialUsuarioSenha.findAll();
        adicionadorLinkCredencialUsuarioSenha.adicionarLink(credenciaisUsuarioSenha);
        return new ResponseEntity<>(credenciaisUsuarioSenha, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredencialUsuarioSenha> obterCredencialUsuarioSenha(@PathVariable Long id) {
        Optional<CredencialUsuarioSenha> credencialUsuarioSenhaOptional = repositorioCredencialUsuarioSenha.findById(id);
        if (credencialUsuarioSenhaOptional.isPresent()) {
            adicionadorLinkCredencialUsuarioSenha.adicionarLink(credencialUsuarioSenhaOptional.get());
            return new ResponseEntity<>(credencialUsuarioSenhaOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CredencialUsuarioSenha> adicionarCredencialUsuarioSenha(@RequestBody CredencialUsuarioSenha credencialUsuarioSenha) {
        CredencialUsuarioSenha novaCredencialUsuarioSenha = repositorioCredencialUsuarioSenha.save(credencialUsuarioSenha);
        adicionadorLinkCredencialUsuarioSenha.adicionarLink(novaCredencialUsuarioSenha);
        return new ResponseEntity<>(novaCredencialUsuarioSenha, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredencialUsuarioSenha> atualizarCredencialUsuarioSenha(@PathVariable Long id, @RequestBody CredencialUsuarioSenha credencialUsuarioSenhaAtualizada) {
        Optional<CredencialUsuarioSenha> credencialUsuarioSenhaOptional = repositorioCredencialUsuarioSenha.findById(id);
        if (credencialUsuarioSenhaOptional.isPresent()) {
            CredencialUsuarioSenha credencialUsuarioSenhaExistente = credencialUsuarioSenhaOptional.get();
            CredencialUsuarioSenha credencialUsuarioSenhaAtualizadaDB = repositorioCredencialUsuarioSenha.save(credencialUsuarioSenhaExistente);
            adicionadorLinkCredencialUsuarioSenha.adicionarLink(credencialUsuarioSenhaAtualizadaDB);
            return new ResponseEntity<>(credencialUsuarioSenhaAtualizadaDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCredencialUsuarioSenha(@PathVariable Long id) {
        Optional<CredencialUsuarioSenha> credencialUsuarioSenhaOptional = repositorioCredencialUsuarioSenha.findById(id);
        if (credencialUsuarioSenhaOptional.isPresent()) {
            repositorioCredencialUsuarioSenha.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
