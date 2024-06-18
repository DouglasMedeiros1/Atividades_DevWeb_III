package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.VeiculoControle;
import com.autobots.automanager.entitades.Veiculo;

@Component
public class AdicionadorLinkVeiculo implements AdicionadorLink<Veiculo> {

    @Autowired
    private ApplicationContext context;

    @Override
    public void adicionarLink(List<Veiculo> lista) {
        VeiculoControle veiculoControle = context.getBean(VeiculoControle.class);
        for (Veiculo veiculo : lista) {
            long id = veiculo.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(veiculoControle.getClass())
                            .obterVeiculo(id))
                    .withSelfRel();
            veiculo.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Veiculo objeto) {
        VeiculoControle veiculoControle = context.getBean(VeiculoControle.class);
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(veiculoControle.getClass())
                        .obterVeiculos())
                .withRel("veiculos");
        objeto.add(linkProprio);
    }
}
