package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Sala;
import br.com.teleconsulta.model.UnidadeSaude;
import br.com.teleconsulta.repository.SalaRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Dependent
public class SalaService implements Serializable {

    @Inject
    private SalaRepository salaRepository;

    public void salvar(Sala sala) {
        validarCamposObrigatorios(sala);
        validarRegrasDeNegocio(sala);
        validarUnicidadeDentroDaUnidade(sala);

        salaRepository.salvar(sala);
    }

    public List<Sala> pesquisarComFiltro(Sala sala) {
        return salaRepository.pesquisarComFiltros(sala);
    }

    public Sala buscarSalaPorId(Long id) {
        return salaRepository.buscarPorId(id);
    }

    public void remover(Sala sala) {
        if (sala == null || sala.getId() == null) {
            throw new RuntimeException("Sala inválida para remoção.");
        }

        Sala existente = salaRepository.buscarPorId(sala.getId());
        if (existente == null) {
            throw new RuntimeException("A sala que você está tentando remover não existe.");
        }

        salaRepository.remover(sala.getId());
    }

    public List<Sala> listarTodos() {
        return salaRepository.listarTodos();
    }

    public List<Sala> buscarSalasDisponiveis(UnidadeSaude unidade, LocalDateTime inicio, LocalDateTime fim) {
        if (unidade == null || unidade.getId() == null) {
            throw new RuntimeException("É necessário informar uma unidade de saúde para buscar disponibilidade.");
        }

        if (inicio == null || fim == null) {
            throw new RuntimeException("O período deve conter data/hora de início e término.");
        }

        if (!fim.isAfter(inicio)) {
            throw new RuntimeException("A data/hora final deve ser posterior à inicial.");
        }
        return salaRepository.buscarSalasDisponiveis(unidade, inicio, fim);
    }

    private void validarCamposObrigatorios(Sala sala) {

        if (sala == null) {
            throw new RuntimeException("A sala não pode ser nula.");
        }

        if (sala.getNome() == null || sala.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome da sala é obrigatório.");
        }

        if (sala.getCapacidade() == null) {
            throw new RuntimeException("A capacidade da sala é obrigatória.");
        }

        if (sala.getUnidadeSaude() == null || sala.getUnidadeSaude().getId() == null) {
            throw new RuntimeException("A unidade de saúde da sala é obrigatória.");
        }
    }

    private void validarRegrasDeNegocio(Sala sala) {

        if (sala.getCapacidade() <= 0) {
            throw new RuntimeException("A capacidade da sala deve ser maior que zero.");
        }

        if (sala.getNome().length() > 50) {
            throw new RuntimeException("O nome da sala não pode exceder 50 caracteres.");
        }
    }

    private void validarUnicidadeDentroDaUnidade(Sala sala) {

        Sala filtro = new Sala();
        filtro.setNome(sala.getNome());
        filtro.setUnidadeSaude(sala.getUnidadeSaude());

        List<Sala> existentes = salaRepository.pesquisarComFiltros(filtro);

        for (Sala s : existentes) {

            if (sala.getId() != null && sala.getId().equals(s.getId())) {
                continue;
            }

            if (s.getNome().equalsIgnoreCase(sala.getNome())) {
                throw new RuntimeException(
                        "Já existe uma sala com este nome cadastrada na unidade de saúde selecionada."
                );
            }
        }
    }

}
