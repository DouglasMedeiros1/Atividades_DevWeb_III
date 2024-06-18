package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.EnderecoControle;
import com.autobots.automanager.entitades.Endereco;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<Endereco> {

    private final EnderecoControle enderecoControle;

    @Autowired
    public AdicionadorLinkEndereco(EnderecoControle enderecoControle) {
        this.enderecoControle = enderecoControle;
    }

    @Override
    public void adicionarLink(Endereco objeto) {
        long id = objeto.getId();
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(EnderecoControle.class)
                        .obterEndereco(id))
                .withSelfRel();
        EntityModel<Endereco> enderecoModel = EntityModel.of(objeto);
        enderecoModel.add(linkProprio);
        objeto = enderecoModel.getContent();
    }

	@Override
	public void adicionarLink(List<Endereco> lista) {
		// TODO Auto-generated method stub
		
	}
}
