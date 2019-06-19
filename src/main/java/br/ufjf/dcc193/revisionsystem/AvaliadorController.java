package br.ufjf.dcc193.revisionsystem;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.revisionsystem.model.Avaliador;
import br.ufjf.dcc193.revisionsystem.model.Categoria;
import br.ufjf.dcc193.revisionsystem.repository.AvaliadorRepository;
import br.ufjf.dcc193.revisionsystem.repository.CategoriaRepository;

@Controller
@RequestMapping("/avaliador")
public class AvaliadorController {

    @Autowired
    AvaliadorRepository avaliadorRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @RequestMapping(value = { "/categorias-avaliador" }, method = RequestMethod.GET)
    public ModelAndView categoriasAvaliador(@RequestParam("id") Long id) {
        Avaliador avaliador = avaliadorRepository.getOne(id);
        List<Categoria> categoriasAvaliador = new ArrayList();
        categoriasAvaliador = avaliador.getCategorias();
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliador);
        mv.addObject("categoriasAvaliador", categoriasAvaliador);
        mv.setViewName("categorias-avaliador.html");
        return mv;

    }

    @RequestMapping(value = { "/inserir-categoria-avaliador" }, method = RequestMethod.GET)
    public ModelAndView inserirCategoriaAvaliador(@RequestParam("id") Long id, @RequestParam("id_cat") Long id_cat) {
        Avaliador avaliador = avaliadorRepository.getOne(id);
        Categoria categoria = categoriaRepository.getOne(id_cat);
        List<Categoria> categoriasAvaliador = avaliador.getCategorias();

        if (categoriasAvaliador.indexOf(categoria) == -1){

            categoriasAvaliador.add(categoria);
            avaliador.setCategorias(categoriasAvaliador);
            avaliador.setId(id);
            avaliadorRepository.save(avaliador);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliador);
        mv.addObject("categoriasAvaliador", categoriasAvaliador);
        List<Categoria> categorias = new ArrayList<Categoria>();
        populaListasDeCategoriasDeAvaliador(id, categoriasAvaliador,categorias);
        mv.addObject("categorias", categorias);
        mv.setViewName("inserir-categoria-avaliador.html");
        return mv;

    }

    @RequestMapping(value = { "/excluir-categoria-avaliador" }, method = RequestMethod.GET)
    public ModelAndView excluirCategoriaAvaliador(@RequestParam("id") Long id, @RequestParam("id_cat") Long id_cat) {
        Avaliador avaliador = avaliadorRepository.getOne(id);
        Categoria categoria = categoriaRepository.getOne(id_cat);
        List<Categoria> categoriasAvaliador = avaliador.getCategorias();

        if (categoriasAvaliador.indexOf(categoria) != -1){

            categoriasAvaliador.remove(categoria);
            avaliador.setCategorias(categoriasAvaliador);
            avaliador.setId(id);
            avaliadorRepository.save(avaliador);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliador);
        mv.addObject("categoriasAvaliador", categoriasAvaliador);
        List<Categoria> categorias = new ArrayList<Categoria>();
        populaListasDeCategoriasDeAvaliador(id, categoriasAvaliador,categorias);
        mv.addObject("categorias", categorias);

        mv.setViewName("inserir-categoria-avaliador.html");
        return mv;

    }

    private void populaListasDeCategoriasDeAvaliador(Long idAvaliador, List<Categoria> categoriasAvaliador, List<Categoria> categoriasDisponiveis){
        Avaliador avaliador = avaliadorRepository.getOne(idAvaliador);
        categoriasAvaliador = avaliador.getCategorias();
        List<Categoria> todasAsCategorias = categoriaRepository.findAll();
        for (Categoria cat : todasAsCategorias) {
            if (categoriasAvaliador.indexOf(cat) == -1)
                categoriasDisponiveis.add(cat);
        }
    }

    

    @RequestMapping(value = { "/inserir-categoria-cadastro" }, method = RequestMethod.GET)
    public ModelAndView categoriasAvaliadorCadastro(@RequestParam("id") Long id) {
        Avaliador avaliador = avaliadorRepository.getOne(id);
        List<Categoria> categoriasAvaliador = new ArrayList();
        categoriasAvaliador = avaliador.getCategorias();
        ModelAndView mv = new ModelAndView();
        List<Categoria> categorias = categoriaRepository.findAll();
        mv.addObject("avaliador", avaliador);
        mv.addObject("categorias", categorias);
        mv.addObject("categoriasAvaliador", categoriasAvaliador);
        mv.setViewName("categorias-avaliador.html");
        return mv;

    }

    @RequestMapping(value = { "/inserir-categoria" }, method = RequestMethod.GET)
    public ModelAndView inserirCategoria(@RequestParam("id") Long id) {
        Avaliador avaliador = avaliadorRepository.getOne(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliador);
        List<Categoria> categoriasAvaliador = new ArrayList<Categoria>();
        List<Categoria> categorias = new ArrayList<Categoria>();
        populaListasDeCategoriasDeAvaliador(id, categoriasAvaliador,categorias);
        mv.addObject("categorias", categorias);
        mv.addObject("categoriasAvaliador", avaliador.getCategorias());
        mv.setViewName("inserir-categoria-avaliador.html");
        return mv;
    }

    @RequestMapping("/criar")
    public ModelAndView nova() {
        Avaliador avaliador = new Avaliador();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("criar-avaliador.html");
        mv.addObject("avaliador", avaliador);
        return mv;
    }

    @RequestMapping("/cadastrar")
    public ModelAndView cadastrarPlataforma() {
        Avaliador avaliador = new Avaliador();
        ModelAndView mv = new ModelAndView();
        List<Categoria> categorias = categoriaRepository.findAll();
        mv.setViewName("cadastrar-plataforma.html");
        mv.addObject("avaliador", avaliador);
        mv.addObject("categorias", categorias);
        return mv;
    }

    @PostMapping(value = "/cadastrar-plataforma.html")
    public ModelAndView cadastrarPlataforma(@Valid Avaliador avaliador, BindingResult binding, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("criar-avaliador");
            mv.addObject("avaliador", avaliador);
            return mv;
        }
        avaliadorRepository.save(avaliador);
        session.setAttribute("loggedUser", avaliador);
        mv.setViewName("index.html");
        return mv;
    }

    @PostMapping(value = "/criar-avaliador.html")
    public ModelAndView criar(@Valid Avaliador avaliador, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("criar-avaliador");
            mv.addObject("avaliador", avaliador);
            return mv;
        }
        avaliadorRepository.save(avaliador);
        mv.setViewName("redirect:/avaliador/listar-avaliadores.html");
        return mv;
    }

    @GetMapping(value = "/listar-avaliadores.html")
    public ModelAndView listar() {
        List<Avaliador> avaliadores = avaliadorRepository.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listar-avaliadores.html");
        mv.addObject("avaliadores", avaliadores);
        return mv;
    }

    @RequestMapping(value = { "/editar" }, method = RequestMethod.GET)
    public ModelAndView editar(@RequestParam("id") Long id) {
        Avaliador avaliador = avaliadorRepository.getOne(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("avaliador", avaliador);
        mv.setViewName("criar-avaliador.html");
        return mv;

    }

    @RequestMapping(value = { "/deletar" }, method = RequestMethod.GET)
    public ModelAndView deletar(@RequestParam("id") Long id) {
        avaliadorRepository.deleteById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/avaliador/listar-avaliadores.html");
        return mv;

    }

    @PostMapping(value = "/editar-avaliadores.html")
    public ModelAndView editar(@RequestParam(value = "id", required = true) Long id, @Valid Avaliador avaliador,
            BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("criar-categoria");
            mv.addObject("avaliador", avaliador);
            return mv;
        }
        avaliador.setId(id);
        avaliadorRepository.save(avaliador);
        mv.setViewName("redirect:/trabalhos/listar-avaliadores.html");
        return mv;
    }

}