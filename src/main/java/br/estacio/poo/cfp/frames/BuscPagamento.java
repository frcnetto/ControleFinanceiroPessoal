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
import br.estacio.poo.cfp.util.TrataTable;
import br.estacio.poo.cfp.util.ValidaCampos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class BuscPagamento extends JInternalFrame implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField fornecedor;
	private DateLabelFormatter data;
	private UtilDateModel dtPagamentoModelo;
    private JDatePanelImpl dtPagamentoPanel;
	private JDatePickerImpl dtPagamentoPicker;
	private UtilDateModel dtEmissaoModelo;
    private JDatePanelImpl dtEmissaoPanel;
    private JDatePickerImpl dtEmissaoPicker;
    private UtilDateModel dtVencimentoModelo;
    private JDatePanelImpl dtVencimentoPanel;
    private JDatePickerImpl dtVencimentoPicker;
    private JComboBox<String> situacao;
    private Imagens imagens;
    private JButton btnOk;
    private JButton btnCancelar;
    private JLabel lblFornecedor;
    private JLabel lblDataDoPagamento;
    private JCheckBox chckbxParcelado;
    private JLabel lblDataDeEmisso;
    private JLabel lblDataDeVencimento;
    private JLabel lblSituao;
    private String[] colunas = new String[] {"N\u00FAmero", "Data", "Valor"};
	private DefaultTableModel tbResultModel;
	private ValidaCampos valida;
	private TrataTable trataTable;
	private JButton btnConsultar;
	private JTable tbResult;
	private JScrollPane scpResult;

	public BuscPagamento() {
		getContentPane().setLayout(null);
		
		valida = new ValidaCampos();
		trataTable = new TrataTable();
		
		lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(10, 11, 55, 14);
		getContentPane().add(lblFornecedor);
		
		imagens = new Imagens();
		fornecedor = new JTextField();
		fornecedor.setBounds(10, 26, 509, 20);
		getContentPane().add(fornecedor);
		fornecedor.setColumns(10);
		
		lblDataDoPagamento = new JLabel("Data do Pagamento");
		lblDataDoPagamento.setBounds(10, 50, 95, 14);
		getContentPane().add(lblDataDoPagamento);
		
		data = new DateLabelFormatter();        
		dtPagamentoPanel = new JDatePanelImpl(dtPagamentoModelo);        
		dtPagamentoPicker = new JDatePickerImpl(dtPagamentoPanel, data);
		dtPagamentoPicker.setBounds(10, 67, 194, 20);
		getContentPane().add(dtPagamentoPicker);
		
		chckbxParcelado = new JCheckBox("parcelado?");
		chckbxParcelado.setBounds(222, 64, 77, 23);
		getContentPane().add(chckbxParcelado);
		
		lblDataDeEmisso = new JLabel("Data de Emiss\u00E3o");
		lblDataDeEmisso.setBounds(325, 50, 79, 14);
		getContentPane().add(lblDataDeEmisso);
		
		lblDataDeVencimento = new JLabel("Data de Vencimento");
		lblDataDeVencimento.setBounds(121, 94, 96, 14);
		getContentPane().add(lblDataDeVencimento);
		
		lblSituao = new JLabel("Situa\u00E7\u00E3o");
		lblSituao.setBounds(10, 94, 41, 14);
		getContentPane().add(lblSituao);
		
		dtEmissaoPanel = new JDatePanelImpl(dtEmissaoModelo);
		dtEmissaoPicker = new JDatePickerImpl(dtEmissaoPanel, data);
		dtEmissaoPicker.setBounds(325, 66, 194, 20);
		getContentPane().add(dtEmissaoPicker);
		
		dtVencimentoPanel = new JDatePanelImpl(dtVencimentoModelo);
		dtVencimentoPicker = new JDatePickerImpl(dtVencimentoPanel, data);
		dtVencimentoPicker.setBounds(121, 110, 194, 20);
		getContentPane().add(dtVencimentoPicker);
		
		situacao = new JComboBox<String>();
		situacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Pendente", "Efetuado", "Cancelado", "Atrasado"}));
		situacao.setBounds(10, 110, 101, 20);
		getContentPane().add(situacao);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(424, 427, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnOk = new JButton("Ok");
		btnOk.setIcon(imagens.getImagens(12));
		btnOk.setBounds(315, 427, 101, 25);
		getContentPane().add(btnOk);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(imagens.getImagens(7));
		btnConsultar.setBounds(418, 105, 101, 25);
		getContentPane().add(btnConsultar);
		
		scpResult = new JScrollPane();
		scpResult.setBounds(10, 136, 509, 280);
		getContentPane().add(scpResult);
		
		tbResult = new JTable();
		scpResult.setViewportView(tbResult);
		tbResultModel = new DefaultTableModel(
				new Object[][] {
				},
				colunas
		);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Consulta de Pagamentos");
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
