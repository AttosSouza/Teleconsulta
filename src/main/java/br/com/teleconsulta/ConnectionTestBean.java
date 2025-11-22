package br.com.teleconsulta;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

@Named
@RequestScoped
public class ConnectionTestBean implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManagerPostgres;

    @Inject
    @Named("emSqlite")
    private EntityManager entityManagerSqlite;

    private void addMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void testPostgresConnection() {
        try {
            entityManagerPostgres.createNativeQuery("SELECT 1").getSingleResult();
            addMessage("Sucesso", "Conexão PostgreSQL OK!", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage("ERRO", "Falha na conexão PostgreSQL.", FacesMessage.SEVERITY_ERROR);
            System.err.println("Erro ao testar PostgreSQL: " + e.getMessage());
        }
    }

    public void testSqliteConnection() {
        try {
            entityManagerSqlite.createNativeQuery("SELECT 1").getSingleResult();
            addMessage("Sucesso", "Conexão SQLite OK! O arquivo .db está acessível.", FacesMessage.SEVERITY_INFO);
        } catch (Exception e) {
            addMessage("ERRO", "Falha na conexão SQLite.", FacesMessage.SEVERITY_ERROR);
            System.err.println("Erro ao testar SQLite: " + e.getMessage());
        }
    }
}
