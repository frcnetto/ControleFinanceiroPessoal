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

public class BuscRecebimento extends JInternalFrame implements KeyListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField cliente;
	private DateLabelFormatter data;
	private UtilDateModel dtRecebimentoModelo;
    private JDatePanelImpl dtRecebimentoPanel;
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
    private JLabel lblCliente;
    private JLabel lblDataDoRecebimento;
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

	public BuscRecebimento() {
		getContentPane().setLayout(null);
		
		valida = new ValidaCampos();
		trataTable = new TrataTable();
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 11, 55, 14);
		getContentPane().add(lblCliente);
		
		imagens = new Imagens();
		cliente = new JTextField();
		cliente.setBounds(10, 26, 509, 20);
		getContentPane().add(cliente);
		cliente.setColumns(10);
		
		lblDataDoRecebimento = new JLabel("Data do Recebimento");
		lblDataDoRecebimento.setBounds(10, 50, 103, 14);
		getContentPane().add(lblDataDoRecebimento);
		
		data = new DateLabelFormatter();        
		dtRecebimentoPanel = new JDatePanelImpl(dtRecebimentoModelo);        
		dtPagamentoPicker = new JDatePickerImpl(dtRecebimentoPanel, data);
		dtPagamentoPicker.setBounds(10, 67, 194, 20);
		getContentPane().add(dtPagamentoPicker);
		
		chckbxParcelado = new JCheckBox("parcelado?");
		chckbxParcelado.setBounds(275, 64, 77, 23);
		getContentPane().add(chckbxParcelado);
		
		lblDataDeEmisso = new JLabel("Data de Emiss\u00E3o");
		lblDataDeEmisso.setBounds(10, 89, 79, 14);
		getContentPane().add(lblDataDeEmisso);
		
		lblDataDeVencimento = new JLabel("Data de Vencimento");
		lblDataDeVencimento.setBounds(214, 89, 96, 14);
		getContentPane().add(lblDataDeVencimento);
		
		lblSituao = new JLabel("Situa\u00E7\u00E3o");
		lblSituao.setBounds(418, 51, 41, 14);
		getContentPane().add(lblSituao);
		
		dtEmissaoPanel = new JDatePanelImpl(dtEmissaoModelo);
		dtEmissaoPicker = new JDatePickerImpl(dtEmissaoPanel, data);
		dtEmissaoPicker.setBounds(10, 105, 194, 20);
		getContentPane().add(dtEmissaoPicker);
		
		dtVencimentoPanel = new JDatePanelImpl(dtVencimentoModelo);
		dtVencimentoPicker = new JDatePickerImpl(dtVencimentoPanel, data);
		dtVencimentoPicker.setBounds(214, 105, 194, 20);
		getContentPane().add(dtVencimentoPicker);
		
		situacao = new JComboBox<String>();
		situacao.setModel(new DefaultComboBoxModel<String>(new String[] {"Pendente", "Efetuado", "Cancelado", "Atrasado"}));
		situacao.setBounds(418, 67, 101, 20);
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
		setTitle("Consulta de Recebimentos");
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
