package com.autobots.automanager.modelos;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.autobots.automanager.controles.VendaControle;
import com.autobots.automanager.entitades.Venda;

import java.util.List;

@Component
public class AdicionadorLinkVenda implements AdicionadorLink<Venda> {

	@Override
	public void adicionarLink(List<Venda> lista) {
		for (Venda venda : lista) {
			adicionarLink(venda);
		}
	}

	@Override
	public void adicionarLink(Venda objeto) {
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VendaControle.class).obter(objeto.getId())).withSelfRel();
		objeto.add(selfLink);
	}
}
