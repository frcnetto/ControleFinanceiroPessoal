package br.estacio.poo.cfp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.estacio.poo.cfp.entidades.Cliente;
import br.estacio.poo.cfp.persistence.Conexao;
import br.estacio.poo.cfp.util.ClienteTableModel;

public class ClienteDao {
    Conexao conexao = new Conexao();
    
    public ClienteDao(){
    	
    }
    
    public void cadastraCliente(Cliente novo){
        conexao.persisteUm(novo);
    }
    
    @SuppressWarnings("unchecked")
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
    
    @SuppressWarnings("unchecked")
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
    
    @SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public void todosComNome(String nome, ClienteTableModel tabela) {
		conexao.criaConexao();
    	try{
    		nome = "%" + nome + "%";
	    	Query query = conexao.criaQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :nome");  	  
	    	query.setParameter("nome", nome);
			List<Cliente> clientes = query.getResultList();
	    	conexao.fechaConexao();
	    	for(Cliente c : clientes){
	    		tabela.addRow(c);
	    	}
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    	}
	}
	
	@SuppressWarnings("unchecked")
	public void todosComNome(String nome, String estado, ClienteTableModel tabela) {
		conexao.criaConexao();
    	try{
    		nome = "%" + nome + "%";
    		EstadoDao estadoDao = new EstadoDao();
    		int estadoId = estadoDao.carregaId(estado);
	    	Query query = conexao.criaQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :nome and c.uf = :uf");  	  
	    	query.setParameter("nome", nome);
	    	query.setParameter("uf", estadoId);
			List<Cliente> clientes = query.getResultList();
	    	conexao.fechaConexao();
	    	for(Cliente c : clientes){
	    		tabela.addRow(c);
	    	}
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    	}
	}

	@SuppressWarnings("unchecked")
	public void todosComNome(String nome, String estado, String cidade, ClienteTableModel tabela) {
		conexao.criaConexao();
    	try{
    		nome = "%" + nome + "%";
    		EstadoDao estadoDao = new EstadoDao();
    		int estadoId = estadoDao.carregaId(estado);
    		CidadeDao cidadeDao = new CidadeDao();
    		int cidadeId =cidadeDao.buscaId(cidade); 
	    	Query query = conexao.criaQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :nome AND c.uf = :uf AND c.cidade = :cidade");  	  
	    	query.setParameter("nome", nome);
	    	query.setParameter("uf", estadoId);
	    	query.setParameter("cidade", cidadeId);
			List<Cliente> clientes = query.getResultList();
	    	conexao.fechaConexao();
	    	for(Cliente c : clientes){
	    		tabela.addRow(c);
	    	}
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    	}
	}

	public Cliente buscaCliente(Cliente cliente) {
		conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT c FROM Cliente c WHERE c.id = :id");  	  
	    	query.setParameter("id", cliente.getCod());
	    	cliente = (Cliente) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return cliente;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		e.printStackTrace();
    		return null;
    	}
	}
}