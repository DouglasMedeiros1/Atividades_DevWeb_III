package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.Optional;

import com.autobots.automanager.entitades.Documento;
import com.autobots.automanager.repositorios.RepositorioDocumento;
import com.autobots.automanager.modelos.AdicionadorLink;

@RestController
@RequestMapping("/documentos")
public class DocumentoControle {
	@Autowired
	private RepositorioDocumento documentoRepositorio;
	
	@Autowired
	private AdicionadorLink<Documento> adicionadorLink;

	@GetMapping
	public ResponseEntity<List<Documento>> listar() {
		List<Documento> documentos = documentoRepositorio.findAll();
		adicionadorLink.adicionarLink(documentos);
		return ResponseEntity.ok(documentos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Documento> obter(@PathVariable Long id) {
		Optional<Documento> documento = documentoRepositorio.findById(id);
		if (documento.isPresent()) {
			adicionadorLink.adicionarLink(documento.get());
			return ResponseEntity.ok(documento.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Documento> criar(@RequestBody Documento documento) {
		Documento salvo = documentoRepositorio.save(documento);
		adicionadorLink.adicionarLink(salvo);
		return ResponseEntity.ok(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Documento> atualizar(@PathVariable Long id, @RequestBody Documento documento) {
		if (!documentoRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		documento.setId(id);
		Documento atualizado = documentoRepositorio.save(documento);
		adicionadorLink.adicionarLink(atualizado);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!documentoRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		documentoRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
