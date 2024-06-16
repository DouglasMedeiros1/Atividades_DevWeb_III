package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteSelecionador clienteSelecionador;

    @Autowired
    private EnderecoAtualizador enderecoAtualizador;

    @GetMapping("/{clienteId}")
    public ResponseEntity<Endereco> obterEndereco(@PathVariable Long clienteId) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null && cliente.getEndereco() != null) {
            return ResponseEntity.ok(cliente.getEndereco());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<Endereco> criarEndereco(@PathVariable Long clienteId, @RequestBody Endereco endereco) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            cliente.setEndereco(endereco);
            clienteRepositorio.save(cliente);
            return ResponseEntity.ok(endereco);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long clienteId, @RequestBody Endereco atualizacao) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null && cliente.getEndereco() != null) {
            Endereco endereco = cliente.getEndereco();
            enderecoAtualizador.atualizar(endereco, atualizacao);
            clienteRepositorio.save(cliente);
            return ResponseEntity.ok(endereco);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long clienteId) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null && cliente.getEndereco() != null) {
            cliente.setEndereco(null);
            clienteRepositorio.save(cliente);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
