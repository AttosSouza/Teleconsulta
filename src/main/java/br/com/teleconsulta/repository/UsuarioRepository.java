package br.com.teleconsulta.repository;

import br.com.teleconsulta.core.Repository;
import br.com.teleconsulta.model.Usuario;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.io.Serializable;
import java.util.List;

@Dependent
public class UsuarioRepository extends Repository<Usuario, Long> implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManager;

    public List<Usuario> pesquisarComFiltros(Usuario filtro) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> root = cq.from(Usuario.class);

        List<Predicate> predicates = new java.util.ArrayList<>();

        if (filtro.getNome() != null && !filtro.getNome().trim().isBlank()) {
            predicates.add(
                    cb.like(
                            cb.upper(root.get("nome")),
                            "%" + filtro.getNome().trim().toUpperCase() + "%"
                    )
            );
        }

        if (filtro.getCpf() != null && !filtro.getCpf().trim().isBlank()) {
            predicates.add(
                    cb.equal(
                            root.get("cpf"),
                            filtro.getCpf().trim()
                    )
            );
        }

        cq.where(predicates.toArray(new Predicate[0]));

        cq.orderBy(cb.asc(root.get("nome")));

        TypedQuery<Usuario> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Usuario> listarTodos() {
        return entityManager
                .createQuery("SELECT u FROM Usuario u ORDER BY u.nome", Usuario.class)
                .getResultList();
    }

}
