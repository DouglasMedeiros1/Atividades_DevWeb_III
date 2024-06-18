package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;

import java.util.List;
import java.util.Optional;

import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.modelos.AdicionadorLink;
import com.autobots.automanager.repositorios.RepositorioServico;

@RestController
@RequestMapping("/servicos")
public class ServicoControle {
	@Autowired
	private RepositorioServico servicoRepositorio;
	
	@Autowired
	private AdicionadorLink<Servico> adicionadorLink;

	@GetMapping
	public ResponseEntity<List<Servico>> listar() {
		List<Servico> servicos = servicoRepositorio.findAll();
		adicionadorLink.adicionarLink(servicos);
		return ResponseEntity.ok(servicos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servico> obter(@PathVariable Long id) {
		Optional<Servico> servico = servicoRepositorio.findById(id);
		if (servico.isPresent()) {
			adicionadorLink.adicionarLink(servico.get());
			return ResponseEntity.ok(servico.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Servico> criar(@RequestBody Servico servico) {
		Servico salvo = servicoRepositorio.save(servico);
		adicionadorLink.adicionarLink(salvo);
		return ResponseEntity.ok(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servico) {
		if (!servicoRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		servico.setId(id);
		Servico atualizado = servicoRepositorio.save(servico);
		adicionadorLink.adicionarLink(atualizado);
		return ResponseEntity.ok(atualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!servicoRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		servicoRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
