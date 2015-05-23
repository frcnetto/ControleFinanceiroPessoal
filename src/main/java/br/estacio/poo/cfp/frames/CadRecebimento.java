package br.estacio.poo.cfp.frames;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import br.estacio.poo.cfp.util.DateLabelFormatter;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.JMoneyField;
import br.estacio.poo.cfp.util.TrataTable;
import br.estacio.poo.cfp.util.ValidaCampos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class CadRecebimento extends JInternalFrame implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField cliente;
	private DateLabelFormatter data;
	private UtilDateModel dtRecebimentoModelo;
    private JDatePanelImpl dtRecebimentoPanel;
	private JDatePickerImpl dtRecebimentoPicker;
	private JTextField valor;
	private JTextField qtdParcelas;
	private UtilDateModel dtEmissaoModelo;
    private JDatePanelImpl dtEmissaoPanel;
    private JDatePickerImpl dtEmissaoPicker;
    private UtilDateModel dtVencimentoModelo;
    private JDatePanelImpl dtVencimentoPanel;
    private JDatePickerImpl dtVencimentoPicker;
    private JTable tbPagar;
    private JComboBox<String> situacao;
    private JTable tbPago;
    private JButton moveEsquerdaSimples;
    private JButton moveEsquerdaTotal;
    private Imagens imagens;
    private JButton moveDireitaTotal;
    private JButton moveDireitaSimples;
    private JButton btnCadastrar;
    private JButton btnCancelar;
    private JLabel lblCliente;
    private JLabel lblDataDoRecebimento;
    private JCheckBox chckbxParcelado;
    private JTextArea textArea;
    private JLabel lblValor;
    private JLabel lblQuantidadeDeParcelas;
    private JLabel lblDescrio;
    private JLabel lblDataDeEmisso;
    private JLabel lblDataDeVencimento;
    private JLabel lblSituao;
    private JLabel lblAPagar;
    private JLabel lblPago;
    private JButton procurar;
	private ValidaCampos valida;
	private String[] colunas = new String[] {"N\u00FAmero", "Data", "Valor"};
	private JScrollPane scpPagar;
	private JScrollPane scpPago;
	private DefaultTableModel tbPagarModel;
	private DefaultTableModel tbPagoModel;
	private JButton btnAdicionar;
	private TrataTable trataTable;

	public CadRecebimento() {
		getContentPane().setLayout(null);
		
		valida = new ValidaCampos();
		trataTable = new TrataTable();
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 11, 33, 14);
		getContentPane().add(lblCliente);
		
		imagens = new Imagens();
		cliente = new JTextField();
		cliente.setBounds(10, 26, 450, 20);
		getContentPane().add(cliente);
		cliente.setColumns(10);
		
		procurar = new JButton();
		procurar.setIcon(imagens.getImagens(7));
		procurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		procurar.setBounds(470, 26, 49, 20);
		getContentPane().add(procurar);
		
		lblDataDoRecebimento = new JLabel("Data do Recebimento");
		lblDataDoRecebimento.setBounds(10, 50, 103, 14);
		getContentPane().add(lblDataDoRecebimento);
		
		lblValor = new JLabel("Valor");
		lblValor.setBounds(214, 50, 24, 14);
		getContentPane().add(lblValor);
		
		lblQuantidadeDeParcelas = new JLabel("Qtd de Parcelas");
		lblQuantidadeDeParcelas.setBounds(443, 50, 76, 14);
		getContentPane().add(lblQuantidadeDeParcelas);
		
		data = new DateLabelFormatter();        
		dtRecebimentoPanel = new JDatePanelImpl(dtRecebimentoModelo);        
		dtRecebimentoPicker = new JDatePickerImpl(dtRecebimentoPanel, data);
		dtRecebimentoPicker.setBounds(10, 67, 194, 20);
		getContentPane().add(dtRecebimentoPicker);
		
		valor = new JMoneyField();
		valor.setHorizontalAlignment(SwingConstants.RIGHT);
		valor.setBounds(214, 67, 135, 20);
		getContentPane().add(valor);
		valor.setColumns(10);
		
		qtdParcelas = new JTextField();
		qtdParcelas.setBounds(443, 67, 76, 20);
		qtdParcelas.addKeyListener(this);
		getContentPane().add(qtdParcelas);
		qtdParcelas.setColumns(10);
		
		chckbxParcelado = new JCheckBox("parcelado?");
		chckbxParcelado.setBounds(355, 67, 77, 23);
		getContentPane().add(chckbxParcelado);
		
		lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(10, 92, 46, 14);
		getContentPane().add(lblDescrio);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 112, 509, 83);
		getContentPane().add(textArea);
		
		lblDataDeEmisso = new JLabel("Data de Emiss\u00E3o");
		lblDataDeEmisso.setBounds(10, 197, 79, 14);
		getContentPane().add(lblDataDeEmisso);
		
		lblDataDeVencimento = new JLabel("Data de Vencimento");
		lblDataDeVencimento.setBounds(214, 197, 96, 14);
		getContentPane().add(lblDataDeVencimento);
		
		lblSituao = new JLabel("Situa\u00E7\u00E3o");
		lblSituao.setBounds(418, 197, 41, 14);
		getContentPane().add(lblSituao);
		
		dtEmissaoPanel = new JDatePanelImpl(dtEmissaoModelo);
		dtEmissaoPicker = new JDatePickerImpl(dtEmissaoPanel, data);
		dtEmissaoPicker.setBounds(10, 213, 194, 20);
		getContentPane().add(dtEmissaoPicker);
		
		dtVencimentoPanel = new JDatePanelImpl(dtVencimentoModelo);
		dtVencimentoPicker = new JDatePickerImpl(dtVencimentoPanel, data);
		dtVencimentoPicker.setBounds(214, 213, 194, 20);
		getContentPane().add(dtVencimentoPicker);
		
		situacao = new JComboBox<String>();
		situacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Pendente", "Efetuado", "Cancelado", "Atrasado"}));
		situacao.setBounds(418, 213, 101, 20);
		getContentPane().add(situacao);
		
		lblAPagar = new JLabel("A Pagar");
		lblAPagar.setBounds(10, 237, 38, 14);
		getContentPane().add(lblAPagar);
		
		lblPago = new JLabel("Pago");
		lblPago.setBounds(294, 237, 24, 14);
		getContentPane().add(lblPago);
		
		moveEsquerdaSimples = new JButton();
		moveEsquerdaSimples.setIcon(imagens.getImagens(13));
		moveEsquerdaSimples.setBounds(245, 280, 39, 25);
		getContentPane().add(moveEsquerdaSimples);
		
		moveEsquerdaTotal = new JButton();
		moveEsquerdaTotal.setIcon(imagens.getImagens(14));
		moveEsquerdaTotal.setBounds(245, 311, 39, 25);
		getContentPane().add(moveEsquerdaTotal);
		
		moveDireitaSimples = new JButton();
		moveDireitaSimples.setIcon(imagens.getImagens(15));
		moveDireitaSimples.setBounds(245, 342, 39, 25);
		getContentPane().add(moveDireitaSimples);
		
		moveDireitaTotal = new JButton();
		moveDireitaTotal.setIcon(imagens.getImagens(16));
		moveDireitaTotal.setBounds(245, 373, 39, 25);
		getContentPane().add(moveDireitaTotal);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(424, 427, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(imagens.getImagens(12));
		btnCadastrar.setBounds(315, 427, 101, 25);
		getContentPane().add(btnCadastrar);
		
		scpPagar = new JScrollPane();
		scpPagar.setBounds(8, 252, 227, 169);
		getContentPane().add(scpPagar);
		
		tbPagar = new JTable();
		scpPagar.setViewportView(tbPagar);
		tbPagarModel = new DefaultTableModel(
				new Object[][] {
					},
				colunas);
		tbPagar.setModel(tbPagarModel);
		tbPagar.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		scpPago = new JScrollPane();
		scpPago.setBounds(293, 252, 225, 170);
		getContentPane().add(scpPago);
		
		tbPago = new JTable();
		tbPagoModel = new DefaultTableModel(
				new Object[][] {
					},
				colunas);
		tbPago.setModel(tbPagoModel);
		scpPago.setViewportView(tbPago);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(442, 88, 77, 23);
		btnAdicionar.addActionListener(this);
		getContentPane().add(btnAdicionar);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Recebimento");
		setSize(546, 488);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imagens.getImagens(0));
        setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == qtdParcelas){
			valida.validaSomenteCom(valida.NUMEROS, e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAdicionar){
			trataTable.validaAdicao(tbPagarModel, Integer.parseInt(qtdParcelas.getText()));
//			if(tbPagarModel.getRowCount() == 0){
//				for(int i = 0; i < Integer.parseInt(qtdParcelas.getText()); i++){
//					String linha[] = {String.valueOf(i + 1), "", ""};
//					tbPagarModel.addRow(linha);
//				}				
//			}else {
//				if(tbPagarModel.getRowCount() < Integer.parseInt(qtdParcelas.getText())){
//					for(int i = tbPagarModel.getRowCount(); i < Integer.parseInt(qtdParcelas.getText()); i++){						
//						String linha[] = {String.valueOf(i + 1), "", ""};
//						tbPagarModel.addRow(linha);
//					}					
//				} else{
//					if(tbPagarModel.getRowCount() > Integer.parseInt(qtdParcelas.getText())){
//						for(int i = tbPagarModel.getRowCount() - 1; i > (Integer.parseInt(qtdParcelas.getText()) - 1); i--){
//							tbPagarModel.removeRow(i);
//						}
//					}
//				}
//			}
		} else if(e.getSource() == moveDireitaSimples){
			
		} else if(e.getSource() == moveDireitaTotal){
			
		} else if(e.getSource() == moveEsquerdaSimples){
		
		} else if(e.getSource() == moveEsquerdaTotal){
		
		}
	}
}
