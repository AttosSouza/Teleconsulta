package br.com.teleconsulta.controller;

import br.com.teleconsulta.core.Controller;
import br.com.teleconsulta.model.Sala;
import br.com.teleconsulta.model.UnidadeSaude;
import br.com.teleconsulta.service.SalaService;
import br.com.teleconsulta.service.UnidadeSaudeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named
@ViewScoped
public class DisponibilidadeController extends Controller implements Serializable {

    @Inject
    private SalaService salaService;

    @Inject
    private UnidadeSaudeService unidadeSaudeService;

    private UnidadeSaude filtroUnidadeSaude;
    private LocalDateTime filtroInicio;
    private LocalDateTime filtroFim;

    private List<UnidadeSaude> unidades;
    private List<Sala> salasDisponiveis;


    @PostConstruct
    public void init() {
        unidades = unidadeSaudeService.listarTodos();
        salasDisponiveis = null;
    }

//    public void inicializarFormulario () {
//        if(idSelecionado != null) {
//            this.sala = salaService.buscarSalaPorId(idSelecionado);
//        } else {
//            this.sala = new Sala();
//            this.sala.setUnidadeSaude(new UnidadeSaude());
//        }
//    }

    public void buscarSalasDisponiveis() {
        salasDisponiveis = salaService.buscarSalasDisponiveis(
                filtroUnidadeSaude,
                filtroInicio,
                filtroFim
        );
    }

    public UnidadeSaude getFiltroUnidadeSaude() {
        return filtroUnidadeSaude;
    }

    public void setFiltroUnidadeSaude(UnidadeSaude filtroUnidadeSaude) {
        this.filtroUnidadeSaude = filtroUnidadeSaude;
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

    public List<UnidadeSaude> getUnidades() {
        return unidades;
    }

    public List<Sala> getSalasDisponiveis() {
        return salasDisponiveis;
    }

}
