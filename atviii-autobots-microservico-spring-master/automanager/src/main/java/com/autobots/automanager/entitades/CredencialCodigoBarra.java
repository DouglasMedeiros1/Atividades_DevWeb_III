package com.autobots.automanager.entitades;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.hateoas.Link;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CredencialCodigoBarra extends Credencial {
	@Column(nullable = false, unique = true)
	private long codigo;

	public void add(Link linkProprio) {
		// TODO Auto-generated method stub
		
	}
}