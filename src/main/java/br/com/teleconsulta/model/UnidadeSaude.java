package br.com.teleconsulta.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "unidade_saude")
public class UnidadeSaude implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnpj", length = 18, nullable = false, unique = true)
    private String cnpj;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "razao_social", length = 150)
    private String razaoSocial;

    @Column(name = "sigla", length = 10)
    private String sigla;

    @Column(name = "cnes", length = 15)
    private String cnes;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnidadeSaude)) return false;
        UnidadeSaude that = (UnidadeSaude) o;
        return Objects.equals(id, that.id); // entidade identificada pelo ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
