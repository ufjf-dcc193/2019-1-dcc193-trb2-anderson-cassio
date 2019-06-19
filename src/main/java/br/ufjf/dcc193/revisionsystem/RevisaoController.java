package br.ufjf.dcc193.revisionsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufjf.dcc193.revisionsystem.model.Avaliador;
import br.ufjf.dcc193.revisionsystem.model.Categoria;
import br.ufjf.dcc193.revisionsystem.model.Revisao;
import br.ufjf.dcc193.revisionsystem.model.Status;
import br.ufjf.dcc193.revisionsystem.model.Trabalho;
import br.ufjf.dcc193.revisionsystem.repository.AvaliadorRepository;
import br.ufjf.dcc193.revisionsystem.repository.CategoriaRepository;
import br.ufjf.dcc193.revisionsystem.repository.RevisaoRepository;
import br.ufjf.dcc193.revisionsystem.repository.TrabalhoRepository;

@Controller
@RequestMapping("/revisao")
public class RevisaoController {


    @Autowired
    TrabalhoRepository trabalhoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    RevisaoRepository revisaoRepository;

    @Autowired
    AvaliadorRepository avaliadorRepository;

    @RequestMapping("/avaliar")
    public ModelAndView DetalheTrabalho(@RequestParam("id") Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        Avaliador user = (Avaliador)session.getAttribute("loggedUser");
        Avaliador avaliador = avaliadorRepository.getOne(user.getId());
        Trabalho trabalho = trabalhoRepository.getOne(id);
        Set<Revisao> revisoes = trabalho.getRevisoes();
        Revisao revisao = new Revisao();
        revisao.setStatus(Status.A_FAZER);
        for (Revisao r : revisoes) {
            if (r.getAvaliador().equals(avaliador)){
                revisao = r;
                break;
            }
        }
        List<Categoria> categorias = categoriaRepository.findAll();
        mv.addObject("categorias", categorias);
        mv.addObject("trabalho", trabalho);
        mv.addObject("revisao", revisao);
        mv.setViewName("trabalho-detalhe");
        return mv;
    }

    @RequestMapping("/revisar-trabalho.html")
    public ModelAndView RevisarTrabalho(@Valid Revisao revisao, Long idTrabalho, String tipoAvaliacao,HttpSession session){
        ModelAndView mv = new ModelAndView();
        Trabalho trabalho = trabalhoRepository.getOne(idTrabalho);
        Avaliador user = (Avaliador)session.getAttribute("loggedUser");
        Avaliador avaliador = avaliadorRepository.getOne(user.getId());
        
        switch (tipoAvaliacao){
            case "agora": 
                revisao.setStatus(Status.AVALIADO);
                break;
            case "depois": 
                revisao.setStatus(Status.A_FAZER);
                break;
            case "pular": 
                revisao.setStatus(Status.IMPEDIDO);
                revisao.setNota(0);
                revisao.setDescricao("");
                break;
        }
        revisao.setTrabalho(trabalho);
        revisao.setAvaliador(avaliador);
        boolean isNewRevision = revisao.getId() == null;
        revisaoRepository.save(revisao);
        if (isNewRevision)
            trabalho.addRevisao(revisao);
        trabalhoRepository.save(trabalho);
        mv.addObject("id", trabalho.getTrabalhoAreaDeConhecimento().getId());
        mv.setViewName("redirect:/trabalhos/listar-trabalhos-categoria");
        return mv;
    }


    
    @RequestMapping("/listar-revisoes-avaliador")
    public ModelAndView ListarAvaliacoes(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        
        Avaliador user = (Avaliador)session.getAttribute("loggedUser");
        Avaliador avaliador = avaliadorRepository.getOne(user.getId());

        List<Revisao> revisoes = avaliador.getRevisoesAvaliadas();
        mv.addObject("revisoes", revisoes);
        mv.setViewName("listar-revisoes-avaliador");
        return mv;
    }

    @RequestMapping("/detalhe-revisao")
    public ModelAndView DetalheAvaliacao (@RequestParam("id") Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        
        Revisao revisao = revisaoRepository.getOne(id);

        mv.addObject("revisao", revisao);
        mv.addObject("tituloTrabalho", revisao.getTrabalho().getTitulo());
        List<Status> statusEnum = new ArrayList<>();
        statusEnum.add(Status.AVALIADO);
        statusEnum.add(Status.VALIDADO);
        statusEnum.add(Status.INVALIDADO);
        mv.addObject("statusEnum", statusEnum);
        mv.setViewName("detalhe-revisao");
        return mv;
    }
    
    @RequestMapping("/editar-revisao.html")
    public ModelAndView EditarAvaliacao(Revisao revisao, int statusEnum, HttpSession session){
        ModelAndView mv = new ModelAndView();

        Revisao current = revisaoRepository.getOne(revisao.getId());
        current.setStatus(Status.getStatusByValue(statusEnum));
        revisaoRepository.save(current);
        
        Avaliador user = (Avaliador)session.getAttribute("loggedUser");
        Avaliador avaliador = avaliadorRepository.getOne(user.getId());

        List<Revisao> revisoes = avaliador.getRevisoesAvaliadas();
        mv.addObject("revisoes", revisoes);
        mv.setViewName("listar-revisoes-avaliador");
        return mv;
    }
    

}