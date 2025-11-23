package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.repository.PacienteRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Dependent
public class PacienteService implements Serializable {

    @Inject
     private PacienteRepository pacienteRepository;

    public void salvar(Paciente paciente) {
        pacienteRepository.salvar(paciente);
    }

    public List<Paciente> pesquisarComFiltro(Paciente paciente) {
        return pacienteRepository.pesquisarComFiltros(paciente);
    }

    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.buscarPorId(id);
    }

    public void remover(Paciente paciente) {
        pacienteRepository.remover(paciente.getId());
    }
}
