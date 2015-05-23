package br.estacio.poo.cfp.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Conexao {
	private EntityManagerFactory factory;
    private EntityManager manager;
    
    public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public Conexao(){
    	
    }
    
    public void criaConexao(){
        factory = Persistence.createEntityManagerFactory("ControleFinanceiroPessoal");
        manager = factory.createEntityManager();
        manager.clear();
        manager.getTransaction().begin();
    }
    
    public void fechaConexao(){
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
    
    public void persiste(Object objeto){
    	criaConexao();
    	manager.persist(objeto);
    	fechaConexao();
    }
    
    public Query criaQuery(String query){
    	return manager.createQuery(query);
    }
}
