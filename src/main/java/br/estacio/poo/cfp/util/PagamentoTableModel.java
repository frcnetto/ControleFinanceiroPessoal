package br.estacio.poo.cfp.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.entidades.Pagamento;

public class PagamentoTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6436332118940791750L;
	private List<Pagamento> linhas;
	private String[] colunas = {"Id",       "Descriçao",         "Emissao", "Parcela", 
								"Situaçao", "Total de Parcelas", "Valor",   "Vencimento", 
								"Fornecedor", "Data de Pagamento"};
	
	public PagamentoTableModel(){
		linhas = new ArrayList<Pagamento>();
	}
	
	public void addRow(Pagamento novo){
        this.linhas.add(novo);
        this.fireTableDataChanged();
    }
	
	public Pagamento getLinha(int linha){
		return linhas.get(linha);
	}
	
	public void removeRow(int linha){
	    this.linhas.remove(linha);
	    this.fireTableRowsDeleted(linha, linha);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}
	
	@Override
	public String getColumnName(int coluna){
		return colunas[coluna];		
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna){
	        case 0: return linhas.get(linha).getCod();
	        case 1: return linhas.get(linha).getDesc();
	        case 2: return linhas.get(linha).getEmissao();
	        case 3: return linhas.get(linha).isParcela();
	        case 4: return linhas.get(linha).getSituacao();
	        case 5: return linhas.get(linha).getTotParcela();
	        case 6: return linhas.get(linha).getValor();
	        case 7: return linhas.get(linha).getVencimento();
	        case 8: return linhas.get(linha).getFornecedor();
	        case 9: return linhas.get(linha).getDtPagamento();
		}
		return null;
	}
	
	public void setValueAt(Object valor, int linha, int coluna) {
		switch(coluna){
		case 0: break;
        case 1: linhas.get(linha).setDesc((String) valor);
        case 2: linhas.get(linha).setEmissao((Calendar) valor);
        case 3: linhas.get(linha).setParcela((boolean) valor);
        case 4: linhas.get(linha).setSituacao((String) valor);
        case 5: linhas.get(linha).setTotParcela((int) valor);
        case 6: linhas.get(linha).setValor((double) valor);
        case 7: linhas.get(linha).setVencimento((Calendar) valor);
        case 8: linhas.get(linha).setFornecedor((Fornecedor) valor);
        case 9: linhas.get(linha).setDtPagamento((Calendar) valor);
		}
		this.fireTableRowsUpdated(linha, linha);
	}

	public void limpaModel(){
		if(!linhas.isEmpty()){
			linhas.clear();
			fireTableRowsDeleted(0, 0);
		}
	}
}
