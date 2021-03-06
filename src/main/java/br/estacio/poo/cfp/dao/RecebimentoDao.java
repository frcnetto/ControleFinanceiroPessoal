package br.estacio.poo.cfp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import br.estacio.poo.cfp.entidades.Cliente;
import br.estacio.poo.cfp.entidades.Parcela;
import br.estacio.poo.cfp.entidades.Recebimento;
import br.estacio.poo.cfp.persistence.Conexao;
import br.estacio.poo.cfp.util.RecebimentoTableModel;

public class RecebimentoDao {
    Conexao conexao = new Conexao();
    
    public RecebimentoDao(){
    	
    }
    
    public void cadastraRecebimento(Recebimento novo){
        conexao.persisteUm(novo);
    }
    
    @SuppressWarnings("unchecked")
	public void todosCliente(Cliente cliente, RecebimentoTableModel tabela) {
		conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT r FROM Recebimento r WHERE r.cliente = :cliente");  	  
	    	query.setParameter("cliente", cliente);
			List<Recebimento> recebimentos = query.getResultList();
	    	conexao.fechaConexao();
	    	for(Recebimento r : recebimentos){
	    		tabela.addRow(r);
	    	}
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    	}
	}

	public void cadastraRecebimento(Recebimento recebimento, ArrayList<Parcela> parcelas) {
		try{
	    	conexao.criaConexao();
	    	conexao.persisteVarios(recebimento);
	    	if(recebimento.isParcela()){
				for (int i = 0; i < parcelas.size(); i++) {
		    		conexao.persisteVarios(parcelas.get(i));
				}
			} 
			conexao.fechaConexao();    	
	        JOptionPane.showMessageDialog(null, "Recebimento cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
    	} catch(PersistenceException e){
			e.printStackTrace();
    	}
	}
}