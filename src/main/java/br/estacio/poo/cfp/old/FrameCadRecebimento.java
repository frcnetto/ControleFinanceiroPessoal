package br.estacio.poo.cfp.old;

import br.estacio.poo.cfp.util.DateLabelFormatter;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.JMoneyField;
import br.estacio.poo.cfp.util.TrataLabel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameCadRecebimento extends JInternalFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4250681882822287266L;

	JTextField cliente;
    JButton procura;
    UtilDateModel recebimentoDataModelo;
    JDatePanelImpl recebimentoDataPanel;
    JDatePickerImpl recebimentoDataPicker;
    JMoneyField valor;
    JCheckBox parcelado;
    JFormattedTextField totParcela;
    MaskFormatter mfParcela;
    JTextArea desc;
    UtilDateModel emissaoDataModelo;
    JDatePanelImpl emissaoDataPanel;
    JDatePickerImpl emissaoDataPicker;
    UtilDateModel vencimentoDataModelo;
    JDatePanelImpl vencimentoDataPanel;
    JDatePickerImpl vencimentoDataPicker;
    JComboBox<String> situacao;
    String[] situacoes  = {
    		"Pendente", 
    		"Efetuado", 
    		"Cancelado", 
    		"Atrasado"};
    String[] colunas = new String[]{
    		"N�mero", 
    		"Vencimento", 
    		"Valor"};
    String[][] dados = new String[][]{
    		{"-", "-", "-"}};
    DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
    JTable pagar;
    JTable pago;
    JScrollPane jspPagar;
    JScrollPane jspPago;
    JButton mover;
    JButton todos;
    JButton cadastrar;
    JButton cancelar;
    Imagens imgs;
    GridBagLayout layout;
    GridBagConstraints constraints;
    TrataLabel mLabel;
    DateLabelFormatter data;
    public FrameCadRecebimento(){
        super("Cadastro de recebimentos");
        
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        setLayout(layout);
        
        cliente = new JTextField(60);
        cliente.setSize(60, 20);
        procura = new JButton();
        imgs = new Imagens();
        procura.setIcon(imgs.getImagens(7));
        recebimentoDataModelo = new UtilDateModel();
        emissaoDataModelo = new UtilDateModel();
        vencimentoDataModelo = new UtilDateModel();
        
        data = new DateLabelFormatter();
        
        recebimentoDataPanel = new JDatePanelImpl(recebimentoDataModelo);        
        recebimentoDataPicker = new JDatePickerImpl(recebimentoDataPanel, data);
        
        valor = new JMoneyField();
        parcelado = new JCheckBox("parcelado?");
        try {
			mfParcela = new MaskFormatter("###");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        totParcela = new JFormattedTextField(mfParcela);
        desc = new JTextArea();
        
        emissaoDataPanel = new JDatePanelImpl(emissaoDataModelo);
        emissaoDataPicker = new JDatePickerImpl(emissaoDataPanel, data);
        
        vencimentoDataPanel = new JDatePanelImpl(vencimentoDataModelo);
        vencimentoDataPicker = new JDatePickerImpl(vencimentoDataPanel, data);
        
        situacao = new JComboBox<String>(situacoes);
        pagar = new JTable(modelo);
        jspPagar = new JScrollPane(pagar);
        pago = new JTable(modelo);
        jspPago = new JScrollPane(pago);
        mover = new JButton("<Mover>");
        todos = new JButton("<Todos>");
        cadastrar = new JButton("Cadastrar");
        cadastrar.setIcon(imgs.getImagens(12));
        cancelar = new JButton("Cancelar");
        cancelar.setIcon(imgs.getImagens(1));
        mLabel = new TrataLabel();
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        addComponent(mLabel.criaJLabel("Cliente:"), 0, 0, 4, 1);
        
        addComponent(cliente, 1, 0, 3, 1);
        addComponent(procura, 1, 3, 1, 1);
        
        addComponent(mLabel.criaJLabel("Data do recebimento:"), 2, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Valor:"), 2, 1, 1, 1);
        addComponent(mLabel.criaJLabel("Quantidade de parcelas:"), 2, 3, 1, 1);
        
        addComponent(recebimentoDataPicker, 3, 0, 1, 1);
        addComponent(valor, 3, 1, 1, 1);
        addComponent(parcelado, 3, 2, 1, 1);
        addComponent(totParcela, 3, 3, 1, 1);
        
        addComponent(mLabel.criaJLabel("Descri��o:"), 4, 0, 1, 1);
        
        addComponent(desc, 5, 0, 4, 1);
        
        addComponent(mLabel.criaJLabel("Emiss�o:"), 6, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Vencimento:"), 6, 1, 1, 1);
        addComponent(mLabel.criaJLabel("Situa��o:"), 6, 3, 1, 1);
        
        addComponent(emissaoDataPicker, 7, 0, 1, 1);
        addComponent(vencimentoDataPicker, 7, 1, 1, 1);
        addComponent(situacao, 7, 3, 1, 1);
        
        addComponent(mLabel.criaJLabel("A pagar:"), 8, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Pago:"), 8, 3, 1, 1);
        
        addComponent(jspPagar, 9, 0, 1, 3);
        addComponent(mover, 9, 1, 2, 1);
        addComponent(jspPago, 9, 3, 1, 3);
        
        addComponent(todos, 10, 1, 2, 1);
        
        addComponent(cadastrar, 12, 2, 1, 1);
        addComponent(cancelar, 12, 3, 1, 1);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);       
        setSize(500, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imgs.getImagens(0));
        moveToFront();
        setVisible(true);
    }
    
    private void addComponent(Component component, int linha, int coluna, int largura, int altura){
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;
        layout.setConstraints(component, constraints);
        add(component);
    } 
    
    public void actionPerformed(ActionEvent e) {
    }
}