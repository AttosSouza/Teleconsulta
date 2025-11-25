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

    public List<Paciente> pesquisarComFiltros(Paciente paciente) {

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Paciente.class);
        var root = cq.from(Paciente.class);

        List<jakarta.persistence.criteria.Predicate> predicates = new java.util.ArrayList<>();

        if (paciente.getNome() != null && !paciente.getNome().trim().isBlank()) {
            predicates.add(
                    cb.like(
                            cb.upper(root.get("nome")),
                            "%" + paciente.getNome().toUpperCase() + "%"
                    )
            );
        }

        if (paciente.getNomeSocial() != null && !paciente.getNomeSocial().trim().isBlank()) {
            predicates.add(
                    cb.like(
                            cb.upper(root.get("nomeSocial")),
                            "%" + paciente.getNomeSocial().toUpperCase() + "%"
                    )
            );
        }

        if (paciente.getCpf() != null && !paciente.getCpf().trim().isBlank()) {
            predicates.add(
                    cb.equal(root.get("cpf"), paciente.getCpf())
            );
        }

        if (paciente.getRg() != null && !paciente.getRg().trim().isBlank()) {
            predicates.add(
                    cb.equal(cb.upper(root.get("rg")), paciente.getRg().toUpperCase())
            );
        }

        if (paciente.getCns() != null && !paciente.getCns().trim().isBlank()) {
            predicates.add(
                    cb.equal(cb.upper(root.get("cns")), paciente.getCns().toUpperCase())
            );
        }

        cq.where(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

}
