package br.estacio.poo.cfp.entidades;

import java.util.Calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("pagamento")
public class Pagamento extends Conta{
	@ManyToOne
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

    public Calendar getDtPaamento() {
        return dtPagamento;
    }

    public void setDtPaamento(Calendar dtPaamento) {
        this.dtPagamento = dtPaamento;
    }
}
