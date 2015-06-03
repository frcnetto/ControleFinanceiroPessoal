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
import br.estacio.poo.cfp.util.PagamentoTableModel;

public class PagamentoDao {
    Conexao conexao = new Conexao();
    ParcelaDao parcelaDao = new ParcelaDao();
    
    public PagamentoDao(){
    	
    }
    
    public void cadastraPagamento(Pagamento pagamento, List<Parcela> parcelas){
    	try{
	    	conexao.criaConexao();
	    	conexao.persisteVarios(pagamento);
	    	if(pagamento.isParcela()){
				for (int i = 0; i < parcelas.size(); i++) {
		    		conexao.persisteVarios(parcelas.get(i));
				}
			} 
			conexao.fechaConexao();    	
	        JOptionPane.showMessageDialog(null, "Pagamento cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
    	} catch(PersistenceException e){
    		e.printStackTrace();
    	}
    }
    
    @SuppressWarnings("unchecked")
	public void todosFornecedor(Fornecedor fornecedor, PagamentoTableModel tabela) {
		conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT p FROM Pagamento p WHERE p.fornecedor = :fornecedor");  	  
	    	query.setParameter("fornecedor", fornecedor);
			List<Pagamento> pagamentos = query.getResultList();
	    	conexao.fechaConexao();
	    	for(Pagamento p : pagamentos){
	    		tabela.addRow(p);
	    	}
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    	}
	}
}