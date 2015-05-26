package br.estacio.poo.cfp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.estacio.poo.cfp.entidades.Cliente;
import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.entidades.Pagamento;
import br.estacio.poo.cfp.entidades.Recebimento;
import br.estacio.poo.cfp.persistence.Conexao;

public class RecebimentoDao {
    Conexao conexao = new Conexao();
    
    public RecebimentoDao(){
    	
    }
    
    public void cadastraRecebimento(Recebimento novo){
        conexao.persisteUm(novo);
    }
    
    public List<Recebimento> buscaPeloCliente(Cliente cliente) {
    	conexao.criaConexao();
    	try{
	    	Query query = conexao.criaQuery("SELECT r FROM Recebimento r WHERE r.cliente_id = :cliente_cod");
	    	query.setParameter("cliente_cod", cliente.getCod());
	    	List<Recebimento> pagamento = query.getResultList();
	    	conexao.fechaConexao();
	    	return pagamento;
    	}catch(NoResultException e){
    		conexao.fechaConexao();
    		return null;
    	}
	}
}