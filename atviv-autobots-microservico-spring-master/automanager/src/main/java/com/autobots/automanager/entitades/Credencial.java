package com.autobots.automanager.entitades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.Link;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Credencial implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nomeUsuario;
    
    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String codigo = "DEFAULT_CODE"; 

    public void add(Link linkProprio) {
        // TODO Auto-generated method stub
    }
}
