package br.estacio.poo.cfp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.persistence.Conexao;
import br.estacio.poo.cfp.util.FornecedorTableModel;

public class FornecedorDao {
    Conexao conexao = new Conexao();
    
    public FornecedorDao(){
    	
    }
    
    /*Cadastro*/
    public void cadastraFornecedor(Fornecedor novo){
        conexao.persisteUm(novo);
    }
    
    /*Buscas*/
    public Fornecedor buscaFornecedor(Fornecedor fornecedor) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT f FROM Fornecedor f WHERE f.id = :id");  	  
	    	query.setParameter("id", fornecedor.getCod());
	    	fornecedor = (Fornecedor) query.getSingleResult();
	    	conexao.fechaConexao();
	    	return fornecedor;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		e.printStackTrace();
    		return null;
    	}
	}
    
    @SuppressWarnings("unchecked")
	public void todosComNome(String nome, String estado, FornecedorTableModel tabela) {
		conexao.criaConexao();
    	try{
    		nome = "%" + nome + "%";
    		EstadoDao estadoDao = new EstadoDao();
    		int estadoId = estadoDao.carregaId(estado);
	    	Query query = conexao.criaQuery("SELECT f FROM Fornecedor f WHERE f.nome LIKE :nome and f.uf = :uf");  	  
	    	query.setParameter("nome", nome);
	    	query.setParameter("uf", estadoId);
			List<Fornecedor> fornecedores = query.getResultList();
	    	conexao.fechaConexao();
	    	for(Fornecedor f : fornecedores){
	    		tabela.addRow(f);
	    	}
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    	}
	}

	@SuppressWarnings("unchecked")
	public void todosComNome(String nome, String estado, String cidade, FornecedorTableModel tabela) {
		conexao.criaConexao();
    	try{
    		nome = "%" + nome + "%";
    		EstadoDao estadoDao = new EstadoDao();
    		int estadoId = estadoDao.carregaId(estado);
    		CidadeDao cidadeDao = new CidadeDao();
    		int cidadeId =cidadeDao.buscaId(cidade); 
	    	Query query = conexao.criaQuery("SELECT f FROM Fornecedor f WHERE f.nome LIKE :nome AND f.uf = :uf AND f.cidade = :cidade");  	  
	    	query.setParameter("nome", nome);
	    	query.setParameter("uf", estadoId);
	    	query.setParameter("cidade", cidadeId);
			List<Fornecedor> fornecedores = query.getResultList();
	    	conexao.fechaConexao();
	    	for(Fornecedor f : fornecedores){
	    		tabela.addRow(f);
	    	}
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    	}
	}
    
    @SuppressWarnings("unchecked")
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
    
    /*Atualização*/
    public void atualizaFornecedor(Fornecedor fornecedor){
    	conexao.criaConexao();
    	Fornecedor vazio = new Fornecedor();
    	vazio.setCod(fornecedor.getCod());
    	vazio = (Fornecedor) conexao.atualizaVarios(vazio);
    	vazio = fornecedor;
    	conexao.fechaConexao();
    }
}