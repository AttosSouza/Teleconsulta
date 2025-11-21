package br.com.teleconsulta;

import jakarta.inject.Named;

@Named("navigationBean")
public class NavigationBean {

    public String irParaCadastro() {
        return "irParaCadastro";
    }
}
