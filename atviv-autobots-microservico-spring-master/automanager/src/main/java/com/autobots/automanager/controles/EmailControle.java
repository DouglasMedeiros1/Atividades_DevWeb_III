package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.Optional;

import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.repositorios.RepositorioEmail;
import com.autobots.automanager.modelos.AdicionadorLink;

@RestController
@RequestMapping("/emails")
public class EmailControle {
	@Autowired
	private RepositorioEmail emailRepositorio;
	
	@Autowired
	private AdicionadorLink<Email> adicionadorLink;

	@GetMapping
	public ResponseEntity<List<Email>> listar() {
		List<Email> emails = emailRepositorio.findAll();
		adicionadorLink.adicionarLink(emails);
		return ResponseEntity.ok(emails);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Email> obter(@PathVariable Long id) {
		Optional<Email> email = emailRepositorio.findById(id);
		if (email.isPresent()) {
			adicionadorLink.adicionarLink(email.get());
			return ResponseEntity.ok(email.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Email> criar(@RequestBody Email email) {
		Email salvo = emailRepositorio.save(email);
		adicionadorLink.adicionarLink(salvo);
		return ResponseEntity.ok(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Email> atualizar(@PathVariable Long id, @RequestBody Email email) {
		if (!emailRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		email.setId(id);
		Email atualizado = emailRepositorio.save(email);
		adicionadorLink.adicionarLink(atualizado);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!emailRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		emailRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
