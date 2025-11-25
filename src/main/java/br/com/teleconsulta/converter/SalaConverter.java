package br.com.teleconsulta.converter;

import br.com.teleconsulta.model.Sala;
import br.com.teleconsulta.service.SalaService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "salaConverter", managed = true)
public class SalaConverter implements Converter<Sala> {

    @Inject
    private SalaService salaService;

    @Override
    public Sala getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isBlank()) return null;
        return salaService.buscarSalaPorId(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Sala sala) {
        return (sala == null || sala.getId() == null) ? "" : sala.getId().toString();
    }
}
