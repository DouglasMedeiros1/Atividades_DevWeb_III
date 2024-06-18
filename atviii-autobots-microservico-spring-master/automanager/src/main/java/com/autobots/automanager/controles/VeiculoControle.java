package com.autobots.automanager.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.modelos.AdicionadorLinkVeiculo;
import com.autobots.automanager.repositorios.RepositorioVeiculo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoControle {

    private final RepositorioVeiculo repositorioVeiculo;
    private final AdicionadorLinkVeiculo adicionadorLinkVeiculo;

    @Autowired
    public VeiculoControle(RepositorioVeiculo repositorioVeiculo, AdicionadorLinkVeiculo adicionadorLinkVeiculo) {
        this.repositorioVeiculo = repositorioVeiculo;
        this.adicionadorLinkVeiculo = adicionadorLinkVeiculo;
    }

    @GetMapping("/")
    public ResponseEntity<List<Veiculo>> obterVeiculos() {
        List<Veiculo> veiculos = repositorioVeiculo.findAll();
        adicionadorLinkVeiculo.adicionarLink(veiculos);
        return new ResponseEntity<>(veiculos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> obterVeiculo(@PathVariable Long id) {
        Optional<Veiculo> veiculoOptional = repositorioVeiculo.findById(id);
        if (veiculoOptional.isPresent()) {
            adicionadorLinkVeiculo.adicionarLink(veiculoOptional.get());
            return new ResponseEntity<>(veiculoOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Veiculo> adicionarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = repositorioVeiculo.save(veiculo);
        adicionadorLinkVeiculo.adicionarLink(novoVeiculo);
        return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        Optional<Veiculo> veiculoOptional = repositorioVeiculo.findById(id);
        if (veiculoOptional.isPresent()) {
            Veiculo veiculoExistente = veiculoOptional.get();
            veiculoExistente.setTipo(veiculoAtualizado.getTipo());
            veiculoExistente.setModelo(veiculoAtualizado.getModelo());
            veiculoExistente.setPlaca(veiculoAtualizado.getPlaca());
            veiculoExistente.setProprietario(veiculoAtualizado.getProprietario());
            Veiculo veiculoAtualizadoDB = repositorioVeiculo.save(veiculoExistente);
            adicionadorLinkVeiculo.adicionarLink(veiculoAtualizadoDB);
            return new ResponseEntity<>(veiculoAtualizadoDB, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        Optional<Veiculo> veiculoOptional = repositorioVeiculo.findById(id);
        if (veiculoOptional.isPresent()) {
            repositorioVeiculo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
