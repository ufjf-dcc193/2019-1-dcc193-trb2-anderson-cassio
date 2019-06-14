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

import br.ufjf.dcc193.tomatoban.model.Avaliador;
import br.ufjf.dcc193.tomatoban.repository.AvaliadorRepository;


@Controller
@RequestMapping("/avaliador")
public class AvaliadorController {
    
    
    @Autowired
    AvaliadorRepository avaliadorRepository;

    @RequestMapping({ "", "/", "/index.html" })
    public ModelAndView AvaliadorIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atividade-index");
        mv.addObject("nome", "Fulano");
        return mv;
    }

    

    
    @RequestMapping("/criar")
    public ModelAndView nova(){
            Avaliador avaliador = new Avaliador();
            ModelAndView mv = new ModelAndView();
            mv.setViewName("criar-avaliador.html");
            mv.addObject("avaliador", avaliador);
            return mv;
    }

    @PostMapping(value="/criar-avaliador.html")
    public ModelAndView criar(@Valid Avaliador avaliador, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("criar-avaliador");
                mv.addObject("avaliador", avaliador);
                return mv;
            }
            avaliadorRepository.save(avaliador);
            mv.setViewName("redirect:/avaliador/listar-avaliadores.html");
            return mv;
    }

    @GetMapping(value="/listar-avaliadores.html")
    public ModelAndView listar(){
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
        mv.setViewName("editar-avaliador.html");
        return mv;

}

  
@RequestMapping(value = { "/deletar" }, method = RequestMethod.GET)
    public ModelAndView deletar(@RequestParam("id") Long id) {
        avaliadorRepository.deleteById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/avaliador/listar-avaliadores.html");
        return mv;

}

@PostMapping(value="/editar-avaliadores.html")
public ModelAndView editar(@RequestParam(value = "id", required = true) Long id ,@Valid Avaliador avaliador, BindingResult binding){
        ModelAndView mv = new ModelAndView();
        if(binding.hasErrors()){
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