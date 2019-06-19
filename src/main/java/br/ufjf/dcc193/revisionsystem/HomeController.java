package br.ufjf.dcc193.revisionsystem;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.revisionsystem.model.Avaliador;
import br.ufjf.dcc193.revisionsystem.repository.AvaliadorRepository;

@Controller
public class HomeController {

    
    @Autowired
    AvaliadorRepository avaliadorRepository;

    
    @RequestMapping({ "", "/", "/index.html" })
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Avaliador user = (Avaliador)session.getAttribute("loggedUser");
        if (user !=null){
            mv.setViewName("index");

            Long userId = user.getId();
            if (userId == null)
                mv.addObject("useridNulo", "useridNulo");
            else
                mv.addObject("useridNulo", "useridNãoNulo");
        }
        else
            mv.setViewName("index-nologged");
        return mv;
    }

    

    @RequestMapping({ "login.html" })
    public ModelAndView AvaliadorLogin() {
        ModelAndView mv = new ModelAndView();
        Avaliador avaliador = new Avaliador();
        avaliador.setNome("noBlank");
        mv.addObject("avaliador", avaliador);
        mv.setViewName("login");
        return mv;
    }
    @RequestMapping({ "logout.html" })
    public ModelAndView Logout(HttpSession session) {
        session.setAttribute("loggedUser", null);
        ModelAndView mv = new ModelAndView();
        Avaliador avaliador = new Avaliador();
        avaliador.setNome("noBlank");
        mv.addObject("avaliador", avaliador);
        mv.setViewName("login");
        return mv;
    }

    

    @PostMapping(value = "/do-login.html")
    public ModelAndView EfetuarLogin(@Valid Avaliador avaliador, BindingResult binding, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("login");
            mv.addObject("avaliador", avaliador);
            return mv;
        }
        Avaliador user = avaliadorRepository.findByEmail(avaliador.getEmail());
        if (user == null)
            mv.addObject("errorMessage", "Usuário inválido");
        else if (user.getPassword().equals(avaliador.getPassword())){
            session.setAttribute("loggedUser", avaliador);
            mv.addObject("errorMessage", "");
            mv.setViewName("index");
            return mv;
        }
        else{
            mv.addObject("errorMessage", "Usuário inválido");
        }
        mv.setViewName("login");
        mv.addObject("avaliador", avaliador);
        return mv;
    }
}