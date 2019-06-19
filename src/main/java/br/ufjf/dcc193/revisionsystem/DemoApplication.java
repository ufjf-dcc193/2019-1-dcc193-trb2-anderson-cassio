package br.ufjf.dcc193.revisionsystem;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufjf.dcc193.revisionsystem.model.Avaliador;
import br.ufjf.dcc193.revisionsystem.model.Categoria;
import br.ufjf.dcc193.revisionsystem.model.Revisao;
import br.ufjf.dcc193.revisionsystem.model.Trabalho;
import br.ufjf.dcc193.revisionsystem.repository.AvaliadorRepository;
import br.ufjf.dcc193.revisionsystem.repository.CategoriaRepository;
import br.ufjf.dcc193.revisionsystem.repository.TrabalhoRepository;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	public static TrabalhoRepository trabalhoRepository;
	@Autowired
	public static CategoriaRepository categoriaRepository;
	@Autowired
	public static AvaliadorRepository avaliadorRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//popularDadosPrincipais();
	}

	/*Por algum motivo, a configuração do application.properties para não recriar o banco a cada nova execuçãp não estava funcionando
	* Por esse motivo foi criado o método para popular dados iniciais
	*/

	public static void popularDadosPrincipais(){
		Avaliador a1 = new Avaliador("Avaliador 1","aval1@mail.com","1234");
		Avaliador a2 = new Avaliador("Avaliador 2","aval2@mail.com","123456");

		Categoria c1 = new Categoria("Categoria 1");
		Categoria c2 = new Categoria("Categoria 2");

		Trabalho t1 = new Trabalho("Trabalho 1","Descricao 1","Url 1");
		Trabalho t2 = new Trabalho("Trabalho 2","Descricao 2","Url 2");

		t1.setTrabalhoAreaDeConhecimento(c1);
		t2.setTrabalhoAreaDeConhecimento(c2);


		avaliadorRepository.save(a1);
		avaliadorRepository.save(a2);

		categoriaRepository.save(c1);
		categoriaRepository.save(c2);

		trabalhoRepository.save(t1);
		trabalhoRepository.save(t2);

	}

}
