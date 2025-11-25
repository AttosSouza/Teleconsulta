package br.com.teleconsulta.service;


import br.com.teleconsulta.model.StatusReserva;
import br.com.teleconsulta.repository.StatusReservaRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Dependent
public class StatusReservaService implements Serializable {

    @Inject
    private StatusReservaRepository statusReservaRepository;

    public List<StatusReserva> listarTodos() {
        return statusReservaRepository.listarTodos();
    }

    public StatusReserva buscarPorId(Long id) {
        return statusReservaRepository.buscarPorId(id);
    }
}
