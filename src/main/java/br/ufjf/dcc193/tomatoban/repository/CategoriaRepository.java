package br.ufjf.dcc193.tomatoban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufjf.dcc193.tomatoban.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}