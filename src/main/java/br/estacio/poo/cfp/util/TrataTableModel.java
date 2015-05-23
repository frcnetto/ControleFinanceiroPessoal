package br.estacio.poo.cfp.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.estacio.poo.cfp.entidades.Parcela;

public class TrataTableModel extends AbstractTableModel{
	private static final long serialVersionUID = -2549094659376298217L;

	private List<Parcela> parcelas;
	
	public TrataTableModel(List<Parcela> parcelas) {  
        this.parcelas = parcelas == null ? new ArrayList<Parcela>() : parcelas;  
    }
	
	public List<Parcela> getParcela() {
		return parcelas;
	}

	public void setParcela(List<Parcela> parcela) {
		this.parcelas = parcela;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
