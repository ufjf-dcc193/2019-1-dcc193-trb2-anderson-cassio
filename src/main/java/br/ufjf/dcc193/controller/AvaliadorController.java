package br.ufjf.dcc193.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.model.Avaliador;
import br.ufjf.dcc193.repository.AvaliadorRepository;

@Controller
@RequestMapping("/avaliador")
public class AvaliadorController {
    @Autowired
    AvaliadorRepository avaliadorRepository;

    @RequestMapping({ "", "/", "/index.html" })
    public ModelAndView AvaliadorIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Avaliador-index");
        mv.addObject("nome", "Fulano");
        return mv;
    }

    @RequestMapping("/nova.html")
    public ModelAndView nova(){
            Avaliador Avaliador = new Avaliador();
            ModelAndView mv = new ModelAndView();
            mv.setViewName("Avaliador-form-new");
            mv.addObject("Avaliador", Avaliador);
            return mv;
    }

    @PostMapping(value="/criar.html")
    public ModelAndView criar(@Valid Avaliador Avaliador, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("Avaliador-form-new");
                mv.addObject("Avaliador", Avaliador);
                return mv;
            }
            avaliadorRepository.save(Avaliador);
            mv.setViewName("redirect:listar.html");
            return mv;
    }

    @GetMapping(value="/listar.html")
    public ModelAndView listar(){
        List<Avaliador> Avaliadors = avaliadorRepository.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Avaliador-list.html");
        mv.addObject("Avaliadors", Avaliadors);
        return mv;
    }
}