package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.model.Usuario;
import br.com.teleconsulta.repository.UsuarioRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.util.List;

@Dependent
public class UsuarioService implements Serializable {

    @Inject
    private UsuarioRepository usuarioRepository;

    public void salvar(Usuario usuario) {
        usuarioRepository.salvar(usuario);
    }

    public List<Usuario> pesquisarComFiltro(Usuario usuario) {
        return usuarioRepository.pesquisarComFiltros(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    public void remover(Usuario usuario) {
        usuarioRepository.remover(usuario.getId());
    }
}
