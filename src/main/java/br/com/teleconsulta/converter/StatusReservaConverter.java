package br.com.teleconsulta.converter;

import br.com.teleconsulta.model.StatusReserva;
import br.com.teleconsulta.service.StatusReservaService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;

@FacesConverter(value = "statusReservaConverter", managed = true)
public class StatusReservaConverter implements Converter<StatusReserva> {

    @Inject
    private StatusReservaService statusReservaService;

    @Override
    public StatusReserva getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isBlank()) return null;
        return statusReservaService.buscarPorId(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, StatusReserva status) {
        return (status == null || status.getId() == null) ? "" : status.getId().toString();
    }
}
