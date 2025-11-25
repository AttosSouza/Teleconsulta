package br.com.teleconsulta.controller;

import br.com.teleconsulta.core.Controller;
import br.com.teleconsulta.model.Sala;
import br.com.teleconsulta.model.UnidadeSaude;
import br.com.teleconsulta.service.SalaService;
import br.com.teleconsulta.service.UnidadeSaudeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SalaController extends Controller implements Serializable {

    @Inject
    private SalaService salaService;

    @Inject
    private UnidadeSaudeService unidadeSaudeService;

    private Sala filtro;

    private List<Sala> salas;
    private List<UnidadeSaude> unidades;

    private Sala sala;

    private Long idSelecionado;

    @PostConstruct
    public void init() {
        filtro = new Sala();
        pesquisar();
        unidades = unidadeSaudeService.listarTodos();
        if(sala == null) {
            sala = new Sala();
        }
    }

    public void inicializarFormulario () {
        if(idSelecionado != null) {
            this.sala = salaService.buscarSalaPorId(idSelecionado);
        } else {
            this.sala = new Sala();
            this.sala.setUnidadeSaude(new UnidadeSaude());
        }
    }

    public void pesquisar() {
        salas =  salaService.pesquisarComFiltro(filtro);
    }

    public void limpar() {
        this.filtro = new Sala();
        this.sala = new Sala();
        this.idSelecionado = null;
        pesquisar();
    }

    public void salvar () {
        boolean novoCadastro = true;
        if(this.sala.getId() != null) {
            novoCadastro = false;
        }
        salaService.salvar(this.sala);
        if(novoCadastro) {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente cadastrado com sucesso");
        } else  {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente editado com sucesso");
        }
    }

    public String cancelar () {
        limpar();
        return "pesquisarSala?faces-redirect=true";
    }

    public String irParaEdicao (Sala sala) {
        return "cadastroSala?faces-redirect=true&id=" + sala.getId();
    }

    public String irParaCadastro () {
        this.limpar();
        return "cadastroSala?faces-redirect=true";
    }

    public void remover(Sala sala) {
        salaService.remover(sala);
        pesquisar();
        addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente removido com sucesso");
    }

    public Sala getFiltro() {
        return filtro;
    }

    public void setFiltro(Sala filtro) {
        this.filtro = filtro;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public List<UnidadeSaude> getUnidades() {
        return unidades;
    }

}
