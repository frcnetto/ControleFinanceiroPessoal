package br.estacio.poo.cfp.old;

import br.estacio.poo.cfp.util.DateLabelFormatter;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.TrataLabel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class FrameBuscPagamento extends JInternalFrame implements ActionListener{
    JTextField fornecedor;
    JButton procura;
    UtilDateModel pagamentoDataModelo;
    JDatePanelImpl pagamentoDataPanel;
    JDatePickerImpl pagamentoDataPicker;
    JTextField valor;
    JCheckBox parcelado;
    JTextField totParcela;
    JTextArea desc;
    UtilDateModel emissaoDataModelo;
    JDatePanelImpl emissaoDataPanel;
    JDatePickerImpl emissaoDataPicker;
    UtilDateModel vencimentoDataModelo;
    JDatePanelImpl vencimentoDataPanel;
    JDatePickerImpl vencimentoDataPicker;
    JComboBox situacao;
    JList pagar;
    JList pago;
    JButton mover;
    JButton todos;
    JButton cadastrar;
    JButton cancelar;
    Imagens imgs;
    GridBagLayout layout;
    GridBagConstraints constraints;
    TrataLabel mLabel;
    DateLabelFormatter data;
    public FrameBuscPagamento(){
        super("Cadastro de pagamentos");
        
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        setLayout(layout);
        
        fornecedor = new JTextField(60);
        fornecedor.setSize(60, 20);
        procura = new JButton();
        imgs = new Imagens();
        procura.setIcon(imgs.getImagens(7));
        pagamentoDataModelo = new UtilDateModel();
        emissaoDataModelo = new UtilDateModel();
        vencimentoDataModelo = new UtilDateModel();    
        
        data = new DateLabelFormatter();
        
        pagamentoDataPanel = new JDatePanelImpl(pagamentoDataModelo);        
        pagamentoDataPicker = new JDatePickerImpl(pagamentoDataPanel, data);
        
        valor = new JTextField();
        parcelado = new JCheckBox("parcelado?");
        totParcela = new JTextField();
        desc = new JTextArea();
        
        emissaoDataPanel = new JDatePanelImpl(emissaoDataModelo);
        emissaoDataPicker = new JDatePickerImpl(emissaoDataPanel, data);
        
        vencimentoDataPanel = new JDatePanelImpl(vencimentoDataModelo);
        vencimentoDataPicker = new JDatePickerImpl(vencimentoDataPanel, data);
        
        situacao = new JComboBox();
        pagar = new JList();
        pagar.setSize(33, 80);
        pago = new JList();
        pago.setSize(33, 80);
        mover = new JButton("<Mover>");
        todos = new JButton("<Todos>");
        cadastrar = new JButton("Cadastrar");
        cancelar = new JButton("Cancelar");
        mLabel = new TrataLabel();
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(mLabel.criaJLabel("Fornecedor:"), 0, 0, 4, 1);
        
        addComponent(fornecedor, 1, 0, 3, 1);
        addComponent(procura, 1, 3, 1, 1);
        
        addComponent(mLabel.criaJLabel("Data do pagamento:"), 2, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Valor:"), 2, 1, 1, 1);
        addComponent(mLabel.criaJLabel("Quantidade de parcelas:"), 2, 3, 1, 1);
        
        addComponent(pagamentoDataPicker, 3, 0, 1, 1);
        addComponent(valor, 3, 1, 1, 1);
        addComponent(parcelado, 3, 2, 1, 1);
        addComponent(totParcela, 3, 3, 1, 1);
        
        addComponent(mLabel.criaJLabel("Descrição:"), 4, 0, 1, 1);
        
        addComponent(desc, 5, 0, 4, 1);
        
        addComponent(mLabel.criaJLabel("Emissão:"), 6, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Vencimento:"), 6, 1, 1, 1);
        addComponent(mLabel.criaJLabel("Situação:"), 6, 3, 1, 1);
        
        addComponent(emissaoDataPicker, 7, 0, 1, 1);
        addComponent(vencimentoDataPicker, 7, 1, 1, 1);
        addComponent(situacao, 7, 3, 1, 1);
        
        addComponent(mLabel.criaJLabel("A pagar:"), 8, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Pago:"), 8, 3, 1, 1);
        
        addComponent(pagar, 9, 0, 1, 3);
        addComponent(mover, 9, 1, 2, 1);
        addComponent(pago, 9, 3, 1, 3);
        
        addComponent(todos, 10, 1, 2, 1);
        
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
