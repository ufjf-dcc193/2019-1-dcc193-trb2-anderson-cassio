package br.ufjf.dcc193.revisionsystem.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



@Entity
public class Avaliador {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
    name = "categorias")
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "avaliador", cascade = CascadeType.ALL)
    private Set<Revisao> revisoes;

 
    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Avaliador() {

    }

    public Avaliador(String nome, String email, String password){
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the categoria
     */

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}