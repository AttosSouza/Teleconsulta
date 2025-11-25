package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Sala;
import br.com.teleconsulta.repository.SalaRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Dependent
public class SalaService implements Serializable {

    @Inject
    private SalaRepository salaRepository;

    public void salvar(Sala sala) {
        salaRepository.salvar(sala);
    }

    public List<Sala> pesquisarComFiltro(Sala sala) {
        return salaRepository.pesquisarComFiltros(sala);
    }

    public Sala buscarSalaPorId(Long id) {
        return salaRepository.buscarPorId(id);
    }

    public void remover(Sala sala) {
        salaRepository.remover(sala.getId());
    }

    public List<Sala> listarTodos() {
        return salaRepository.listarTodos();
    }

}
