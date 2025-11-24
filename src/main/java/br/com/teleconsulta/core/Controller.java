package br.com.teleconsulta.core;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public abstract class Controller {
    public void addMessage(FacesMessage.Severity severityInfo, String titulo, String descricao) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severityInfo, titulo, descricao));
    }
}
