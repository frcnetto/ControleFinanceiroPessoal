package br.estacio.poo.cfp.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cliente")
public class Cliente extends Pessoa{
	private boolean contrato;
    private String servico;
    private float vlrMensal;
    
    public Cliente(){
    
    }

	public boolean isContrato() {
        return contrato;
    }

    public void setContrato(boolean contrato) {
        this.contrato = contrato;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public float getVlrMensal() {
        return vlrMensal;
    }

    public void setVlrMensal(float vlrMensal) {
        this.vlrMensal = vlrMensal;
    }    
}
