package com.autobots.automanager.modelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.EmpresaControle;
import com.autobots.automanager.entitades.Empresa;

import java.util.List;

@Component
public class AdicionadorLinkEmpresa implements AdicionadorLink<Empresa> {

    @Autowired
    public AdicionadorLinkEmpresa(EmpresaControle empresaControle) {
    }

    @Override
    public void adicionarLink(List<Empresa> lista) {
        for (Empresa empresa : lista) {
            long id = empresa.getId();
            Link linkProprio = WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder
                            .methodOn(EmpresaControle.class)
                            .obterEmpresa(id))
                    .withSelfRel();
            EntityModel<Empresa> empresaModel = EntityModel.of(empresa);
            empresaModel.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(Empresa objeto) {
        Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(EmpresaControle.class)
                        .obterEmpresas())
                .withRel("empresas");
        EntityModel<Empresa> empresaModel = EntityModel.of(objeto);
        empresaModel.add(linkProprio);
    }
}
