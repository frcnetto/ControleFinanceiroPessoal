package br.estacio.poo.cfp.frames;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import br.estacio.poo.cfp.dao.FornecedorDao;
import br.estacio.poo.cfp.dao.PagamentoDao;
import br.estacio.poo.cfp.entidades.Pagamento;
import br.estacio.poo.cfp.util.DateLabelFormatter;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.JMoneyField;
import br.estacio.poo.cfp.util.TrataTable;
import br.estacio.poo.cfp.util.ValidaCampos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;

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

public class CadPagamento extends JInternalFrame implements KeyListener, ActionListener, ItemListener{

	private static final long serialVersionUID = 1L;
	private JTextField tfFornecedor;
	private DateLabelFormatter data;
	private UtilDateModel dtPagamentoModelo;
    private JDatePanelImpl dtPagamentoPanel;
	private JDatePickerImpl dtPagamentoPicker;
	private JMoneyField valor;
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
    private JCheckBox ckbxParcelado;
    private JLabel lblDescrio;
    private JTextArea descricao;
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
	Locale locBrazil = new Locale("pt", "BR");
	NumberFormat nf = NumberFormat.getInstance(locBrazil);

	public CadPagamento() {
		getContentPane().setLayout(null);
		
		valida = new ValidaCampos();
		trataTable = new TrataTable();
		
		imagens = new Imagens();
		
		lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(10, 11, 55, 14);
		getContentPane().add(lblFornecedor);
		tfFornecedor = new JTextField();
		tfFornecedor.setBounds(10, 26, 470, 20);
		getContentPane().add(tfFornecedor);
		tfFornecedor.setColumns(10);
		
		procurar = new JButton();
		procurar.setIcon(imagens.getImagens(7));
		procurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		procurar.setBounds(490, 26, 49, 20);
		getContentPane().add(procurar);
		
		lblDataDoPagamento = new JLabel("Data do Pagamento");
		lblDataDoPagamento.setBounds(10, 50, 95, 14);
		getContentPane().add(lblDataDoPagamento);
		
		lblValor = new JLabel("Valor");
		lblValor.setBounds(214, 50, 24, 14);
		getContentPane().add(lblValor);
		
		lblQuantidadeDeParcelas = new JLabel("Qtd de Parcelas");
		lblQuantidadeDeParcelas.setBounds(464, 197, 76, 14);
		getContentPane().add(lblQuantidadeDeParcelas);
		
		data = new DateLabelFormatter();        
		dtPagamentoPanel = new JDatePanelImpl(dtPagamentoModelo);        
		dtPagamentoPicker = new JDatePickerImpl(dtPagamentoPanel, data);
		dtPagamentoPicker.setBounds(10, 67, 194, 20);
		getContentPane().add(dtPagamentoPicker);
		
		valor = new JMoneyField();
		valor.setText("0");
		valor.setHorizontalAlignment(SwingConstants.RIGHT);
		valor.setBounds(214, 67, 135, 20);
		getContentPane().add(valor);
		valor.setColumns(10);
		
		qtdParcelas = new JFormattedTextField();
		qtdParcelas.setHorizontalAlignment(SwingConstants.RIGHT);
		qtdParcelas.setText("0");
		qtdParcelas.setBounds(464, 214, 76, 20);
		qtdParcelas.addKeyListener(this);
		qtdParcelas.setEnabled(false);
		getContentPane().add(qtdParcelas);
		qtdParcelas.setColumns(10);
		
		ckbxParcelado = new JCheckBox("parcelado?");
		ckbxParcelado.setBounds(355, 67, 77, 23);
		ckbxParcelado.addItemListener(this);
		getContentPane().add(ckbxParcelado);
		
		lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(10, 92, 46, 14);
		getContentPane().add(lblDescrio);
		
		descricao = new JTextArea();
		descricao.setBounds(10, 113, 529, 83);
		getContentPane().add(descricao);
		
		lblDataDeEmisso = new JLabel("Data de Emiss\u00E3o");
		lblDataDeEmisso.setBounds(10, 197, 79, 14);
		getContentPane().add(lblDataDeEmisso);
		
		lblDataDeVencimento = new JLabel("Data de Vencimento");
		lblDataDeVencimento.setBounds(214, 197, 96, 14);
		getContentPane().add(lblDataDeVencimento);
		
		lblSituao = new JLabel("Situa\u00E7\u00E3o");
		lblSituao.setBounds(438, 51, 41, 14);
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
		situacao.setBounds(438, 67, 101, 20);
		getContentPane().add(situacao);
		
		lblAPagar = new JLabel("A Pagar");
		lblAPagar.setBounds(10, 262, 38, 14);
		getContentPane().add(lblAPagar);
		
		lblPago = new JLabel("Pago");
		lblPago.setBounds(314, 262, 24, 14);
		getContentPane().add(lblPago);
		
		moveDireitaSimples = new JButton();
		moveDireitaSimples.addActionListener(this);
		moveDireitaSimples.setIcon(imagens.getImagens(13));
		moveDireitaSimples.setBounds(248, 304, 56, 25);
		getContentPane().add(moveDireitaSimples);
		
		moveDireitaTotal = new JButton();
		moveDireitaTotal.addActionListener(this);
		moveDireitaTotal.setIcon(imagens.getImagens(14));
		moveDireitaTotal.setBounds(248, 335, 56, 25);
		getContentPane().add(moveDireitaTotal);
		
		moveEsquerdaSimples = new JButton();
		moveEsquerdaSimples.addActionListener(this);
		moveEsquerdaSimples.setIcon(imagens.getImagens(15));
		moveEsquerdaSimples.setBounds(248, 366, 56, 25);
		getContentPane().add(moveEsquerdaSimples);
		
		moveEsquerdaTotal = new JButton();
		moveEsquerdaTotal.addActionListener(this);
		moveEsquerdaTotal.setIcon(imagens.getImagens(16));
		moveEsquerdaTotal.setBounds(248, 397, 56, 25);
		getContentPane().add(moveEsquerdaTotal);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(444, 448, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(imagens.getImagens(12));
		btnCadastrar.addActionListener(this);
		btnCadastrar.setBounds(335, 448, 101, 25);
		getContentPane().add(btnCadastrar);
		
		scpPagar = new JScrollPane();
		scpPagar.setBounds(10, 278, 228, 167);
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
		scpPago.setBounds(314, 278, 225, 167);
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
		btnAdicionar.setBounds(463, 235, 77, 23);
		btnAdicionar.addActionListener(this);
		btnAdicionar.setEnabled(false);
		getContentPane().add(btnAdicionar);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Pagamentos");
		setSize(566, 513);
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
		if(e.getSource() == btnCadastrar){
			FornecedorDao fornecedorDao = new FornecedorDao();
			Pagamento pagamento = new Pagamento();
			pagamento.setFornecedor(fornecedorDao.buscaPeloNome(tfFornecedor.getText()));
			pagamento.setDtPaamento((Calendar) dtVencimentoPicker.getModel().getValue());
			try {
				pagamento.setValor((double) nf.parse(valor.getText()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pagamento.setParcela(ckbxParcelado.isSelected());
			pagamento.setSituacao(String.valueOf(situacao.getSelectedItem()));
			pagamento.setDesc(descricao.getText());
			pagamento.setEmissao((Calendar) dtEmissaoPicker.getModel().getValue());
			pagamento.setVencimento((Calendar) dtVencimentoPicker.getModel().getValue());
			pagamento.setTotParcela((int) qtdParcelas.getValue());
			pagamento.setParcelas(trataTable.carregaLista(tbPagarModel, tbPagoModel, pagamento));
			PagamentoDao pagamentoDao = new PagamentoDao();
			pagamentoDao.cadastraPagamento(pagamento);
		}else if(e.getSource() == btnAdicionar){
			trataTable.validaAdicao(tbPagarModel, Integer.parseInt(qtdParcelas.getText()), (Calendar)dtVencimentoPicker.getModel().getValue(), valor.getText());
		} else if(e.getSource() == moveEsquerdaSimples){
			trataTable.validaTransferencia(tbPagarModel, tbPagoModel, tbPago.getSelectedRows());
		} else if(e.getSource() == moveEsquerdaTotal){
			trataTable.validaTransferencia(tbPagarModel, tbPagoModel);
		} else if(e.getSource() == moveDireitaSimples){
			trataTable.validaTransferencia(tbPagoModel, tbPagarModel, tbPagar.getSelectedRows());
		} else if(e.getSource() == moveDireitaTotal){
			trataTable.validaTransferencia(tbPagoModel, tbPagarModel);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == ckbxParcelado){
			if(!qtdParcelas.isEnabled() && !btnAdicionar.isEnabled()){
				qtdParcelas.setEnabled(true);
				btnAdicionar.setEnabled(true);
			} else{
				qtdParcelas.setEnabled(false);
				btnAdicionar.setEnabled(false);
			}
		}
	}
}
