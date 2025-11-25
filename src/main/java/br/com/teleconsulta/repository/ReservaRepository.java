package br.com.teleconsulta.repository;

import br.com.teleconsulta.core.Repository;
import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.model.Reserva;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

@Dependent
public class ReservaRepository extends Repository<Reserva, Long> implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManager;

    public List<Reserva> pesquisarComFiltros(Reserva filtro) {

        StringBuilder jpql = new StringBuilder("SELECT r FROM Reserva r WHERE 1=1");

        if (filtro.getSala() != null && filtro.getSala().getId() != null) {
            jpql.append(" AND r.sala.id = :salaId");
        }

        if (filtro.getUsuario() != null && filtro.getUsuario().getId() != null) {
            jpql.append(" AND r.usuario.id = :usuarioId");
        }

        if (filtro.getDataHoraInicio() != null) {
            jpql.append(" AND r.dataHoraInicio >= :inicio");
        }

        if (filtro.getDataHoraTermino() != null) {
            jpql.append(" AND r.dataHoraTermino <= :fim");
        }

        TypedQuery<Reserva> query = entityManager.createQuery(jpql.toString(), Reserva.class);

        if (filtro.getSala() != null && filtro.getSala().getId() != null) {
            query.setParameter("salaId", filtro.getSala().getId());
        }

        if (filtro.getUsuario() != null && filtro.getUsuario().getId() != null) {
            query.setParameter("usuarioId", filtro.getUsuario().getId());
        }

        if (filtro.getDataHoraInicio() != null) {
            query.setParameter("inicio", filtro.getDataHoraInicio());
        }

        if (filtro.getDataHoraTermino() != null) {
            query.setParameter("fim", filtro.getDataHoraTermino());
        }

        return query.getResultList();
    }

    public boolean existeConflito(Reserva reserva) {
        String jpql =
                "SELECT COUNT(r) FROM Reserva r " +
                        "WHERE r.sala.id = :salaId " +
                        "AND r.id <> :idReserva " +
                        "AND r.status.id <> 3 " + // ignora CANCELADA
                        "AND (r.dataHoraInicio < :fim AND r.dataHoraTermino > :inicio)";

        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("salaId", reserva.getSala().getId());
        query.setParameter("inicio", reserva.getDataHoraInicio());
        query.setParameter("fim", reserva.getDataHoraTermino());
        query.setParameter("idReserva", reserva.getId() == null ? -1 : reserva.getId());

        return query.getSingleResult() > 0;
    }

}
