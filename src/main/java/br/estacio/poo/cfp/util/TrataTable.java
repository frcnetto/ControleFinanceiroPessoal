package br.estacio.poo.cfp.util;

import javax.swing.table.DefaultTableModel;

public class TrataTable {
	
	public TrataTable(){}
	
	public void validaAdicao(DefaultTableModel modelo, int adicao){
		if(modelo.getRowCount() == 0){
			for(int i = 0; i < adicao; i++){
				String linha[] = {String.valueOf(i + 1), "", ""};
				modelo.addRow(linha);
			}				
		}else {
			if(modelo.getRowCount() < adicao){
				for(int i = modelo.getRowCount(); i < adicao; i++){						
					String linha[] = {String.valueOf(i + 1), "", ""};
					modelo.addRow(linha);
				}					
			} else{
				if(modelo.getRowCount() > adicao){
					for(int i = modelo.getRowCount() - 1; i > adicao - 1; i--){
						modelo.removeRow(i);
					}
				}
			}
		}
	}
	
	public void validaAdicao(DefaultTableModel destino, DefaultTableModel origem, int[] adicao){
		if(destino.getRowCount() == 0){
			for(int i = 0; i < adicao.length; i++){
				destino.addRow(new String[]{
						String.valueOf(origem.getValueAt(adicao[i], 0)),
						String.valueOf(origem.getValueAt(adicao[i], 1)),
						String.valueOf(origem.getValueAt(adicao[i], 2))
						}
				);
			}
		}else{
			for(int i = destino.getRowCount(); i < adicao.length; i++){
				destino.addRow(new String[]{
						String.valueOf(origem.getValueAt(adicao[i], 0)),
						String.valueOf(origem.getValueAt(adicao[i], 1)),
						String.valueOf(origem.getValueAt(adicao[i], 2))
						}
				);
			}					
		}
	}
}
