package com.autobots.automanager.modelos;

import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.autobots.automanager.controles.EmailControle;
import com.autobots.automanager.entitades.Email;

import java.util.List;

@Component
public class AdicionadorLinkEmail implements AdicionadorLink<Email> {

	@Override
	public void adicionarLink(List<Email> lista) {
		for (Email email : lista) {
			adicionarLink(email);
		}
	}

	@Override
	public void adicionarLink(Email objeto) {
		Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmailControle.class).obter(objeto.getId())).withSelfRel();
		objeto.add(selfLink);
	}
}
