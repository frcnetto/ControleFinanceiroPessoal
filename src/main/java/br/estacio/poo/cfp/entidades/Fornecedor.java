package br.estacio.poo.cfp.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("fornecedor")
public class Fornecedor extends Pessoa{
    private String tipo;    
    private float valorl;
    private String descricao;
    @OneToMany(mappedBy = "fornecedor")
    private List<Pagamento> pagamentos;
    
    public Fornecedor(){
    	super();
		this.tipo = "";
		this.valorl = 0;
		this.descricao = "";
		this.pagamentos = new ArrayList<Pagamento>();
    }

	public Fornecedor(String tipo, float valorl, String descricao,
			List<Pagamento> pagamentos) {
		super();
		this.tipo = tipo;
		this.valorl = valorl;
		this.descricao = descricao;
		this.pagamentos = pagamentos;
	}

	public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return descricao;
    }

    public void setDesc(String desc) {
        this.descricao = desc;
    }

    public float getValorl() {
        return valorl;
    }

    public void setValorl(float valorl) {
        this.valorl = valorl;
    }
}