package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.AdicionadorLinkDocumento;
import com.autobots.automanager.modelos.DocumentoAtualizador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

    @Autowired
    private DocumentoRepositorio repositorio;
    @Autowired
    private AdicionadorLinkDocumento adicionadorLinkDocumento;

    @GetMapping("/documentos")
    public ResponseEntity<List<Documento>> obterDocumentos() {
        List<Documento> documentos = repositorio.findAll();
        adicionadorLinkDocumento.adicionarLink(documentos);
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    @GetMapping("/documento/{id}")
    public ResponseEntity<Documento> obterDocumento(@PathVariable Long id) {
        Documento documento = repositorio.findById(id).orElse(null);
        if (documento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            adicionadorLinkDocumento.adicionarLink(documento);
            return new ResponseEntity<>(documento, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrarDocumento(@RequestBody Documento documento) {
        repositorio.save(documento);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarDocumento(@PathVariable Long id, @RequestBody Documento atualizacao) {
        Documento documento = repositorio.findById(id).orElse(null);
        if (documento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            DocumentoAtualizador atualizador = new DocumentoAtualizador();
            atualizador.atualizar(documento, atualizacao);
            repositorio.save(documento);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarDocumento(@PathVariable Long id) {
        repositorio.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
