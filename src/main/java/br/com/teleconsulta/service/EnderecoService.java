package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Endereco;
import br.com.teleconsulta.repository.EnderecoRepository;
import jakarta.inject.Inject;

public class EnderecoService {

    @Inject
    private EnderecoRepository enderecoRepository;

    public void salvar(Endereco endereco) {
        enderecoRepository.salvar(endereco);
    }
}
