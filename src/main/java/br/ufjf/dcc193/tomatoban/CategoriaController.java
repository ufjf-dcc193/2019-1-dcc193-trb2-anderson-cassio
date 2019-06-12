package br.ufjf.dcc193.tomatoban;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.tomatoban.model.Categoria;
import br.ufjf.dcc193.tomatoban.repository.CategoriaRepository;


@Controller
@RequestMapping("/Categoria")
public class CategoriaController {
    
    
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
    public ModelAndView nova(){
            Categoria Categoria = new Categoria();
            ModelAndView mv = new ModelAndView();
            mv.setViewName("criar-Categoria.html");
            mv.addObject("Categoria", Categoria);
            return mv;
    }

    @PostMapping(value="/criar-categoria.html")
    public ModelAndView criar(@Valid Categoria Categoria, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("criar-categoria");
                mv.addObject("Categoria", Categoria);
                return mv;
            }
            categoriaRepository.save(Categoria);
            mv.setViewName("redirect:/Categoria/listar-categorias.html");
            return mv;
    }

    @GetMapping(value="/listar-categorias.html")
    public ModelAndView listar(){
        List<Categoria> Categoriaes = categoriaRepository.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listar-Categoriaes.html");
        mv.addObject("Categoriaes", Categoriaes);
        return mv;
    }
}