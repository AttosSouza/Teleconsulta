package br.com.teleconsulta.service;

import br.com.teleconsulta.model.UnidadeSaude;
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

        validarCamposObrigatorios(unidadeSaude);
        validarTamanhos(unidadeSaude);
        validarUnicidade(unidadeSaude);

        if (unidadeSaude.getId() != null) {
            UnidadeSaude existente = unidadeSaudeRepository.buscarPorId(unidadeSaude.getId());
            if (existente == null) {
                throw new RuntimeException("Unidade de saúde não encontrada para edição.");
            }
        }

        unidadeSaudeRepository.salvar(unidadeSaude);
    }

    public List<UnidadeSaude> pesquisarComFiltro(UnidadeSaude unidadeSaude) {
        return unidadeSaudeRepository.pesquisarComFiltros(unidadeSaude);
    }

    public UnidadeSaude buscarUnidadeDeSaudePorId(Long id) {
        return unidadeSaudeRepository.buscarPorId(id);
    }

    public void remover(UnidadeSaude unidadeSaude) {
        UnidadeSaude existente = unidadeSaudeRepository.buscarPorId(unidadeSaude.getId());

        if (existente == null) {
            throw new RuntimeException("Unidade de saúde não encontrada para remoção.");
        }

        unidadeSaudeRepository.remover(unidadeSaude.getId());
    }

    public List<UnidadeSaude> listarTodos() {
        return unidadeSaudeRepository.listarTodos();
    }

    private boolean isCnpjValido(String cnpj) {
        if (cnpj == null) return false;
        return cnpj.matches("\\d{14}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    }

    private void validarCamposObrigatorios(UnidadeSaude u) {

        if (u.getCnpj() == null || u.getCnpj().trim().isEmpty()) {
            throw new RuntimeException("O CNPJ é obrigatório.");
        }

        if (u.getNome() == null || u.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome é obrigatório.");
        }

        if (u.getEndereco() == null) {
            throw new RuntimeException("O endereço é obrigatório.");
        }
    }

    private void validarUnicidade(UnidadeSaude unidade) {

        List<UnidadeSaude> todas = unidadeSaudeRepository.listarTodos();

        for (UnidadeSaude u : todas) {

            boolean mesmoRegistro = unidade.getId() != null &&
                    unidade.getId().equals(u.getId());

            if (mesmoRegistro) continue;

            if (u.getCnpj().equals(unidade.getCnpj())) {
                throw new RuntimeException("Já existe uma unidade de saúde com este CNPJ.");
            }
        }
    }

    private void validarTamanhos(UnidadeSaude u) {

        if (u.getSigla() != null && u.getSigla().length() > 10) {
            throw new RuntimeException("A sigla deve ter no máximo 10 caracteres.");
        }

        if (u.getCnes() != null && u.getCnes().length() > 15) {
            throw new RuntimeException("O CNES deve ter no máximo 15 caracteres.");
        }

        // Validação opcional de CNPJ (formato)
        if (!isCnpjValido(u.getCnpj())) {
            throw new RuntimeException("CNPJ inválido.");
        }
    }
}
