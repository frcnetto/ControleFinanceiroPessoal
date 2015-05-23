package br.estacio.poo.cfp.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario{
    @Id
    @GeneratedValue
    @Column(nullable = false)    
    private int id;
    @Column(nullable = true)
    private String usuario;
    @Column(nullable = true)
    private String senha;

    public Usuario() {
    }

    public Usuario( String user, String senha){
        this.usuario = user;
        this.senha = senha;
    }
    
    public String getUser() {
        return usuario;
    }

    public void setUser(String user) {
        this.usuario = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }
}
