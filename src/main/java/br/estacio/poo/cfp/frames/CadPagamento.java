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
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class CadPagamento extends JInternalFrame implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField fornecedor;
	private DateLabelFormatter data;
	private UtilDateModel dtPagamentoModelo;
    private JDatePanelImpl dtPagamentoPanel;
	private JDatePickerImpl dtPagamentoPicker;
	private JTextField valor;
	private JFormattedTextField qtdParcelas;
	private UtilDateModel dtEmissaoModelo;
    private JDatePanelImpl dtEmissaoPanel;
    private JDatePickerImpl dtEmissaoPicker;
    private UtilDateModel dtVencimentoModelo;
    private JDatePanelImpl dtVencimentoPanel;
    private JDatePickerImpl dtVencimentoPicker;
    private JTable tbPagar;
    private DefaultTableModel tbPagarModel;
    private JComboBox<String> situacao;
    private JTable tbPago;
    private JButton moveDireitaSimples;
    private JButton moveDireitaTotal;
    private Imagens imagens;
    private JButton moveEsquerdaTotal;
    private JButton moveEsquerdaSimples;
    private JButton btnCadastrar;
    private JButton btnCancelar;
    private JLabel lblFornecedor;
    private JButton procurar;
    private JLabel lblDataDoPagamento;
    private JLabel lblValor;
    private JLabel lblQuantidadeDeParcelas;
    private JCheckBox chckbxParcelado;
    private JLabel lblDescrio;
    private JTextArea textArea;
    private JLabel lblDataDeEmisso;
    private JLabel lblDataDeVencimento;
    private JLabel lblSituao;
    private JLabel lblAPagar;
    private JLabel lblPago;
    private JScrollPane scpPagar;
    private JScrollPane scpPago;
    private String[] colunas = new String[] {"N\u00FAmero", "Data", "Valor"};
	private DefaultTableModel tbPagoModel;
	private ValidaCampos valida;
	private JButton btnAdicionar;
	private TrataTable trataTable;

	public CadPagamento() {
		getContentPane().setLayout(null);
		
		valida = new ValidaCampos();
		trataTable = new TrataTable();
		
		imagens = new Imagens();
		
		lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(10, 11, 55, 14);
		getContentPane().add(lblFornecedor);
		fornecedor = new JTextField();
		fornecedor.setBounds(10, 26, 450, 20);
		getContentPane().add(fornecedor);
		fornecedor.setColumns(10);
		
		procurar = new JButton();
		procurar.setIcon(imagens.getImagens(7));
		procurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		procurar.setBounds(470, 26, 49, 20);
		getContentPane().add(procurar);
		
		lblDataDoPagamento = new JLabel("Data do Pagamento");
		lblDataDoPagamento.setBounds(10, 50, 95, 14);
		getContentPane().add(lblDataDoPagamento);
		
		lblValor = new JLabel("Valor");
		lblValor.setBounds(214, 50, 24, 14);
		getContentPane().add(lblValor);
		
		lblQuantidadeDeParcelas = new JLabel("Qtd de Parcelas");
		lblQuantidadeDeParcelas.setBounds(443, 50, 76, 14);
		getContentPane().add(lblQuantidadeDeParcelas);
		
		data = new DateLabelFormatter();        
		dtPagamentoPanel = new JDatePanelImpl(dtPagamentoModelo);        
		dtPagamentoPicker = new JDatePickerImpl(dtPagamentoPanel, data);
		dtPagamentoPicker.setBounds(10, 67, 194, 20);
		getContentPane().add(dtPagamentoPicker);
		
		valor = new JMoneyField();
		valor.setHorizontalAlignment(SwingConstants.RIGHT);
		valor.setBounds(214, 67, 135, 20);
		getContentPane().add(valor);
		valor.setColumns(10);
		
		qtdParcelas = new JFormattedTextField();
		qtdParcelas.setHorizontalAlignment(SwingConstants.RIGHT);
		qtdParcelas.setText("0");
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
		textArea.setBounds(10, 113, 509, 83);
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
		
		moveDireitaSimples = new JButton();
		moveDireitaSimples.addActionListener(this);
		moveDireitaSimples.setIcon(imagens.getImagens(13));
		moveDireitaSimples.setBounds(245, 280, 39, 25);
		getContentPane().add(moveDireitaSimples);
		
		moveDireitaTotal = new JButton();
		moveDireitaTotal.addActionListener(this);
		moveDireitaTotal.setIcon(imagens.getImagens(14));
		moveDireitaTotal.setBounds(245, 311, 39, 25);
		getContentPane().add(moveDireitaTotal);
		
		moveEsquerdaSimples = new JButton();
		moveEsquerdaSimples.addActionListener(this);
		moveEsquerdaSimples.setIcon(imagens.getImagens(15));
		moveEsquerdaSimples.setBounds(245, 342, 39, 25);
		getContentPane().add(moveEsquerdaSimples);
		
		moveEsquerdaTotal = new JButton();
		moveEsquerdaTotal.addActionListener(this);
		moveEsquerdaTotal.setIcon(imagens.getImagens(16));
		moveEsquerdaTotal.setBounds(245, 373, 39, 25);
		getContentPane().add(moveEsquerdaTotal);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(424, 427, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(imagens.getImagens(12));
		btnCadastrar.setBounds(315, 427, 101, 25);
		getContentPane().add(btnCadastrar);
		
		scpPagar = new JScrollPane();
		scpPagar.setBounds(10, 253, 228, 167);
		getContentPane().add(scpPagar);
		
		tbPagar = new JTable();
		scpPagar.setViewportView(tbPagar);
		tbPagarModel = new DefaultTableModel(
				new Object[][] {
				},
				colunas
		);
		tbPagar.setModel(tbPagarModel);
		tbPagar.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		scpPago = new JScrollPane();
		scpPago.setBounds(294, 253, 225, 167);
		getContentPane().add(scpPago);
		
		tbPago = new JTable();
		tbPago.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tbPagoModel = new DefaultTableModel(
				new Object[][] {
				},
				colunas
		);
		tbPago.setModel(tbPagoModel);
		scpPago.setViewportView(tbPago);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(442, 88, 77, 23);
		btnAdicionar.addActionListener(this);
		getContentPane().add(btnAdicionar);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Pagamentos");
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
		} else if(e.getSource() == moveEsquerdaSimples){
			int[] linhas = tbPagar.getSelectedRows();
			trataTable.validaAdicao(tbPagoModel, tbPagoModel, linhas);
		} else if(e.getSource() == moveEsquerdaTotal){
			
		} else if(e.getSource() == moveDireitaSimples){
		
		} else if(e.getSource() == moveDireitaTotal){
			tbPagoModel = tbPagarModel;
			tbPago.setModel(tbPagoModel);
		}
	}
}
