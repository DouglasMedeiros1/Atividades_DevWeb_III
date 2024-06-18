package com.autobots.automanager.modelos;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.autobots.automanager.controles.ServicoControle;
import com.autobots.automanager.entitades.Servico;

import java.util.List;

@Component
public class AdicionadorLinkServico implements AdicionadorLink<Servico> {

	@Override
	public void adicionarLink(List<Servico> lista) {
		for (Servico servico : lista) {
			adicionarLink(servico);
		}
	}

	@Override
	public void adicionarLink(Servico objeto) {
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ServicoControle.class).obter(objeto.getId())).withSelfRel();
		objeto.add(selfLink);
	}
}
