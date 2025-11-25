package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Reserva;
import br.com.teleconsulta.repository.ReservaRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Dependent
public class ReservaService implements Serializable {

    @Inject
    private ReservaRepository reservaRepository;


    public void salvar(Reserva reserva) {

        if (reservaRepository.existeConflito(reserva)) {
            throw new RuntimeException("Conflito de horário! Já existe uma reserva para esta sala neste período.");
        }

        reservaRepository.salvar(reserva);
    }

    public List<Reserva> pesquisarComFiltro(Reserva filtro) {
        return reservaRepository.pesquisarComFiltros(filtro);
    }

    public Reserva buscarReservaPorId(Long id) {
        return reservaRepository.buscarPorId(id);
    }

    public void remover(Reserva reserva) {
        reservaRepository.remover(reserva.getId());
    }
}
