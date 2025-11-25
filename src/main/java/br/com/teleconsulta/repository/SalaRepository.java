package br.com.teleconsulta.repository;

import br.com.teleconsulta.core.Repository;
import br.com.teleconsulta.model.Sala;
import br.com.teleconsulta.model.UnidadeSaude;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Dependent
public class SalaRepository extends Repository<Sala, Long> implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManager;

    public List<Sala> pesquisarComFiltros(Sala sala) {

        StringBuilder jpql = new StringBuilder("SELECT s FROM Sala s WHERE 1=1");

        if (sala.getNome() != null && !sala.getNome().trim().isBlank()) {
            jpql.append(" AND UPPER(s.nome) LIKE :nome");
        }

        if (sala.getUnidadeSaude() != null && sala.getUnidadeSaude().getId() != null) {
            jpql.append(" AND s.unidadeSaude.id = :unidadeId");
        }

        TypedQuery<Sala> query =
                entityManager.createQuery(jpql.toString(), Sala.class);

        if (sala.getNome() != null && !sala.getNome().trim().isBlank()) {
            query.setParameter("nome", "%" + sala.getNome().toUpperCase() + "%");
        }

        if (sala.getUnidadeSaude() != null && sala.getUnidadeSaude().getId() != null) {
            query.setParameter("unidadeId", sala.getUnidadeSaude().getId());
        }

        return query.getResultList();
    }

    public List<Sala> buscarSalasDisponiveis(UnidadeSaude unidade, LocalDateTime inicio, LocalDateTime fim) {
        StringBuilder jpql = new StringBuilder("SELECT s FROM Sala s WHERE 1 = 1");
        if (unidade != null && unidade.getId() != null) {
            jpql.append(" AND s.unidadeSaude.id = :unidadeId");
        }
        if (inicio != null && fim != null) {
            jpql.append(" AND NOT EXISTS (" + " SELECT r FROM Reserva r " + " WHERE r.sala.id = s.id " + " AND (" + " r.dataHoraInicio < :fim AND " + " r.dataHoraTermino > :inicio" + " )" + ")");
        }
        TypedQuery<Sala> query = entityManager.createQuery(jpql.toString(), Sala.class);
        if (unidade != null && unidade.getId() != null) {
            query.setParameter("unidadeId", unidade.getId());
        }
        if (inicio != null && fim != null) {
            query.setParameter("inicio", inicio);
            query.setParameter("fim", fim);
        }
        return query.getResultList();
    }

    public List<Sala> listarTodos() {
        return entityManager
                .createQuery("SELECT s FROM Sala s ORDER BY s.nome", Sala.class)
                .getResultList();
    }


}
