package br.estacio.poo.cfp.entidades;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("recebimento")
public class Recebimento extends Conta{
	@ManyToOne	
    private Cliente cliente;
    private Calendar dtRecebimento;
    
    public Recebimento(){
    
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Calendar getDtRecebimento() {
        return dtRecebimento;
    }

    public void setDtRecebimento(Calendar dtRecebimento) {
        this.dtRecebimento = dtRecebimento;
    }
}
