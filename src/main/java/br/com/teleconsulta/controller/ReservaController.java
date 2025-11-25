package br.com.teleconsulta.controller;

import br.com.teleconsulta.core.Controller;
import br.com.teleconsulta.model.*;
import br.com.teleconsulta.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class ReservaController extends Controller implements Serializable {

    @Inject
    private ReservaService reservaService;

    @Inject
    private SalaService salaService;

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private StatusReservaService statusReservaService;

    private Reserva filtro;
    private Reserva reserva;
    private LocalDateTime filtroInicio;
    private LocalDateTime filtroFim;

    private List<Reserva> reservas;
    private List<Sala> salas;
    private List<Usuario> usuarios;
    private List<StatusReserva> statusList;

    private Long idSelecionado;

    @PostConstruct
    public void init() {
        filtro = new Reserva();
        reserva = new Reserva();

        salas = salaService.listarTodos();
        usuarios = usuarioService.listarTodos();
        statusList = statusReservaService.listarTodos();

        pesquisar();
    }

    public void inicializarFormulario() {
        if (idSelecionado != null) {
            reserva = reservaService.buscarReservaPorId(idSelecionado);
        } else {
            reserva = new Reserva();
        }
    }

    public void pesquisar() {
        filtro.setDataHoraInicio(filtroInicio);
        filtro.setDataHoraTermino(filtroFim);

        reservas = reservaService.pesquisarComFiltro(filtro);
    }

//    public void salvar() {
//        try {
//            boolean novo = reserva.getId() == null;
//
//            reservaService.salvar(reserva);
//
//            if (novo)
//                addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Reserva cadastrada com sucesso.");
//            else
//                addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Reserva atualizada com sucesso.");
//
//        } catch (Exception e) {
//            addMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage());
//        }
//    }

    public void salvar () {
        boolean novoCadastro = true;
        if(this.reserva.getId() != null) {
            novoCadastro = false;
        }
        reservaService.salvar(this.reserva);
        if(novoCadastro) {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Reserva cadastrado com sucesso");
        } else  {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Reserva editado com sucesso");
        }
    }

    public void remover(Reserva reserva) {
        reservaService.remover(reserva);
        pesquisar();
        addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Reserva removida.");
    }

    public void limpar() {
        filtro = new Reserva();
        filtroInicio = null;
        filtroFim = null;
        pesquisar();
    }

    public String irParaEdicao(Reserva reserva) {
        return "cadastroReserva?faces-redirect=true&id=" + reserva.getId();
    }

    public String irParaCadastro() {
        return "cadastroReserva?faces-redirect=true";
    }

    public String cancelar() {
        return "/pesquisarReserva.xhtml?faces-redirect=true";
    }

    public Reserva getFiltro() {
        return filtro;
    }

    public void setFiltro(Reserva filtro) {
        this.filtro = filtro;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<StatusReserva> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusReserva> statusList) {
        this.statusList = statusList;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }

    public LocalDateTime getFiltroInicio() {
        return filtroInicio;
    }

    public void setFiltroInicio(LocalDateTime filtroInicio) {
        this.filtroInicio = filtroInicio;
    }

    public LocalDateTime getFiltroFim() {
        return filtroFim;
    }

    public void setFiltroFim(LocalDateTime filtroFim) {
        this.filtroFim = filtroFim;
    }


}
