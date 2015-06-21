package br.estacio.poo.cfp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.estacio.poo.cfp.persistence.Conexao;

public class EstadoDao {
Conexao conexao = new Conexao();
    
    public EstadoDao(){
    	
    }
    
    @SuppressWarnings("unchecked")
	public List<String> carregaUf() {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT nome FROM Estado e");	    	
	    	List<String> estados = query.getResultList();
	    	conexao.fechaConexao();
	    	return estados;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
    }
    
    public int carregaId(String estado) {
    	conexao.criaConexao();
    	int ret;
    	try{
	    	Query query = conexao.criaQuery("SELECT id FROM Estado WHERE nome = :estado");
	    	query.setParameter("estado", estado);
	    	ret = (int)query.getSingleResult();
	    	conexao.fechaConexao();
	    	return ret;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return -1;
    	}
    }
    
    public String carregaId(int id) {
    	conexao.criaConexao();
    	String ret;
    	try{
	    	Query query = conexao.criaQuery("SELECT nome FROM Estado WHERE id = :id");
	    	query.setParameter("id", id);
	    	ret = (String) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return ret;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return "";
    	}
    }
}
