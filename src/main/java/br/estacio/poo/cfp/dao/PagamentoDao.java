package br.estacio.poo.cfp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.entidades.Pagamento;
import br.estacio.poo.cfp.entidades.Parcela;
import br.estacio.poo.cfp.persistence.Conexao;

public class PagamentoDao {
    Conexao conexao = new Conexao();
    ParcelaDao parcelaDao = new ParcelaDao();
    
    public PagamentoDao(){
    	
    }
    
    public void cadastraPagamento(Pagamento pagamento, List<Parcela> parcelas){
    	try{
	    	conexao.criaConexao();
	    	conexao.persisteVarios(pagamento);
			for (int i = 0; i < parcelas.size(); i++) {
	    		conexao.persisteVarios(parcelas.get(i));
			}
			conexao.fechaConexao();    	
	        JOptionPane.showMessageDialog(null, "Pagamento cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
    	} catch(PersistenceException e){
//    		JOptionPane.showMessageDialog(
//    				null, 
//    				"Não foi possível cadastrar o pagamento devido a algum erro interno!\n"
//    				+ "Detalhes:\n" 
//					+ 
    				e.printStackTrace();
//    				"Erro", 
//    				JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    @SuppressWarnings("unchecked")
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