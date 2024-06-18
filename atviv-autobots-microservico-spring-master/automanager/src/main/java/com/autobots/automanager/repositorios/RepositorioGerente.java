package com.autobots.automanager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entitades.Gerente;

public interface RepositorioGerente extends JpaRepository<Gerente, Long> {
}
