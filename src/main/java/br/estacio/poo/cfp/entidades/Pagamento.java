package br.estacio.poo.cfp.entidades;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("pagamento")
public class Pagamento extends Conta{
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;
    private Calendar dtPagamento;
    
    public Pagamento(){    	
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Calendar getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Calendar dtPaamento) {
        this.dtPagamento = dtPaamento;
    }
}
