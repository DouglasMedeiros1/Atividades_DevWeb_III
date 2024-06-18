package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.modelos.AdicionadorLinkEmpresa;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaControle {

    private final RepositorioEmpresa repositorioEmpresa;
    private final AdicionadorLinkEmpresa adicionadorLinkEmpresa;

    @Autowired
    public EmpresaControle(RepositorioEmpresa repositorioEmpresa, AdicionadorLinkEmpresa adicionadorLinkEmpresa) {
        this.repositorioEmpresa = repositorioEmpresa;
        this.adicionadorLinkEmpresa = adicionadorLinkEmpresa;
    }

    @GetMapping("/")
    public ResponseEntity<List<Empresa>> obterEmpresas() {
        List<Empresa> empresas = repositorioEmpresa.findAll();
        adicionadorLinkEmpresa.adicionarLink(empresas);
        return new ResponseEntity<>(empresas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = repositorioEmpresa.findById(id);
        if (empresaOptional.isPresent()) {
            adicionadorLinkEmpresa.adicionarLink(empresaOptional.get());
            return new ResponseEntity<>(empresaOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Empresa> adicionarEmpresa(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = repositorioEmpresa.save(empresa);
        adicionadorLinkEmpresa.adicionarLink(novaEmpresa);
        return new ResponseEntity<>(novaEmpresa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Optional<Empresa> empresaOptional = repositorioEmpresa.findById(id);
        if (empresaOptional.isPresent()) {
            Empresa empresaExistente = empresaOptional.get();
            empresaExistente.setRazaoSocial(empresaAtualizada.getRazaoSocial());
            empresaExistente.setNomeFantasia(empresaAtualizada.getNomeFantasia());
            empresaExistente.setEndereco(empresaAtualizada.getEndereco());
            Empresa empresaAtualizadaDB = repositorioEmpresa.save(empresaExistente);
            adicionadorLinkEmpresa.adicionarLink(empresaAtualizadaDB);
            return new ResponseEntity<>(empresaAtualizadaDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        Optional<Empresa> empresaOptional = repositorioEmpresa.findById(id);
        if (empresaOptional.isPresent()) {
            repositorioEmpresa.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
