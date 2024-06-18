package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.CredencialCodigoBarraControle;
import com.autobots.automanager.entitades.CredencialCodigoBarra;

@Component
public class AdicionadorLinkCredencialCodigoBarra implements AdicionadorLink<CredencialCodigoBarra> {

    @Override
    public void adicionarLink(List<CredencialCodigoBarra> lista) {
        for (CredencialCodigoBarra credencialCodigoBarra : lista) {
            long id = credencialCodigoBarra.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(CredencialCodigoBarraControle.class)
                            .obterCredencialCodigoBarra(id))
                    .withSelfRel();
            credencialCodigoBarra.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(CredencialCodigoBarra objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(CredencialCodigoBarraControle.class)
                        .obterCredenciaisCodigoBarra())
                .withRel("credenciais-codigobarra");
        objeto.add(linkProprio);
    }
}
