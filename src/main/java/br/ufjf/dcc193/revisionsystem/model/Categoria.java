package br.ufjf.dcc193.revisionsystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message="Nome obrigatório")
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private Set<Avaliador> avaliadores;

    @OneToMany(mappedBy = "trabalhoAreaDeConhecimento", cascade = CascadeType.ALL)
    private Set<Trabalho> trabalhos;

    public Categoria() {

    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public Set<Avaliador> getAvaliadores() {
        return avaliadores;
    }

    public void setAvaliadores(Set<Avaliador> avaliadores) {
        this.avaliadores = avaliadores;
    }

    public Set<Trabalho> getTrabalhos() {
        return trabalhos;
    }

    public void setTrabalhos(Set<Trabalho> trabalhos) {
        this.trabalhos = trabalhos;
    }

    
}