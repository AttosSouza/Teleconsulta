package br.com.teleconsulta.repository;


import br.com.teleconsulta.core.Repository;
import br.com.teleconsulta.model.StatusReserva;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;

@Dependent
public class StatusReservaRepository extends Repository<StatusReserva, Long> implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManager;

    public List<StatusReserva> listarTodos() {
        return entityManager
                .createQuery("SELECT s FROM StatusReserva s ORDER BY s.id", StatusReserva.class)
                .getResultList();
    }
}
