package br.estacio.poo.cfp.entidades;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Parcela {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pagamento_id")
	private Pagamento pagamento;
	private int numero;
	private Calendar vencimento;
	private double valor;
	private boolean pago;
	
	public Parcela(){}
	
	public Parcela(Pagamento pagamento, int numero, Calendar vencimento, double valor,
			boolean pago) {
		super();
		this.pagamento = pagamento;
		this.numero = numero;
		this.vencimento = vencimento;
		this.valor = valor;
		this.pago = pago;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Calendar getVencimento() {
		return vencimento;
	}
	public void setVencimento(Calendar vencimento) {
		this.vencimento = vencimento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
}
