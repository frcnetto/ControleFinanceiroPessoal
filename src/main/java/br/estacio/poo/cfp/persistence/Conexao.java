package br.estacio.poo.cfp.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

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
        manager.getTransaction().begin();
    }
    
    public void fechaConexao(){
        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
    
    public void persisteUm(Object objeto){
    	criaConexao();
    	manager.persist(objeto);
    	fechaConexao();
    }
    
    public void persisteVarios(Object objeto){
    	manager.persist(objeto);
    }
    
    public void atualizaUm(Object objeto){
    	criaConexao();
    	final Session session = null;
    	session.update(objeto);
    	fechaConexao();
    }
    
    @SuppressWarnings("unused")
    public void atualizaVarios(Object objeto){
    	criaConexao();    	
		Object obj = (Object)manager.merge(objeto);
    	obj = objeto;
    	fechaConexao();
    }
    
    public Query criaQuery(String query){
    	return manager.createQuery(query);
    }
}
