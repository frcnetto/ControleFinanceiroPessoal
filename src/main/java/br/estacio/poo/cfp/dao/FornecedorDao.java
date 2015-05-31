package br.estacio.poo.cfp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.persistence.Conexao;
import br.estacio.poo.cfp.util.FornecedorTableModel;

public class FornecedorDao {
    Conexao conexao = new Conexao();
    
    public FornecedorDao(){
    	
    }
    
    public void cadastraFornecedor(Fornecedor novo){
        conexao.persisteUm(novo);
    }
    
    public Fornecedor primeiroComNome(String nome) {
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
    
    public void todosComNome(String nome, FornecedorTableModel tabela) {
    	conexao.criaConexao();
    	try{
    		nome = "%" + nome + "%";
	    	Query query = conexao.criaQuery("SELECT f FROM Fornecedor f WHERE f.nome LIKE :nome");  	  
	    	query.setParameter("nome", nome);
			List<Fornecedor> fornecedores = query.getResultList();
	    	conexao.fechaConexao();
	    	for(Fornecedor f : fornecedores){
	    		tabela.addRow(f);
	    	}
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    	}
	}
}