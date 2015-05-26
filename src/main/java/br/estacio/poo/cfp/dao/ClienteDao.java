package br.estacio.poo.cfp.dao;

import java.util.List;

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
    
    public List<Cliente> buscaPeloNome(String nome) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT c FROM Pessoa u WHERE c.nome LIKE :nome and id in(SELECT id FROM Cliente)");  	  
	    	query.setParameter("nome", nome);
	    	List<Cliente> cliente = (List<Cliente>) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return cliente;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
	}
    
    public List<Cliente> buscaPeloEstado(int uf) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT c FROM Cliente c WHERE c.uf = :uf");  	  
	    	query.setParameter("uf", uf);
	    	List<Cliente> cliente = (List<Cliente>) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return cliente;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
	}
    
    public List<Cliente> buscaPelaCidade(int cidade) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT c FROM Cliente c WHERE c.cidade = :cidade");  	  
	    	query.setParameter("cidade", cidade);
	    	List<Cliente> cliente = (List<Cliente>) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return cliente;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
	}
    
    public List<Cliente> buscaPeloContrato(boolean contrato) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT c FROM Cliente c WHERE c.contrato = :contrato");  	  
	    	query.setParameter("contrato", contrato);
	    	List<Cliente> cliente = (List<Cliente>) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return cliente;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
	}
}