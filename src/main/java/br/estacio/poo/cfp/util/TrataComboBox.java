package br.estacio.poo.cfp.util;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class TrataComboBox {
	public TrataComboBox(){}
	
	public DefaultComboBoxModel<String> carregaCombo(JComboBox<String> comboBox, List<String> lista){
        DefaultComboBoxModel<String>  comboModel = (DefaultComboBoxModel<String>) comboBox.getModel();
        
        comboModel.removeAllElements();
        comboModel.addElement("------");
        
        for (int linha = 0; linha < lista.size(); linha++){
            String estado = lista.get(linha);
            comboModel.addElement(estado);
        }
        
        return comboModel;
    }
}
