package br.ufjf.dcc193.revisionsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.revisionsystem.model.Avaliador;

@Repository
public interface AvaliadorRepository extends JpaRepository<Avaliador, Long> {

    Avaliador findByEmail(String email);
}