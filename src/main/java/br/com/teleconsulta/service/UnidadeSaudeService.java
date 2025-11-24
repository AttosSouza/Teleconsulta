package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.model.UnidadeSaude;
import br.com.teleconsulta.repository.PacienteRepository;
import br.com.teleconsulta.repository.UnidadeSaudeRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Dependent
public class UnidadeSaudeService implements Serializable {

    @Inject
    private UnidadeSaudeRepository unidadeSaudeRepository;

    public void salvar(UnidadeSaude unidadeSaude) {
        unidadeSaudeRepository.salvar(unidadeSaude);
    }

    public List<UnidadeSaude> pesquisarComFiltro(UnidadeSaude unidadeSaude) {
        return unidadeSaudeRepository.pesquisarComFiltros(unidadeSaude);
    }

    public UnidadeSaude buscarUnidadeDeSaudePorId(Long id) {
        return unidadeSaudeRepository.buscarPorId(id);
    }

    public void remover(UnidadeSaude unidadeSaude) {
        unidadeSaudeRepository.remover(unidadeSaude.getId());
    }
}
