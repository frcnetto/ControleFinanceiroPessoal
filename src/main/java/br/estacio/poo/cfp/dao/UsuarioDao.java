package br.estacio.poo.cfp.dao;

import br.estacio.poo.cfp.entidades.Usuario;
import br.estacio.poo.cfp.persistence.Conexao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UsuarioDao {
    Conexao conexao = new Conexao();
    
    public UsuarioDao(){
    	
    }
    
    public void cadastraUsuario(Usuario usuario){
        conexao.persiste(usuario);
    }

    public boolean validaLoginSenha(String login, String senha) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT u FROM Usuario u WHERE u.usuario = :login AND u.senha = :senha");  	  
	    	query.setParameter("login", login);
	    	query.setParameter("senha", senha);
	    	Usuario user = (Usuario) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return (user != null);
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return false;
    	}
	}
    
    public boolean validaSenha(String senha) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT senha FROM Usuario WHERE senha = :senha");
	    	query.setParameter("senha", senha);
	    	Object retorno = query.getSingleResult();
	    	conexao.fechaConexao();
	    	return (retorno != null);
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return false;
    	}
    }
    
    public void atualizaSenha(String nova, String antiga){
    	conexao.criaConexao();
    	try{
			Query query = conexao.criaQuery("UPDATE Usuario SET senha = :nova WHERE senha = :antiga");
			query.setParameter("nova", nova);
			query.setParameter("antiga", antiga);
			query.executeUpdate();
			conexao.fechaConexao();
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		e.printStackTrace();
    	}
    }
}