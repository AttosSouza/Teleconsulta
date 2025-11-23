package br.com.teleconsulta.core;

import br.com.teleconsulta.model.Paciente;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jogamp.graph.font.typecast.ot.table.ID;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class Repository<E, ID> implements Serializable {

    @Inject
    @Named("emPostgres")
    private EntityManager entityManager;

    private final Class<E> entityClass;

    public Repository () {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public E buscarPorId(Long id) {
        return entityManager.find(this.entityClass, id);
    }

    public List<E> listarTodos() {
        String jpql = "SELECT * FROM Paciente" ;

        TypedQuery<E> query = entityManager
                .createQuery(jpql, this.entityClass);

        return query.getResultList();
    }


    public void salvar(E entity) {
        try {
            entityManager.getTransaction().begin();

            entityManager.merge(entity);

            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if(entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if(entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public void remover(ID id) {
        try {
            entityManager.getTransaction().begin();

            E p = entityManager.find(this.entityClass, id);

            if(p != null) {
                entityManager.remove(p);
            }

            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if(entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            if(entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
