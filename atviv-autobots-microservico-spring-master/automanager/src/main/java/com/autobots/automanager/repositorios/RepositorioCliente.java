package com.autobots.automanager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entitades.Cliente;

public interface RepositorioCliente extends JpaRepository<Cliente, Long> {

}
