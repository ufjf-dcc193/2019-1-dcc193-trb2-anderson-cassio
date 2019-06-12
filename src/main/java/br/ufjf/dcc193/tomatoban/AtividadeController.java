package br.ufjf.dcc193.tomatoban;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/atividade")
public class AtividadeController {
    @Autowired
    AtividadeRepository atvRepo;

    @RequestMapping({ "", "/", "/index.html" })
    public ModelAndView atividadeIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atividade-index");
        mv.addObject("nome", "Fulano");
        return mv;
    }

    @RequestMapping("/nova.html")
    public ModelAndView nova(){
            Atividade atividade = new Atividade();
            ModelAndView mv = new ModelAndView();
            mv.setViewName("atividade-form-new");
            mv.addObject("atividade", atividade);
            return mv;
    }

    @PostMapping(value="/criar.html")
    public ModelAndView criar(@Valid Atividade atividade, BindingResult binding){
            ModelAndView mv = new ModelAndView();
            if(binding.hasErrors()){
                mv.setViewName("atividade-form-new");
                mv.addObject("atividade", atividade);
                return mv;
            }
            atvRepo.save(atividade);
            mv.setViewName("redirect:listar.html");
            return mv;
    }

    @GetMapping(value="/listar.html")
    public ModelAndView listar(){
        List<Atividade> atividades = atvRepo.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("atividade-list.html");
        mv.addObject("atividades", atividades);
        return mv;
    }
}