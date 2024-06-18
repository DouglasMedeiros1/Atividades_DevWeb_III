package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Endereco;
import com.autobots.automanager.modelos.AdicionadorLinkEndereco;
import com.autobots.automanager.repositorios.RepositorioEndereco;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {

    private final RepositorioEndereco repositorio;
    private final AdicionadorLinkEndereco adicionadorLinkEndereco;

    @Autowired
    public EnderecoControle(RepositorioEndereco repositorio, AdicionadorLinkEndereco adicionadorLinkEndereco) {
        this.repositorio = repositorio;
        this.adicionadorLinkEndereco = adicionadorLinkEndereco;
    }

    @GetMapping
    public List<Endereco> obterEnderecos() {
        List<Endereco> enderecos = repositorio.findAll();
        adicionadorLinkEndereco.adicionarLink(enderecos);
        return enderecos;
    }

    @GetMapping("/{id}")
    public Endereco obterEndereco(@PathVariable Long id) {
        Optional<Endereco> endereco = repositorio.findById(id);
        return endereco.orElse(null);
    }

    @PostMapping
    public Endereco adicionarEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = repositorio.save(endereco);
        adicionadorLinkEndereco.adicionarLink(novoEndereco);
        return novoEndereco;
    }

    @PutMapping("/{id}")
    public Endereco atualizarEndereco(@PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        Optional<Endereco> optionalEndereco = repositorio.findById(id);
        if (optionalEndereco.isPresent()) {
            Endereco endereco = optionalEndereco.get();
            endereco.setEstado(enderecoAtualizado.getEstado());
            endereco.setCidade(enderecoAtualizado.getCidade());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setNumero(enderecoAtualizado.getNumero());
            endereco.setCodigoPostal(enderecoAtualizado.getCodigoPostal());
            endereco.setInformacoesAdicionais(enderecoAtualizado.getInformacoesAdicionais());
            Endereco enderecoSalvo = repositorio.save(endereco);
            adicionadorLinkEndereco.adicionarLink(enderecoSalvo);
            return enderecoSalvo;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void removerEndereco(@PathVariable Long id) {
        repositorio.deleteById(id);
    }
}
