package br.com.teleconsulta.controller;

import br.com.teleconsulta.core.Controller;
import br.com.teleconsulta.model.Endereco;
import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.service.PacienteService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class PacienteController extends Controller implements Serializable {

    @Inject
    private PacienteService pacienteService;

    private Paciente filtro;
    private List<Paciente> pacientes;

    private Paciente paciente;

    private Long idSelecionado;


    @PostConstruct
    public void init() {
        filtro = new Paciente();
        pesquisar();
        if(paciente == null) {
            paciente = new Paciente();
        }
    }

    public void inicializarFormulario () {
        if(idSelecionado != null) {
            this.paciente = pacienteService.buscarPacientePorId(idSelecionado);
        } else {
            this.paciente = new Paciente();
            this.paciente.setEndereco(new Endereco());
        }
    }

    public void pesquisar() {
        pacientes =  pacienteService.pesquisarComFiltro(filtro);
    }

    public void limpar() {
        this.filtro = new Paciente();
        this.paciente = new Paciente();
        this.idSelecionado = null;
        pesquisar();
    }

    public void salvar () {
        boolean novoCadastro = true;
        if(this.paciente.getId() != null) {
            novoCadastro = false;
        }
        pacienteService.salvar(this.paciente);
        if(novoCadastro) {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente cadastrado com sucesso");
        } else  {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente editado com sucesso");
        }
    }

    public String cancelar () {
        limpar();
        return "pesquisarPaciente?faces-redirect=true";
    }

    public String irParaEdicao (Paciente paciente) {
        return "cadastroPaciente?faces-redirect=true&id=" + paciente.getId();
    }

    public String irParaCadastro () {
        this.limpar();
        return "cadastroPaciente?faces-redirect=true";
    }

    public void remover(Paciente paciente) {
        pacienteService.remover(paciente);
        pesquisar();
        addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente removido com sucesso");
    }

    public Paciente getFiltro() {
        return filtro;
    }

    public void setFiltro(Paciente filtro) {
        this.filtro = filtro;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }
}
