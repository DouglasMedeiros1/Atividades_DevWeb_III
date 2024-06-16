package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/telefones")
public class TelefoneControle {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteSelecionador clienteSelecionador;

    @Autowired
    private TelefoneAtualizador telefoneAtualizador;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Telefone>> obterTelefones(@PathVariable Long clienteId) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            return ResponseEntity.ok(cliente.getTelefones());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{clienteId}/{telefoneId}")
    public ResponseEntity<Telefone> obterTelefone(@PathVariable Long clienteId, @PathVariable Long telefoneId) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            Optional<Telefone> telefone = cliente.getTelefones().stream()
                    .filter(t -> t.getId().equals(telefoneId))
                    .findFirst();
            if (telefone.isPresent()) {
                return ResponseEntity.ok(telefone.get());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<Telefone> criarTelefone(@PathVariable Long clienteId, @RequestBody Telefone telefone) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            cliente.getTelefones().add(telefone);
            clienteRepositorio.save(cliente);
            return ResponseEntity.ok(telefone);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{clienteId}/{telefoneId}")
    public ResponseEntity<Telefone> atualizarTelefone(@PathVariable Long clienteId, @PathVariable Long telefoneId, @RequestBody Telefone atualizacao) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            Optional<Telefone> telefoneExistente = cliente.getTelefones().stream()
                    .filter(t -> t.getId().equals(telefoneId))
                    .findFirst();
            if (telefoneExistente.isPresent()) {
                Telefone telefone = telefoneExistente.get();
                telefoneAtualizador.atualizar(telefone, atualizacao);
                clienteRepositorio.save(cliente);
                return ResponseEntity.ok(telefone);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clienteId}/{telefoneId}")
    public ResponseEntity<Void> deletarTelefone(@PathVariable Long clienteId, @PathVariable Long telefoneId) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            Optional<Telefone> telefone = cliente.getTelefones().stream()
                    .filter(t -> t.getId().equals(telefoneId))
                    .findFirst();
            if (telefone.isPresent()) {
                cliente.getTelefones().remove(telefone.get());
                clienteRepositorio.save(cliente);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
