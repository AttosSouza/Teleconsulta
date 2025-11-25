package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.repository.PacienteRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Dependent
public class PacienteService implements Serializable {

    @Inject
     private PacienteRepository pacienteRepository;

    public void salvar(Paciente paciente) {
        validarCamposObrigatorios(paciente);
        validarRegrasDeNegocio(paciente);
        validarUnicidade(paciente);

        pacienteRepository.salvar(paciente);
    }

    public List<Paciente> pesquisarComFiltro(Paciente paciente) {
        return pacienteRepository.pesquisarComFiltros(paciente);
    }

    public Paciente buscarPacientePorId(Long id) {
        return pacienteRepository.buscarPorId(id);
    }

    public void remover(Paciente paciente) {
        if (paciente == null || paciente.getId() == null) {
            throw new RuntimeException("Paciente inválido para remoção.");
        }
        pacienteRepository.remover(paciente.getId());
    }


    private void validarCamposObrigatorios(Paciente p) {

        if (p == null) {
            throw new RuntimeException("Paciente não pode ser nulo.");
        }

        if (p.getNome() == null || p.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome do paciente é obrigatório.");
        }

        if (p.getCpf() == null || p.getCpf().trim().isEmpty()) {
            throw new RuntimeException("O CPF é obrigatório.");
        }

        if (p.getSexo() == null || p.getSexo().trim().isEmpty()) {
            throw new RuntimeException("O sexo é obrigatório.");
        }

        if (p.getEndereco() == null) {
            throw new RuntimeException("O endereço do paciente é obrigatório.");
        }
    }

    private void validarRegrasDeNegocio(Paciente p) {

        if (p.getCpf().length() < 11) {
            throw new RuntimeException("CPF inválido.");
        }

        if (!p.getSexo().equalsIgnoreCase("M")
                && !p.getSexo().equalsIgnoreCase("F")
                && !p.getSexo().equalsIgnoreCase("O")) {
            throw new RuntimeException("Sexo inválido. Valores permitidos: M, F ou O.");
        }

        if (p.getDataNascimento() != null &&
                p.getDataNascimento().isAfter(LocalDate.now())) {
            throw new RuntimeException("A data de nascimento não pode ser futura.");
        }

        if (p.getEmail() != null && !p.getEmail().isBlank()) {
            if (!p.getEmail().contains("@") || !p.getEmail().contains(".")) {
                throw new RuntimeException("E-mail inválido.");
            }
        }

        if (p.getCns() != null && !p.getCns().isBlank()) {
            if (p.getCns().length() != 15) {
                throw new RuntimeException("O CNS deve conter exatamente 15 dígitos.");
            }
        }
    }

    private void validarUnicidade(Paciente p) {

        List<Paciente> existentes = pacienteRepository.pesquisarComFiltros(p);

        for (Paciente e : existentes) {

            if (p.getId() != null && p.getId().equals(e.getId())) {
                continue;
            }

            if (e.getCpf().equals(p.getCpf())) {
                throw new RuntimeException("Já existe um paciente cadastrado com este CPF.");
            }

            if (p.getEmail() != null && !p.getEmail().isBlank()
                    && e.getEmail() != null
                    && e.getEmail().equals(p.getEmail())) {
                throw new RuntimeException("Já existe um paciente cadastrado com este e-mail.");
            }
        }
    }
}
