package br.estacio.poo.cfp.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.estacio.poo.cfp.entidades.Cliente;
import br.estacio.poo.cfp.entidades.Recebimento;

public class RecebimentoTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6436332118940791750L;
	private List<Recebimento> linhas;
	private String[] colunas = {"Id",       "Descriçao",         "Emissao", "Parcela", 
								"Situaçao", "Total de Parcelas", "Valor",   "Vencimento", 
								"Cliente", "Data de Recebimento"};
	
	public RecebimentoTableModel(){
		linhas = new ArrayList<Recebimento>();
	}
	
	public void addRow(Recebimento recebimento){
        this.linhas.add(recebimento);
        this.fireTableDataChanged();
    }
	
	public Recebimento getLinha(int linha){
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
	
	public int getUltimaLinha(){
		if(getRowCount() <= 0){
			return 0;
		} else{
			return getRowCount() - 1;
		} 
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
	        case 8: return linhas.get(linha).getCliente();
	        case 9: return linhas.get(linha).getDtRecebimento();
		}
		return null;
	}
	
	public void setValueAt(Object valor, int linha, int coluna) {
		switch(coluna){
		case 0: linhas.get(linha).setCod((int) valor);
        case 1: linhas.get(linha).setDesc((String) valor);
        case 2: linhas.get(linha).setEmissao((Calendar) valor);
        case 3: linhas.get(linha).setParcela((boolean) valor);
        case 4: linhas.get(linha).setSituacao((String) valor);
        case 5: linhas.get(linha).setTotParcela((int) valor);
        case 6: linhas.get(linha).setValor((double) valor);
        case 7: linhas.get(linha).setVencimento((Calendar) valor);
        case 8: linhas.get(linha).setCliente((Cliente) valor);
        case 9: linhas.get(linha).setDtRecebimento((Calendar) valor);
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
