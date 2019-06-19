package br.ufjf.dcc193.revisionsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

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

import br.ufjf.dcc193.revisionsystem.model.Categoria;
import br.ufjf.dcc193.revisionsystem.model.Trabalho;
import br.ufjf.dcc193.revisionsystem.repository.CategoriaRepository;
import br.ufjf.dcc193.revisionsystem.repository.TrabalhoRepository;

@Controller
@RequestMapping("/trabalhos")
public class TrabalhoController {

    @Autowired
    TrabalhoRepository trabalhoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @RequestMapping({ "", "/", "/index.html" })
    public ModelAndView CategoriaIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atividade-index");
        mv.addObject("nome", "Fulano");
        return mv;
    }

    @RequestMapping("/criar")
    public ModelAndView nova() {
        List<Categoria> categorias = categoriaRepository.findAll();
        Trabalho trabalho = new Trabalho();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("criar-trabalho.html");
        mv.addObject("trabalho", trabalho);
        mv.addObject("categorias", categorias);
        return mv;
    }

    @PostMapping(value = "/criar-trabalho.html")
    public ModelAndView criar(@Valid Trabalho trabalho, BindingResult binding,
            Long id_cat) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("criar-trabalho");
            mv.addObject("trabalho", trabalho);
            return mv;
        }
        //trabalho.setTrabalhoAreaDeConhecimento(categoriaRepository.getOne(id_cat));
        trabalhoRepository.save(trabalho);
        mv.setViewName("redirect:/trabalhos/listar-trabalhos.html");
        return mv;
    }

    @GetMapping(value = "/listar-trabalhos.html")
    public ModelAndView listar() {
        List<Trabalho> trabalhos = trabalhoRepository.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listar-trabalhos.html");
        mv.addObject("trabalhos", trabalhos);
        return mv;
    }

    @GetMapping(value = "/listar-trabalhos-categoria")
    public ModelAndView listarTrabalhosCategoria(@RequestParam("id") Long id) {
        Categoria categoria = categoriaRepository.getOne(id);
        List<Trabalho> trabalhos = new ArrayList<>(categoria.getTrabalhos());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listar-trabalhos-a-avaliar.html");

        Collections.sort(trabalhos, new Comparator<Trabalho>() {
            @Override
            public int compare(Trabalho  trabalho1, Trabalho  trabalho2)
            {
    
                return  trabalho1.getRevisoesAvaliadasCount().compareTo(trabalho2.getRevisoesAvaliadasCount());
            }
        });



        mv.addObject("trabalhos", trabalhos);
        return mv;
    }

    

    @RequestMapping(value = { "/editar" }, method = RequestMethod.GET)
    public ModelAndView editar(@RequestParam("id") Long id) {
        List<Categoria> categorias = categoriaRepository.findAll();
        Trabalho trabalho = trabalhoRepository.getOne(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("categorias", categorias);
        mv.addObject("trabalho", trabalho);
        mv.setViewName("criar-trabalho.html");
        return mv;

    }

    @RequestMapping(value = { "/deletar" }, method = RequestMethod.GET)
    public ModelAndView deletar(@RequestParam("id") Long id) {
        trabalhoRepository.deleteById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/trabalhos/listar-trabalhos.html");
        return mv;

    }

    @PostMapping(value = "/editar-trabalho.html")
    public ModelAndView editar(@RequestParam(value = "id", required = true) Long id, @Valid Trabalho trabalho,
            BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("criar-trabalho");
            mv.addObject("trabalho", trabalho);
            return mv;
        }
        trabalho.setId(id);
        trabalhoRepository.save(trabalho);
        mv.setViewName("redirect:/trabalhos/listar-trabalhos.html");
        return mv;
    }

}