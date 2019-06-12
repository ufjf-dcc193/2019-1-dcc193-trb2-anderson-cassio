package br.ufjf.dcc193.tomatoban.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Trabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;

  
  
    @ManyToOne
    @JoinColumn
    private Categoria areaDeConhecimento;

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

     /**
      * @return the areaDeConhecimento
      */
     public Categoria getAreaDeConhecimento() {
         return areaDeConhecimento;
     }

     /**
      * @param areaDeConhecimento the areaDeConhecimento to set
      */
     public void setAreaDeConhecimento(Categoria areaDeConhecimento) {
         this.areaDeConhecimento = areaDeConhecimento;
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

     


}