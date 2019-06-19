package br.ufjf.dcc193.revisionsystem.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Trabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message="Título obrigatório")
    private String titulo;
    private String descricao;
    private String url;

    @ManyToOne
    @JoinColumn
    @NotNull(message="Categoria obrigatória")
    private Categoria trabalhoAreaDeConhecimento;

    @OneToMany(mappedBy = "trabalho", cascade = CascadeType.ALL)
    private Set<Revisao> revisoes;

    public Trabalho() {

    }

    public Trabalho(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public Categoria getTrabalhoAreaDeConhecimento() {
        return trabalhoAreaDeConhecimento;
    }

    /**
     * @param trabalhoAreaDeConhecimento the trabalhoAreaDeConhecimento to set
     */
    public void setTrabalhoAreaDeConhecimento(Categoria trabalhoAreaDeConhecimento) {
        this.trabalhoAreaDeConhecimento = trabalhoAreaDeConhecimento;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Revisao> getRevisoes() {
        return revisoes;
    }

    public void setRevisoes(Set<Revisao> revisoes) {
        this.revisoes = revisoes;
    }


    public Integer getRevisoesCount(){
        return (Integer)this.revisoes.size();
    }
    

}