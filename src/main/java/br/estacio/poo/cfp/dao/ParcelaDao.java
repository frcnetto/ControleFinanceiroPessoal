package br.estacio.poo.cfp.dao;

import java.util.List;

import br.estacio.poo.cfp.entidades.Parcela;
import br.estacio.poo.cfp.persistence.Conexao;

public class ParcelaDao {
	Conexao conexao = new Conexao();
    
    public ParcelaDao() {
	}

	public void cadastraParcela(Parcela novo){
    	conexao.persisteUm(novo);
    }
	
	public void cadastraParcelas(List<Parcela> novo){
    	for (int i = 0; i < novo.size(); i++) {
    		conexao.persisteVarios(novo.get(i));
		}
    }
}