package br.estacio.poo.cfp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class TrataTable extends AbstractTableModel {
	private static final long serialVersionUID = -3483215688827241245L;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private String valor = new String();

	public TrataTable(){}
	
	public void validaAdicao(DefaultTableModel modelo, int qtdLinhas, Calendar  vencimento, String valor){
		this.valor = valor.replace(".", "");
		this.valor = this.valor.replace(",", ".");
		if(modelo.getRowCount() <= qtdLinhas){
			limpaTabela(modelo);
			for(int i = 0; i < qtdLinhas; i++){
				String linha[] = {String.valueOf(i + 1), 
								  String.valueOf(
										  dateFormat.format(
												  vencimento.getTime()
										  )
								  ), 
								  String.valueOf(
										  Float.parseFloat(
												  this.valor)/qtdLinhas
										  )
								  };
				vencimento.add(Calendar.MONTH, 1);
				modelo.addRow(linha);
			}				
		}else {
			if(modelo.getRowCount() > qtdLinhas){
				for(int i = modelo.getRowCount() - 1; i > qtdLinhas - 1; i--){
					modelo.removeRow(i);
				}
			}
		}
	}
	
	public void validaTransferencia(DefaultTableModel destinoModel, DefaultTableModel origemModel){		
		for(int i = 0; i < origemModel.getRowCount(); i++){
			String[] linha = {String.valueOf(origemModel.getValueAt(i, 0)), 
							  String.valueOf(origemModel.getValueAt(i, 1)), 
							  String.valueOf(origemModel.getValueAt(i, 2))};
			destinoModel.addRow(linha);
		}
		
		for(int i = origemModel.getRowCount() - 1; i >= 0; i--){
			origemModel.removeRow(i);
		}
	}
	
	public void validaTransferencia(DefaultTableModel destinoModel, DefaultTableModel origemModel, int[] linhas){				
		for(int i = 0; i < linhas.length; i++){
			String[] linha = {String.valueOf(origemModel.getValueAt(linhas[i], 0)), 
							  String.valueOf(origemModel.getValueAt(linhas[i], 1)), 
							  String.valueOf(origemModel.getValueAt(linhas[i], 2))};
			destinoModel.addRow(linha);
		}

		for(int i = linhas.length - 1; i >= 0; i--){
			origemModel.removeRow(linhas[i]);
		}
	}
	
	public Calendar retornaCalendar(String data){
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal  = Calendar.getInstance();
		try {
			cal.setTime(df.parse(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}
	
	public void limpaTabela(DefaultTableModel modelo){
		if(modelo.getRowCount() > 0){
			int total = modelo.getRowCount();
			for(int i = 0; i < total; i++){
				modelo.removeRow(0);
			}
		}
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
	
//	public List<Parcela> carregaLista(DefaultTableModel pagar, DefaultTableModel pago, Conta conta){
//		parcelas = null;
//		if(pagar.getRowCount() > 0)
//			for(int i = 0; i < pagar.getRowCount(); i++){
//				parcelas.add(i, new Parcela(conta, 
//										   (int)(pagar.getValueAt(i, 0)), 
//										   (Calendar)pagar.getValueAt(i, 1), 
//										   (double)pagar.getValueAt(i, 2), 
//										   false));
//			}
//		if(pago.getRowCount() > 0)
//			for(int i = 0; i < pago.getRowCount(); i++){
//				parcelas.add(i, new Parcela(conta, 
//										   (int)(pago.getValueAt(i, 0)), 
//										   (Calendar)pago.getValueAt(i, 1), 
//										   (double)pago.getValueAt(i, 2), 
//										   true));
//			}
//		return parcelas;		
//	}
}
