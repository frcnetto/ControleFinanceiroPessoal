package br.estacio.poo.cfp.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.estacio.poo.cfp.entidades.Cliente;
import br.estacio.poo.cfp.persistence.Conexao;

public class ClienteDao {
    Conexao conexao = new Conexao();
    
    public ClienteDao(){
    	
    }
    
    public void cadastraCliente(Cliente novo){
        conexao.persiste(novo);
    }
    
    public Cliente buscaPeloNome(String nome) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT c FROM Cliente u WHERE c.nome = :nome");  	  
	    	query.setParameter("nome", nome);
	    	Cliente cliente = (Cliente) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return cliente;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
	}
}