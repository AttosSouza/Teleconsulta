package br.com.teleconsulta.controller;

import br.com.teleconsulta.core.Controller;
import br.com.teleconsulta.model.Endereco;
import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.model.UnidadeSaude;
import br.com.teleconsulta.service.PacienteService;
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
public class UnidadeSaudeController extends Controller implements Serializable {

    @Inject
    private UnidadeSaudeService unidadeSaudeService;

    private UnidadeSaude filtro;
    private List<UnidadeSaude> unidadeSaudes;

    private UnidadeSaude unidadeSaude;

    private Long idSelecionado;


    @PostConstruct
    public void init() {
        filtro = new UnidadeSaude();
        pesquisar();
        if(unidadeSaude == null) {
            unidadeSaude = new UnidadeSaude();
        }
    }

    public void inicializarFormulario () {
        if(idSelecionado != null) {
            this.unidadeSaude = unidadeSaudeService.buscarUnidadeDeSaudePorId(idSelecionado);
        } else {
            this.unidadeSaude = new UnidadeSaude();
            this.unidadeSaude.setEndereco(new Endereco());
        }
    }

    public void pesquisar() {
        unidadeSaudes =  unidadeSaudeService.pesquisarComFiltro(filtro);
    }

    public void limpar() {
        this.filtro = new UnidadeSaude();
        this.unidadeSaude = new UnidadeSaude();
        this.idSelecionado = null;
        pesquisar();
    }

    public void salvar () {
        boolean novoCadastro = true;
        if(this.unidadeSaude.getId() != null) {
            novoCadastro = false;
        }
        unidadeSaudeService.salvar(this.unidadeSaude);
        if(novoCadastro) {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente cadastrado com sucesso");
        } else  {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente editado com sucesso");
        }
    }

    public String cancelar () {
        limpar();
        return "pesquisarUnidadeSaude?faces-redirect=true";
    }

    public String irParaEdicao (UnidadeSaude unidadeSaude) {
        return "cadastroUnidadeSaude?faces-redirect=true&id=" + unidadeSaude.getId();
    }

    public String irParaCadastro () {
        this.limpar();
        return "cadastroUnidadeSaude?faces-redirect=true";
    }

    public void remover(UnidadeSaude unidadeSaude) {
        unidadeSaudeService.remover(unidadeSaude);
        pesquisar();
        addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente removido com sucesso");
    }

    public UnidadeSaude getFiltro() {
        return filtro;
    }

    public void setFiltro(UnidadeSaude filtro) {
        this.filtro = filtro;
    }

    public List<UnidadeSaude> getUnidadeSaudes() {
        return unidadeSaudes;
    }

    public void setUnidadeSaudes(List<UnidadeSaude> unidadeSaudes) {
        this.unidadeSaudes = unidadeSaudes;
    }

    public UnidadeSaude getUnidadeSaude() {
        return unidadeSaude;
    }

    public void setUnidadeSaude(UnidadeSaude unidadeSaude) {
        this.unidadeSaude = unidadeSaude;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }
}
