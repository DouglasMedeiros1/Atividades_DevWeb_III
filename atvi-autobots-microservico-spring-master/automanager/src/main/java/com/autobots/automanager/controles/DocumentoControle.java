package com.autobots.automanager.controles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.ClienteSelecionador;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteSelecionador clienteSelecionador;

    @Autowired
    private DocumentoAtualizador documentoAtualizador;

    @GetMapping("/documento/{clienteId}/{documentoId}")
    public Documento obterDocumento(@PathVariable long clienteId, @PathVariable long documentoId) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            return cliente.getDocumentos().stream()
                    .filter(doc -> doc.getId() == documentoId)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @GetMapping("/documentos/{clienteId}")
    public List<Documento> obterDocumentos(@PathVariable long clienteId) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            return cliente.getDocumentos().stream()
                    .collect(Collectors.toList());
        }
        return null;
    }

    @PostMapping("/cadastro/{clienteId}")
    public void cadastrarDocumento(@PathVariable long clienteId, @RequestBody Documento documento) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            cliente.getDocumentos().add(documento);
            clienteRepositorio.save(cliente);
        }
    }

    @PutMapping("/atualizar/{clienteId}")
    public void atualizarDocumento(@PathVariable long clienteId, @RequestBody Documento atualizacao) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            Documento documento = cliente.getDocumentos().stream()
                    .filter(doc -> doc.getId() == atualizacao.getId())
                    .findFirst()
                    .orElse(null);
            if (documento != null) {
                documentoAtualizador.atualizar(documento, atualizacao);
                clienteRepositorio.save(cliente);
            }
        }
    }

    @DeleteMapping("/excluir/{clienteId}")
    public void excluirDocumento(@PathVariable long clienteId, @RequestBody Documento exclusao) {
        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, clienteId);
        if (cliente != null) {
            Documento documento = cliente.getDocumentos().stream()
                    .filter(doc -> doc.getId() == exclusao.getId())
                    .findFirst()
                    .orElse(null);
            if (documento != null) {
                cliente.getDocumentos().remove(documento);
                clienteRepositorio.save(cliente);
            }
        }
    }
}
