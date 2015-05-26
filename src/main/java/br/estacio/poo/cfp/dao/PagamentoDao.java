package br.estacio.poo.cfp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.entidades.Pagamento;
import br.estacio.poo.cfp.persistence.Conexao;

public class PagamentoDao {
    Conexao conexao = new Conexao();
    
    public PagamentoDao(){
    	
    }
    
    public void cadastraPagamento(Pagamento novo){
    	conexao.persisteUm(novo);
        JOptionPane.showMessageDialog(null, "Pagamento cadastrado com sucesso!");        
    }
    
    public List<Pagamento> buscaPeloFornecedor(Fornecedor fornecedor) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT p FROM Pagamento p WHERE p.fornecedor_id = :fornecedor_cod");  	  
	    	query.setParameter("fornecedor_cod", fornecedor.getCod());
	    	List<Pagamento> pagamento = query.getResultList();
	    	conexao.fechaConexao();
	    	return pagamento;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
	}
}