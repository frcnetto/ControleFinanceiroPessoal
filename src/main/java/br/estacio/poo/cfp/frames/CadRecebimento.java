package br.estacio.poo.cfp.frames;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import br.estacio.poo.cfp.dao.ClienteDao;
import br.estacio.poo.cfp.dao.RecebimentoDao;
import br.estacio.poo.cfp.entidades.Cliente;
import br.estacio.poo.cfp.entidades.Recebimento;
import br.estacio.poo.cfp.entidades.Parcela;
import br.estacio.poo.cfp.util.DateLabelFormatter;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.JMoneyField;
import br.estacio.poo.cfp.util.TrataJInternalFrame;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class CadRecebimento extends JInternalFrame implements KeyListener, ActionListener, ItemListener{

	private static final long serialVersionUID = 1L;
	private JTextField tfCliente;
	private DateLabelFormatter data;
	private UtilDateModel dtRecebimentoModelo;
    private JDatePanelImpl dtRecebimentoPanel;
	private JDatePickerImpl dtRecebimentoPicker;
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
    private JButton btnMoveDireitaSimples;
    private JButton btnMoveDireitaTotal;
    private Imagens imagens;
    private JButton btnMoveEsquerdaTotal;
    private JButton btnMoveEsquerdaSimples;
    private JButton btnCadastrar;
    private JButton btnCancelar;
    private JLabel lblCliente;
    private JButton btnProcurar;
    private JLabel lblDataDoRecebimento;
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
	private ArrayList<Parcela> parcelas;
	private BuscCliente buscCliente = new BuscCliente();
	private JDesktopPane dsktLocal;
	private TrataJInternalFrame trataJInternalFrame = new TrataJInternalFrame();
	private Cliente cliente;
	
	Locale locBrazil = new Locale("pt", "BR");
	NumberFormat nf = NumberFormat.getInstance(locBrazil);
	
	public CadRecebimento(){
		
	}

	public CadRecebimento(final JDesktopPane dsktPane) {
		getContentPane().setLayout(null);
		
		dsktLocal = dsktPane;
		
		valida = new ValidaCampos();
		trataTable = new TrataTable();
		
		imagens = new Imagens();
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 11, 55, 14);
		getContentPane().add(lblCliente);
		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setBounds(10, 26, 470, 20);
		getContentPane().add(tfCliente);
		tfCliente.setColumns(10);
		
		cliente = new Cliente();
		
		btnProcurar = new JButton();
		btnProcurar.setIcon(imagens.getImagens(7));
		btnProcurar.addActionListener(this);
		btnProcurar.setBounds(490, 26, 49, 20);
		getContentPane().add(btnProcurar);
		
		lblDataDoRecebimento = new JLabel("Data do Recebimento");
		lblDataDoRecebimento.setBounds(10, 50, 95, 14);
		getContentPane().add(lblDataDoRecebimento);
		
		lblValor = new JLabel("Valor");
		lblValor.setBounds(214, 50, 24, 14);
		getContentPane().add(lblValor);
		
		lblQuantidadeDeParcelas = new JLabel("Qtd de Parcelas");
		lblQuantidadeDeParcelas.setBounds(464, 197, 76, 14);
		getContentPane().add(lblQuantidadeDeParcelas);
		
		data = new DateLabelFormatter();        
		dtRecebimentoPanel = new JDatePanelImpl(dtRecebimentoModelo);        
		dtRecebimentoPicker = new JDatePickerImpl(dtRecebimentoPanel, data);
		dtRecebimentoPicker.setBounds(10, 67, 194, 20);
		getContentPane().add(dtRecebimentoPicker);
		
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
		
		btnMoveDireitaSimples = new JButton();
		btnMoveDireitaSimples.addActionListener(this);
		btnMoveDireitaSimples.setIcon(imagens.getImagens(13));
		btnMoveDireitaSimples.setBounds(248, 304, 56, 25);
		getContentPane().add(btnMoveDireitaSimples);
		
		btnMoveDireitaTotal = new JButton();
		btnMoveDireitaTotal.addActionListener(this);
		btnMoveDireitaTotal.setIcon(imagens.getImagens(14));
		btnMoveDireitaTotal.setBounds(248, 335, 56, 25);
		getContentPane().add(btnMoveDireitaTotal);
		
		btnMoveEsquerdaSimples = new JButton();
		btnMoveEsquerdaSimples.addActionListener(this);
		btnMoveEsquerdaSimples.setIcon(imagens.getImagens(15));
		btnMoveEsquerdaSimples.setBounds(248, 366, 56, 25);
		getContentPane().add(btnMoveEsquerdaSimples);
		
		btnMoveEsquerdaTotal = new JButton();
		btnMoveEsquerdaTotal.addActionListener(this);
		btnMoveEsquerdaTotal.setIcon(imagens.getImagens(16));
		btnMoveEsquerdaTotal.setBounds(248, 397, 56, 25);
		getContentPane().add(btnMoveEsquerdaTotal);
		
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
		setTitle("Cadastro de Recebimentos");
		setSize(566, 513);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imagens.getImagens(0));
        setVisible(true);
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
			if(!tfCliente.getText().isEmpty()){
				if(!dtRecebimentoPicker.getJFormattedTextField().getText().isEmpty()){
					if(!valor.getText().equals("0,00")){
						if(!dtEmissaoPicker.getJFormattedTextField().getText().isEmpty()){
							if(!dtVencimentoPicker.getJFormattedTextField().getText().isEmpty()){
								if(!(ckbxParcelado.isSelected() && (qtdParcelas.getText().isEmpty() || qtdParcelas.getText().equals("0")))){
									if(!(ckbxParcelado.isSelected() && tbPagar.getRowCount() == 0 && tbPago.getRowCount() == 0)){
										ClienteDao clienteDao = new ClienteDao();
										Recebimento recebimento = new Recebimento();
										RecebimentoDao recebimentoDao = new RecebimentoDao();
										Parcela parcela;
										parcelas = new ArrayList<Parcela>();
										
										recebimento.setCliente(clienteDao.buscaCliente(cliente));
										recebimento.setDtRecebimento(trataTable.retornaCalendar(dtRecebimentoPicker.getJFormattedTextField().getText()));
										try {
											recebimento.setValor((double) nf.parse(valor.getText()));
										} catch (ParseException e1) {
											e1.printStackTrace();
										}
										recebimento.setParcela(ckbxParcelado.isSelected());
										recebimento.setSituacao(String.valueOf(situacao.getSelectedItem()));
										recebimento.setDesc(descricao.getText());
										recebimento.setEmissao(trataTable.retornaCalendar(dtEmissaoPicker.getJFormattedTextField().getText()));
										recebimento.setVencimento(trataTable.retornaCalendar(dtVencimentoPicker.getJFormattedTextField().getText()));
										recebimento.setTotParcela(Integer.parseInt(qtdParcelas.getText()));
										
										for(int i = 0; i < tbPagarModel.getRowCount(); i++){
											parcela = new Parcela();
											parcela.setConta(recebimento);
											parcela.setNumero(Integer.parseInt(tbPagarModel.getValueAt(i, 0).toString()));
											parcela.setVencimento(trataTable.retornaCalendar(tbPagarModel.getValueAt(i, 1).toString()));
											parcela.setValor(Float.parseFloat(tbPagarModel.getValueAt(i, 2).toString()));
											parcela.setPago(false);
											parcelas.add(parcela);
										}

										for(int i = 0; i < tbPagoModel.getRowCount(); i++){
											parcela = new Parcela();
											parcela.setConta(recebimento);
											parcela.setNumero(Integer.parseInt(tbPagoModel.getValueAt(i, 0).toString()));
											parcela.setVencimento(trataTable.retornaCalendar(tbPagoModel.getValueAt(i, 1).toString()));
											parcela.setValor(Float.parseFloat(tbPagoModel.getValueAt(i, 2).toString()));
											parcela.setPago(true);
											parcelas.add(parcela);
										}
										
										recebimentoDao.cadastraRecebimento(recebimento, parcelas);
									} else{
										JOptionPane.showMessageDialog(null, "Voce não adicionou nenhuma parcela!", "Erro!", JOptionPane.ERROR_MESSAGE);
									}
								} else{									
									JOptionPane.showMessageDialog(null, "Campo Quantidade de Parcelas é um campo obrigatório!", "Erro!", JOptionPane.ERROR_MESSAGE);
								}
							} else{
								JOptionPane.showMessageDialog(null, "Campo Data de Vencimento é um campo obrigatório!", "Erro!", JOptionPane.ERROR_MESSAGE);
							}
						} else{
							JOptionPane.showMessageDialog(null, "Campo Data de Emissão é um campo obrigatório!", "Erro!", JOptionPane.ERROR_MESSAGE);
						}
					} else{
						JOptionPane.showMessageDialog(null, "Campo Valor é um campo obrigatório!", "Erro!", JOptionPane.ERROR_MESSAGE);
					}
				} else{
					JOptionPane.showMessageDialog(null, "Campo Data do Recebimento é um campo obrigatório!", "Erro!", JOptionPane.ERROR_MESSAGE);
				}
			} else{
				JOptionPane.showMessageDialog(null, "Campo cliente é um campo obrigatório! Busque um Cliente clicando na lupa.", "Erro!", JOptionPane.ERROR_MESSAGE);
			}			
		}else if(e.getSource() == btnAdicionar){
			trataTable.validaAdicao(tbPagarModel, Integer.parseInt(qtdParcelas.getText()), (Calendar)dtVencimentoPicker.getModel().getValue(), valor.getText());
		} else if(e.getSource() == btnMoveEsquerdaSimples){
			trataTable.validaTransferencia(tbPagarModel, tbPagoModel, tbPago.getSelectedRows());
		} else if(e.getSource() == btnMoveEsquerdaTotal){
			trataTable.validaTransferencia(tbPagarModel, tbPagoModel);
		} else if(e.getSource() == btnMoveDireitaSimples){
			trataTable.validaTransferencia(tbPagoModel, tbPagarModel, tbPagar.getSelectedRows());
		} else if(e.getSource() == btnMoveDireitaTotal){
			trataTable.validaTransferencia(tbPagoModel, tbPagarModel);
		} else if(e.getSource() == btnProcurar){
			if(!trataJInternalFrame.buscaFrame(buscCliente, dsktLocal.getAllFrames())){
				buscCliente = new BuscCliente(this);
				dsktLocal.add(buscCliente);
			}				
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

	public void setNome(String nome) {
		tfCliente.setText(nome);		
	}
}
