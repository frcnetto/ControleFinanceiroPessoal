package br.estacio.poo.cfp.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.estacio.poo.cfp.entidades.Fornecedor;

public class FornecedorTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6436332118940791750L;
	private List<Fornecedor> linhas;
	private String[] colunas = {"Id", "Nome", "Endere�o", "UF", "Cidade", "Telefone", "Celular", "Tipo", "Descri��o", "Valor", "Obs."};
	
	public FornecedorTableModel(){
		linhas = new ArrayList<Fornecedor>();
	}
	
	public void addRow(Fornecedor novo){
        this.linhas.add(novo);
        this.fireTableDataChanged();
    }
	
	public Fornecedor getLinha(int linha){
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
	        case 1: return linhas.get(linha).getNome();
	        case 2: return linhas.get(linha).getEndereco();
	        case 3: return linhas.get(linha).getUf();
	        case 4: return linhas.get(linha).getCidade();
	        case 5: return linhas.get(linha).getTelefone();
	        case 6: return linhas.get(linha).getCelular();
	        case 7: return linhas.get(linha).getTipo();
	        case 8: return linhas.get(linha).getDesc();
	        case 9: return linhas.get(linha).getValorl();
	        case 10: return linhas.get(linha).getObs();
		}
		return null;
	}
	
	public void setValueAt(Object valor, int linha, int coluna) {
		switch(coluna){
	        case 0: break;
	        case 1: linhas.get(linha).setNome((String) valor);
	        case 2: linhas.get(linha).setEndereco((String) valor);
	        case 3: linhas.get(linha).setUf((int) valor);
	        case 4: linhas.get(linha).setCidade((int) valor);
	        case 5: linhas.get(linha).setTelefone((String) valor);
	        case 6: linhas.get(linha).setCelular((String) valor);
	        case 7: linhas.get(linha).setTipo((String) valor);
	        case 8: linhas.get(linha).setDesc((String) valor);
	        case 9: linhas.get(linha).setValorl((float) valor);
	        case 10: linhas.get(linha).setObs((String) valor);
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
