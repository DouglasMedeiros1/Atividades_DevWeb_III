package com.autobots.automanager.controles;

import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class MercadoriaControle {

    private final RepositorioMercadoria repositorioMercadoria;

    @Autowired
    public MercadoriaControle(RepositorioMercadoria repositorioMercadoria) {
        this.repositorioMercadoria = repositorioMercadoria;
    }

    public List<Mercadoria> obterTodasMercadorias() {
        return repositorioMercadoria.findAll();
    }

    public Optional<Mercadoria> obterMercadoriaPorId(Long id) {
        return repositorioMercadoria.findById(id);
    }

    public Mercadoria salvarMercadoria(Mercadoria mercadoria) {
        return repositorioMercadoria.save(mercadoria);
    }

    public void deletarMercadoriaPorId(Long id) {
        repositorioMercadoria.deleteById(id);
    }

    public void atualizarMercadoria(Mercadoria mercadoria) {
        repositorioMercadoria.save(mercadoria);
    }
}
