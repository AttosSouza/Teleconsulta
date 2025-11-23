package br.com.teleconsulta.controller;

import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.service.PacienteService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PacienteController implements Serializable {

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
        if(paciente != null) {
            paciente = new Paciente();
        }
    }

    public void inicializarFormulario () {
        if(idSelecionado != null) {
            this.paciente = pacienteService.buscarPacientePorId(idSelecionado);
        }
    }

    public void pesquisar() {
        pacientes =  pacienteService.pesquisarComFiltro(filtro);
    }

    public void limpar() {
        filtro = new Paciente();
        pesquisar();
    }

    public void editar (Paciente paciente) {

    }

    public String cancelar () {
        this.paciente = new Paciente();
        return "pesquisarPaciente?faces-redirect=true";
    }

    public String irParaEdicao (Paciente paciente) {
        return "cadastroPaciente?faces-redirect=true&id=" + paciente.getId();
    }

    public void remover(Paciente paciente) {
       // pacienteService.remover(paciente);
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
