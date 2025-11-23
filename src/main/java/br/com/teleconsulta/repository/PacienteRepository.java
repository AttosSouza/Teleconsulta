package br.com.teleconsulta.repository;

import br.com.teleconsulta.core.Repository;
import br.com.teleconsulta.model.Paciente;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

@Dependent
public class PacienteRepository extends Repository<Paciente, Long> implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManager;

//    public Paciente buscarPorId(Long id) {
//        return entityManager.find(Paciente.class, id);
//    }

//    public List<Paciente> listarTodos() {
//        String jpql = "SELECT * FROM Paciente" ;
//
//        TypedQuery<Paciente> query = entityManager
//                .createQuery(jpql, Paciente.class);
//
//        return query.getResultList();
//    }

    public List<Paciente> pesquisarComFiltros(Paciente paciente) {

        StringBuilder jpql = new StringBuilder("SELECT p FROM Paciente p WHERE 1=1");

        if (paciente.getNome() != null && !paciente.getNome().trim().isBlank()) {
            jpql.append(" AND UPPER(p.nome) LIKE :nome");
        }

        if (paciente.getNomeSocial() != null && !paciente.getNomeSocial().trim().isBlank()) {
            jpql.append(" AND UPPER(p.nomeSocial) LIKE :nome_social");
        }

        if (paciente.getCpf() != null && !paciente.getCpf().trim().isBlank()) {
            jpql.append(" AND p.cpf = :cpf");
        }

        if (paciente.getRg() != null && !paciente.getRg().trim().isBlank()) {
            jpql.append(" AND UPPER(p.rg) = :rg");
        }

        if (paciente.getCns() != null && !paciente.getCns().trim().isBlank()) {
            jpql.append(" AND UPPER(p.cns) = :cns");
        }

        TypedQuery<Paciente> query =
                entityManager.createQuery(jpql.toString(), Paciente.class);

        if (paciente.getNome() != null && !paciente.getNome().trim().isBlank()) {
            query.setParameter("nome", "%" + paciente.getNome().toUpperCase() + "%");
        }

        if (paciente.getNomeSocial() != null && !paciente.getNomeSocial().trim().isBlank()) {
            query.setParameter("nome_social", "%" + paciente.getNomeSocial().toUpperCase() + "%");
        }

        if (paciente.getCpf() != null && !paciente.getCpf().trim().isBlank()) {
            query.setParameter("cpf", paciente.getCpf());
        }

        if (paciente.getRg() != null && !paciente.getRg().trim().isBlank()) {
            query.setParameter("rg", paciente.getRg().toUpperCase());
        }

        if (paciente.getCns() != null && !paciente.getCns().trim().isBlank()) {
            query.setParameter("cns", paciente.getCns().toUpperCase());
        }

        return query.getResultList();
    }


//    public void salvar(Paciente paciente) {
//        try {
//            entityManager.getTransaction().begin();
//
//            if (paciente.getId() == null) {
//                entityManager.persist(paciente);
//            } else {
//                entityManager.merge(paciente);
//            }
//
//            entityManager.getTransaction().commit();
//        }catch (Exception e) {
//            if(entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            throw e;
//        } finally {
//            if(entityManager.isOpen()) {
//                entityManager.close();
//            }
//        }
//    }

//    public void remover(Paciente paciente) {
//        try {
//            entityManager.getTransaction().begin();
//
//            Paciente p = entityManager.find(Paciente.class, paciente.getId());
//
//            if(p != null) {
//                entityManager.remove(p);
//            }
//
//            entityManager.getTransaction().commit();
//        }catch (Exception e) {
//            if(entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            throw e;
//        } finally {
//            if(entityManager.isOpen()) {
//                entityManager.close();
//            }
//        }
//    }



}
