package br.com.teleconsulta.controller;

import br.com.teleconsulta.core.Controller;
import br.com.teleconsulta.model.Usuario;
import br.com.teleconsulta.service.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UsuarioController extends Controller implements Serializable {

    @Inject
    private UsuarioService usuarioService;

    private Usuario filtro;
    private List<Usuario> usuarios;

    private Usuario usuario;

    private Long idSelecionado;


    @PostConstruct
    public void init() {
        filtro = new Usuario();
        pesquisar();
        if(usuario == null) {
            usuario = new Usuario();
        }
    }

    public void inicializarFormulario () {
        if(idSelecionado != null) {
            this.usuario = usuarioService.buscarUsuarioPorId(idSelecionado);
        } else {
            this.usuario = new Usuario();
            //this.usuario.setEndereco(new Endereco());
        }
    }

    public void pesquisar() {
        usuarios =  usuarioService.pesquisarComFiltro(filtro);
    }

    public void limpar() {
        this.filtro = new Usuario();
        this.usuario = new Usuario();
        this.idSelecionado = null;
        pesquisar();
    }

    public void salvar () {
        boolean novoCadastro = true;
        if(this.usuario.getId() != null) {
            novoCadastro = false;
        }
        usuarioService.salvar(this.usuario);
        if(novoCadastro) {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente cadastrado com sucesso");
        } else  {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente editado com sucesso");
        }
    }

    public String cancelar () {
        limpar();
        // return "pesquisarUsuario?faces-redirect=true";
        return "/pesquisarUsuario.xhtml?faces-redirect=true";
    }

    public String irParaEdicao (Usuario usuario) {
        return "cadastroUsuario?faces-redirect=true&id=" + usuario.getId();
    }

    public String irParaCadastro () {
        this.limpar();
        return "cadastroUsuario?faces-redirect=true";
    }

    public void remover(Usuario usuario) {
        usuarioService.remover(usuario);
        pesquisar();
        addMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Paciente removido com sucesso");
    }

    public Usuario getFiltro() {
        return filtro;
    }

    public void setFiltro(Usuario filtro) {
        this.filtro = filtro;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(Long idSelecionado) {
        this.idSelecionado = idSelecionado;
    }
}
