package br.estacio.poo.cfp.entidades;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_conta")
public class Conta{
    @Id
    @GeneratedValue
    @Column(nullable = false)
    
    private int cod;
    
    @Column(nullable = true)
    private String descricao;
    private double valor;
    
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Calendar emissao;    
   // @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar vencimento;    
    private boolean parcela;
    private int totParcela = 0;
    private String situacao;
    
    @OneToMany(mappedBy = "conta", targetEntity = Parcela.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Parcela> parcelas;
    
    public Conta(){
    	super();
		this.descricao = "";
		this.valor = 0;
		this.emissao = Calendar.getInstance();
		this.vencimento = Calendar.getInstance();
		this.parcela = false;
		this.totParcela = 0;
		this.situacao = "";
    }

    public Conta(String descricao, double valor, Calendar emissao,
			Calendar vencimento, boolean parcela, int totParcela,
			String situacao) {
		super();
		this.descricao = descricao;
		this.valor = valor;
		this.emissao = emissao;
		this.vencimento = vencimento;
		this.parcela = parcela;
		this.totParcela = totParcela;
		this.situacao = situacao;
	}

	public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDesc() {
        return descricao;
    }

    public void setDesc(String desc) {
        this.descricao = desc;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Calendar getEmissao() {
        return emissao;
    }

    public void setEmissao(Calendar emissao) {
        this.emissao = emissao;
    }

    public Calendar getVencimento() {
        return vencimento;
    }

    public void setVencimento(Calendar vencimento) {
        this.vencimento = vencimento;
    }

    public boolean isParcela() {
        return parcela;
    }

    public void setParcela(boolean parcela) {
        this.parcela = parcela;
    }

    public int getTotParcela() {
        return totParcela;
    }

    public void setTotParcela(int totParcela) {
        this.totParcela = totParcela;
    }

//    public List<Parcela> getNumParcela() {
//        return parcelas;
//    }
//
//    public void setNumParcela(List<Parcela> numParcela) {
//        this.parcelas = numParcela;
//    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }     

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}
}