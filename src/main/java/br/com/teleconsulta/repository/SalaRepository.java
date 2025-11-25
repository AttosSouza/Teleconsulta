package br.com.teleconsulta.repository;

import br.com.teleconsulta.core.Repository;
import br.com.teleconsulta.model.Sala;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
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

        TypedQuery<Sala> query =
                entityManager.createQuery(jpql.toString(), Sala.class);

        if (sala.getNome() != null && !sala.getNome().trim().isBlank()) {
            query.setParameter("nome", "%" + sala.getNome().toUpperCase() + "%");
        }

        return query.getResultList();
    }

    public List<Sala> listarTodos() {
        return entityManager
                .createQuery("SELECT s FROM Sala s ORDER BY s.nome", Sala.class)
                .getResultList();
    }



}
