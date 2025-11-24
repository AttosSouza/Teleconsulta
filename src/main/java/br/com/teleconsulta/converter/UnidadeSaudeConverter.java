package br.com.teleconsulta.converter;

import br.com.teleconsulta.model.UnidadeSaude;
import br.com.teleconsulta.repository.UnidadeSaudeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@ApplicationScoped
@FacesConverter(value = "unidadeSaudeConverter", managed = true)
public class UnidadeSaudeConverter implements Converter<UnidadeSaude> {

    @Inject
    private UnidadeSaudeRepository repository;

    @Override
    public UnidadeSaude getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return repository.buscarPorId(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, UnidadeSaude value) {
        if (value == null || value.getId() == null) {
            return "";
        }
        return value.getId().toString();
    }
}
