package br.ufjf.dcc193.tomatoban;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String titulo;
    @PositiveOrZero
    private Integer tomatoes;

    public Atividade(){

    }

    public Atividade(String titulo){
        setTitulo(titulo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTomatoes() {
        return tomatoes;
    }

    public void setTomatoes(Integer tomatoes) {
        this.tomatoes = tomatoes;
    }

    @Override
    public String toString() {
        return "Atividade [id=" + id + ", titulo=" + titulo + ", tomatoes=" + tomatoes + "]";
    }
    
    
}