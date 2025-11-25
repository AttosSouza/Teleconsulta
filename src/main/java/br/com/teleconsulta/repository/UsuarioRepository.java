package br.com.teleconsulta.repository;

import br.com.teleconsulta.core.Repository;
import br.com.teleconsulta.model.Usuario;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

@Dependent
public class UsuarioRepository extends Repository<Usuario, Long> implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManager;

    public List<Usuario> pesquisarComFiltros(Usuario usuario) {

        StringBuilder jpql = new StringBuilder("SELECT u FROM Usuario u WHERE 1=1");

        if (usuario.getNome() != null && !usuario.getNome().trim().isBlank()) {
            jpql.append(" AND UPPER(u.nome) LIKE :nome");
        }

        if (usuario.getCpf() != null && !usuario.getCpf().trim().isBlank()) {
            jpql.append(" AND u.cpf = :cpf");
        }


        TypedQuery<Usuario> query =
                entityManager.createQuery(jpql.toString(), Usuario.class);

        if (usuario.getNome() != null && !usuario.getNome().trim().isBlank()) {
            query.setParameter("nome", "%" + usuario.getNome().toUpperCase() + "%");
        }

        if (usuario.getCpf() != null && !usuario.getCpf().trim().isBlank()) {
            query.setParameter("cpf", usuario.getCpf());
        }

        return query.getResultList();
    }

    public List<Usuario> listarTodos() {
        return entityManager
                .createQuery("SELECT u FROM Usuario u ORDER BY u.nome", Usuario.class)
                .getResultList();
    }

}
