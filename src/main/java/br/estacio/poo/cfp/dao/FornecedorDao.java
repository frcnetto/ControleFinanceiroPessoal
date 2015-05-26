package br.estacio.poo.cfp.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.persistence.Conexao;

public class FornecedorDao {
    Conexao conexao = new Conexao();
    
    public FornecedorDao(){
    	
    }
    
    public void cadastraFornecedor(Fornecedor novo){
        conexao.persisteUm(novo);
    }
    
    public Fornecedor buscaPeloNome(String nome) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT f FROM Fornecedor f WHERE f.nome = :nome");  	  
	    	query.setParameter("nome", nome);
	    	Fornecedor fornecedor = (Fornecedor) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return fornecedor;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
	}
}