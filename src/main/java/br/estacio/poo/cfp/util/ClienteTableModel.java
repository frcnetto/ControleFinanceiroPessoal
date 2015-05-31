package br.estacio.poo.cfp.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.estacio.poo.cfp.entidades.Cliente;

public class ClienteTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6436332118940791750L;
	private List<Cliente> linhas;
	private String[] colunas = {"Id", "Nome", "Endereço", "UF", "Cidade", "Telefone", "Celular", "Serviço", "Valor Mensal", "Contrato", "Obs."};
	
	public ClienteTableModel(){
		linhas = new ArrayList<Cliente>();
	}
	
	public void addRow(Cliente novo){
        this.linhas.add(novo);
        this.fireTableDataChanged();
    }
	
	public Cliente getLinha(int linha){
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
	        case 7: return linhas.get(linha).getServico();
	        case 8: return linhas.get(linha).getVlrMensal();
	        case 9: return linhas.get(linha).isContrato();
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
	        case 7: linhas.get(linha).setServico((String) valor);
	        case 8: linhas.get(linha).setVlrMensal((float) valor);
	        case 9: linhas.get(linha).setContrato((boolean) valor);
	        case 10: linhas.get(linha).setObs((String) valor);
		}
		this.fireTableRowsUpdated(linha, linha);
	}

	public void limpaModel(){
		if(!linhas.isEmpty()){
			linhas.clear();
		    this.fireTableRowsDeleted(0, linhas.size() - 1);
		}
	}
}
