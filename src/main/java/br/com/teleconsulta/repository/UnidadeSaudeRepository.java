package br.com.teleconsulta.repository;

import br.com.teleconsulta.core.Repository;
import br.com.teleconsulta.model.Paciente;
import br.com.teleconsulta.model.UnidadeSaude;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

@Dependent
public class UnidadeSaudeRepository extends Repository<UnidadeSaude, Long> implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManager;

    public List<UnidadeSaude> pesquisarComFiltros(UnidadeSaude unidadeSaude) {

        StringBuilder jpql = new StringBuilder("SELECT u FROM UnidadeSaude u WHERE 1=1");

        if (unidadeSaude.getNome() != null && !unidadeSaude.getNome().trim().isBlank()) {
            jpql.append(" AND UPPER(u.nome) LIKE :nome");
        }

        TypedQuery<UnidadeSaude> query =
                entityManager.createQuery(jpql.toString(), UnidadeSaude.class);

        if (unidadeSaude.getNome() != null && !unidadeSaude.getNome().trim().isBlank()) {
            query.setParameter("nome", "%" + unidadeSaude.getNome().toUpperCase() + "%");
        }


        return query.getResultList();
    }

}
