package br.ufjf.dcc193.tomatoban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.tomatoban.model.Trabalho;


@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long>{
    
}