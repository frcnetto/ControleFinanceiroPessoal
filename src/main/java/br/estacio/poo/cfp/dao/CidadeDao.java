package br.estacio.poo.cfp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.estacio.poo.cfp.persistence.Conexao;

public class CidadeDao {
	Conexao conexao = new Conexao();
	
	public CidadeDao(){}
	
	public List<String> carregaCidade() {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT nome FROM Cidade c");
	    	List<String> cidades = query.getResultList();
	    	conexao.fechaConexao();
	    	return cidades;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
    }
	
	public List<String> carregaCidade(int id) {
    	conexao.criaConexao();
    	if(id >= 0){
	    	try{
		    	Query query = conexao.criaQuery("SELECT nome FROM Cidade WHERE estado = :id");
		    	query.setParameter("id", id);
		    	List<String> estados = query.getResultList();
		    	conexao.fechaConexao();
		    	return estados;
	    	}catch(NoResultException e){
	    		conexao.fechaConexao();
	    		return null;
	    	}
    	} else{
    		return null;
    	}
    }
	
	public int carregaId(String cidade){
		conexao.criaConexao();
    	int ret;
    	try{
	    	Query query = conexao.criaQuery("SELECT id FROM Cidade WHERE nome = :cidade");
	    	query.setParameter("cidade", cidade);
	    	ret = (int)query.getSingleResult();
	    	conexao.fechaConexao();
	    	return ret;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return -1;
    	}
	}
}
