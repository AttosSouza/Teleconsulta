package br.com.teleconsulta.service;

import br.com.teleconsulta.model.Usuario;
import br.com.teleconsulta.repository.UsuarioRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@Dependent
public class UsuarioService implements Serializable {

    @Inject
    private UsuarioRepository usuarioRepository;

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public void salvar(Usuario usuario) {

        validarCamposObrigatorios(usuario);
        validarEmail(usuario.getEmail());
        validarUnicidade(usuario);

        if (usuario.getId() == null) {

            if (usuario.getDataCadastro() == null) {
                usuario.setDataCadastro(LocalDate.now());
            }

            usuarioRepository.salvar(usuario);
            return;
        }

        Usuario existente = usuarioRepository.buscarPorId(usuario.getId());
        if (existente == null) {
            throw new RuntimeException("Usuário não encontrado para edição.");
        }

        usuarioRepository.salvar(usuario);
    }

    public List<Usuario> pesquisarComFiltro(Usuario usuario) {
        return usuarioRepository.pesquisarComFiltros(usuario);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    public void remover(Usuario usuario) {
        Usuario existente = usuarioRepository.buscarPorId(usuario.getId());
        if (existente == null) {
            throw new RuntimeException("Usuário não existe para remoção.");
        }

        usuarioRepository.remover(usuario.getId());
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }


    private void validarCamposObrigatorios(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new RuntimeException("O nome é obrigatório.");
        }

        if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty()) {
            throw new RuntimeException("O CPF é obrigatório.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new RuntimeException("O e-mail é obrigatório.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
            throw new RuntimeException("A senha é obrigatória.");
        }
    }

    private void validarEmail(String email) {
        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new RuntimeException("E-mail inválido.");
        }
    }

    private void validarUnicidade(Usuario usuario) {

        List<Usuario> usuarios = usuarioRepository.listarTodos();

        for (Usuario u : usuarios) {

            boolean mesmoRegistro = usuario.getId() != null
                    && usuario.getId().equals(u.getId());

            if (mesmoRegistro) {
                continue;
            }

            if (u.getCpf().equals(usuario.getCpf())) {
                throw new RuntimeException("Já existe um usuário cadastrado com este CPF.");
            }

            if (u.getEmail().equalsIgnoreCase(usuario.getEmail())) {
                throw new RuntimeException("Já existe um usuário cadastrado com este e-mail.");
            }
        }
    }
}
