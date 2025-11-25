package br.com.teleconsulta.converter;

import br.com.teleconsulta.model.Usuario;
import br.com.teleconsulta.service.UsuarioService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "usuarioConverter", managed = true)
public class UsuarioConverter implements Converter<Usuario> {

    @Inject
    private UsuarioService usuarioService;

    @Override
    public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isBlank()) return null;
        return usuarioService.buscarUsuarioPorId(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Usuario usuario) {
        return (usuario == null || usuario.getId() == null) ? "" : usuario.getId().toString();
    }
}
