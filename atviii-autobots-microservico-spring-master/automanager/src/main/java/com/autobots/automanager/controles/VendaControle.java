package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.repositorios.RepositorioVenda;
import com.autobots.automanager.modelos.AdicionadorLink;

@RestController
@RequestMapping("/vendas")
public class VendaControle {
	@Autowired
	private RepositorioVenda vendaRepositorio;
	
	@Autowired
	private AdicionadorLink<Venda> adicionadorLink;

	@GetMapping
	public ResponseEntity<List<Venda>> listar() {
		List<Venda> vendas = vendaRepositorio.findAll();
		adicionadorLink.adicionarLink(vendas);
		return ResponseEntity.ok(vendas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Venda> obter(@PathVariable Long id) {
		Optional<Venda> venda = vendaRepositorio.findById(id);
		if (venda.isPresent()) {
			adicionadorLink.adicionarLink(venda.get());
			return ResponseEntity.ok(venda.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Venda> criar(@RequestBody Venda venda) {
		Venda salva = vendaRepositorio.save(venda);
		adicionadorLink.adicionarLink(salva);
		return ResponseEntity.ok(salva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda) {
		if (!vendaRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		venda.setId(id);
		Venda atualizada = vendaRepositorio.save(venda);
		adicionadorLink.adicionarLink(atualizada);
		return ResponseEntity.ok(atualizada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!vendaRepositorio.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		vendaRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
