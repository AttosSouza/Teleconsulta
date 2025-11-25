package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Reserva;
import br.com.teleconsulta.repository.ReservaRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Dependent
public class ReservaService implements Serializable {

    @Inject
    private ReservaRepository reservaRepository;


    public void salvar(Reserva reserva) {

        validarCamposObrigatorios(reserva);
        validarRegrasDeNegocio(reserva);
        validarConflitoHorario(reserva);
        reservaRepository.salvar(reserva);
    }

    public List<Reserva> pesquisarComFiltro(Reserva filtro) {
        return reservaRepository.pesquisarComFiltros(filtro);
    }

    public Reserva buscarReservaPorId(Long id) {
        return reservaRepository.buscarPorId(id);
    }

    public void remover(Reserva reserva) {
        if (reserva == null || reserva.getId() == null) {
            throw new RuntimeException("Reserva inválida para remoção.");
        }

        Reserva existente = reservaRepository.buscarPorId(reserva.getId());

        if (existente == null) {
            throw new RuntimeException("A reserva que você está tentando remover não existe.");
        }
        reservaRepository.remover(reserva.getId());
    }


    private void validarCamposObrigatorios(Reserva reserva) {

        if (reserva == null) {
            throw new RuntimeException("A reserva não pode ser nula.");
        }

        if (reserva.getDataHoraInicio() == null) {
            throw new RuntimeException("A data/hora de início é obrigatória.");
        }

        if (reserva.getDataHoraTermino() == null) {
            throw new RuntimeException("A data/hora de término é obrigatória.");
        }

        if (reserva.getSala() == null || reserva.getSala().getId() == null) {
            throw new RuntimeException("A sala da reserva é obrigatória.");
        }

        if (reserva.getUsuario() == null || reserva.getUsuario().getId() == null) {
            throw new RuntimeException("O usuário da reserva é obrigatório.");
        }

        if (reserva.getStatus() == null || reserva.getStatus().getId() == null) {
            throw new RuntimeException("O status da reserva é obrigatório.");
        }
    }

    private void validarRegrasDeNegocio(Reserva reserva) {

        LocalDateTime inicio = reserva.getDataHoraInicio();
        LocalDateTime fim = reserva.getDataHoraTermino();

        if (!fim.isAfter(inicio)) {
            throw new RuntimeException("A data/hora final deve ser posterior à inicial.");
        }

        if (inicio.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("A reserva não pode iniciar no passado.");
        }

        if (reserva.getMotivoCancelamento() != null
                && reserva.getMotivoCancelamento().length() > 255) {
            throw new RuntimeException("O motivo do cancelamento excede 255 caracteres.");
        }

        boolean cancelada = reserva.getUsuarioCancelamento() != null
                || reserva.getDataCancelamento() != null
                || reserva.getMotivoCancelamento() != null;

        if (cancelada && reserva.getStatus().getId() == 1) { // supondo 1 = ATIVA
            throw new RuntimeException("Reserva marcada como cancelada, mas com status 'ATIVA'.");
        }
    }

    private void validarConflitoHorario(Reserva reserva) {
        if (reservaRepository.existeConflito(reserva)) {
            throw new RuntimeException(
                    "Conflito de horário! Já existe uma reserva para esta sala neste período."
            );
        }
    }
}
