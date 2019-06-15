package br.ufjf.dcc193.tomatoban;

import java.util.List;

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

import br.ufjf.dcc193.tomatoban.model.Categoria;
import br.ufjf.dcc193.tomatoban.repository.CategoriaRepository;


@Controller
@RequestMapping("/trabalhos")
public class TrabalhoController {
    
    
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
            Categoria categoria = new Categoria();
            ModelAndView mv = new ModelAndView();
            mv.setViewName("criar-categoria.html");
            mv.addObject("categoria", categoria);
            return mv;
    }

    @PostMapping(value="/criar-categoria.html")
    public ModelAndView criar(@Valid Categoria categoria, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("criar-categoria");
                mv.addObject("categoria", categoria);
                return mv;
            }
            categoriaRepository.save(categoria);
            mv.setViewName("redirect:/categorias/listar-categorias.html");
            return mv;
    }

    @GetMapping(value="/listar-categorias.html")
    public ModelAndView listar(){
        List<Categoria> categorias = categoriaRepository.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("listar-categorias.html");
        mv.addObject("categorias", categorias);
        return mv;
    }


    @RequestMapping(value = { "/editar" }, method = RequestMethod.GET)
    public ModelAndView editar(@RequestParam("id") Long id) {
        Categoria categoria = categoriaRepository.getOne(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("categoria", categoria);
        mv.setViewName("editar-categoria.html");
        return mv;

}

  
@RequestMapping(value = { "/deletar" }, method = RequestMethod.GET)
    public ModelAndView deletar(@RequestParam("id") Long id) {
        categoriaRepository.deleteById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/categorias/listar-categorias.html");
        return mv;

}

@PostMapping(value="/editar-categoria.html")
public ModelAndView editar(@RequestParam(value = "id", required = true) Long id ,@Valid Categoria categoria, BindingResult binding){
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
            mv.setViewName("criar-categoria");
            mv.addObject("categoria", categoria);
            return mv;
        }
        categoria.setId(id);
        categoriaRepository.save(categoria);
        mv.setViewName("redirect:/trabalhos/listar-categorias.html");
        return mv;
}

}