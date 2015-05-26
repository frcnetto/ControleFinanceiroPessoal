package br.estacio.poo.cfp.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import br.estacio.poo.cfp.dao.CidadeDao;
import br.estacio.poo.cfp.dao.ClienteDao;
import br.estacio.poo.cfp.dao.EstadoDao;
import br.estacio.poo.cfp.entidades.Cliente;
import br.estacio.poo.cfp.old.FrameCadCliente;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.JMoneyField;
import br.estacio.poo.cfp.util.ManipulaThreads;
import br.estacio.poo.cfp.util.TrataComboBox;
import br.estacio.poo.cfp.util.ValidaCampos;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

public class CadCliente extends JInternalFrame implements ItemListener, KeyListener, ActionListener, FocusListener{

	private static final long serialVersionUID = 1L;

    private Imagens imagens;
    private JLabel lblNome;
    private JFormattedTextField nome;
    private ValidaCampos valida = new ValidaCampos();
    private JComboBox<String> cmbxUF;
    private EstadoDao estadoDao = new EstadoDao();
    private ComboBoxModel<String> ufModel;
    private ComboBoxModel<String> cidadeModel;
    private TrataComboBox manipulaCombo = new TrataComboBox();
    private JTextField endereco;
    private JTextField servico;
    private JMoneyField vlrMensal;
    private JLabel lblEndereo;
    private JLabel lblUf;
    private JLabel lblCidade;
    private JComboBox<String> cmbxCidade;
    private CidadeDao cidadeDao = new CidadeDao();
    private JLabel lblTelefone;
    private JLabel lblCelular;
    private JFormattedTextField telefone;
    private JFormattedTextField celular;
    private MaskFormatter mfTelefone;
    private JLabel lblServio;
    private JLabel lblValorMensal;
    private JCheckBox contrato;
    private JLabel lblObservaes;
    private JTextArea observacoes;
    private JButton btnCancelar;
    private JButton btnCadastrar;
    ManipulaThreads manipulaThreads;

	public CadCliente() {
		getContentPane().setLayout(null);
		
		imagens = new Imagens();
		manipulaThreads = new ManipulaThreads();
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 27, 14);
		getContentPane().add(lblNome);
		
		nome = new JFormattedTextField();
		nome.setBounds(10, 27, 510, 20);
		nome.addKeyListener(this);
		getContentPane().add(nome);
		nome.setColumns(10);
		
		lblEndereo = new JLabel("Endere\u00E7o");
		lblEndereo.setBounds(10, 52, 45, 14);
		getContentPane().add(lblEndereo);
		
		lblUf = new JLabel("UF");
		lblUf.setBounds(220, 52, 13, 14);
		getContentPane().add(lblUf);
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(374, 52, 33, 14);
		getContentPane().add(lblCidade);
		
		cmbxCidade = new JComboBox<String>();
		cmbxCidade.setBounds(374, 68, 146, 20);
		cmbxCidade.setEnabled(false);
		getContentPane().add(cmbxCidade);
		
		cmbxUF = new JComboBox<String>();
		cmbxUF.setBounds(220, 68, 146, 20);
		ufModel = manipulaCombo.carregaCombo(cmbxUF, estadoDao.carregaUf());
        cmbxUF.setModel(ufModel);
		cmbxUF.addItemListener(this);
		getContentPane().add(cmbxUF);
		
		endereco = new JTextField();
		endereco.setBounds(10, 68, 200, 20);
		getContentPane().add(endereco);
		endereco.setColumns(10);		
		
		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 93, 42, 14);
		getContentPane().add(lblTelefone);
		
		lblCelular = new JLabel("Celular");
		lblCelular.setBounds(320, 93, 33, 14);
		getContentPane().add(lblCelular);
		
		try {
            mfTelefone = new MaskFormatter("(##) ####-####");
        } catch (ParseException ex) {
            Logger.getLogger(FrameCadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		telefone = new JFormattedTextField(mfTelefone);
		telefone.setBounds(10, 110, 200, 20);
		getContentPane().add(telefone);
		
		celular = new JFormattedTextField(mfTelefone);
		celular.setBounds(320, 110, 200, 20);
		getContentPane().add(celular);
		
		lblServio = new JLabel("Servi\u00E7o");
		lblServio.setBounds(9, 134, 35, 14);
		getContentPane().add(lblServio);
		
		servico = new JTextField();
		servico.setBounds(10, 150, 510, 20);
		getContentPane().add(servico);
		servico.setColumns(10);
		
		lblValorMensal = new JLabel("Valor Mensal");
		lblValorMensal.setBounds(9, 176, 60, 14);
		getContentPane().add(lblValorMensal);
		
		vlrMensal = new JMoneyField();
		vlrMensal.setHorizontalAlignment(SwingConstants.RIGHT);
		vlrMensal.setBounds(10, 194, 200, 20);
		getContentPane().add(vlrMensal);
		vlrMensal.setColumns(10);
		
		contrato = new JCheckBox("Contrato?");
		contrato.setBounds(320, 193, 73, 23);
		getContentPane().add(contrato);
		
		lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setBounds(9, 225, 63, 14);
		getContentPane().add(lblObservaes);
		
		observacoes = new JTextArea();
		observacoes.setBounds(10, 243, 510, 171);
		getContentPane().add(observacoes);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(imagens.getImagens(1));
		btnCancelar.setBounds(431, 425, 95, 25);
		getContentPane().add(btnCancelar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(imagens.getImagens(12));
		btnCadastrar.setBounds(320, 425, 101, 25);
		btnCadastrar.addActionListener(this);
		getContentPane().add(btnCadastrar);
		
		getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(
						new Component[]{nome,     endereco,    cmbxUF,       cmbxCidade, 
										telefone, celular,     servico,      vlrMensal, 
										contrato, observacoes, btnCadastrar, btnCancelar}
						)
				);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Clientes");
		setSize(546, 488);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imagens.getImagens(0));
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCadastrar){
			if(!nome.getText().isEmpty()){
				if(!endereco.getText().isEmpty()){
					if(cmbxCidade.isEnabled() && !cmbxCidade.getSelectedItem().toString().equals("------")){
						if(!(telefone.getText().equals("(  )     -    ") && celular.getText().equals("(  )     -    "))){
							if(!servico.getText().isEmpty()){
								if(!vlrMensal.getText().equals("0,00")){
									Cliente novo = new Cliente();
									novo.setNome(nome.getText());
									novo.setEndereco(endereco.getText());
									novo.setUf(estadoDao.carregaId(cmbxUF.getSelectedItem().toString()));
									novo.setCidade(cidadeDao.carregaId(cmbxCidade.getSelectedItem().toString()));
									novo.setTelefone(telefone.getText());
									novo.setCelular(celular.getText());
									vlrMensal.setText(vlrMensal.getText().replaceAll(".", ""));
									novo.setVlrMensal(Float.parseFloat(vlrMensal.getText().replaceAll(",", ".")));
									novo.setContrato(contrato.isSelected());
									novo.setServico(servico.getText());
									novo.setObs(observacoes.getText());
									ClienteDao clienteDao = new ClienteDao();
									clienteDao.cadastraCliente(novo);
									JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Cadastro realizado!", JOptionPane.INFORMATION_MESSAGE);
								} else{
									JOptionPane.showMessageDialog(null, "Informe o valor do serviço!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
								}
							} else{
								JOptionPane.showMessageDialog(null, "Informe o serviço!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
							}
						} else{
							JOptionPane.showMessageDialog(null, "Ao menos um tipo de contato é obrigatório!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
						}
					} else{
						JOptionPane.showMessageDialog(null, "Selecione uma cidade!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
					}
				} else{
					JOptionPane.showMessageDialog(null, "O endereço do cliente é obrigatório!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
				}
			} else{
				JOptionPane.showMessageDialog(null, "Nome do cliente é obrigatório!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == nome){
			valida.validaSomenteCom(valida.LETRAS + valida.ACENTOS + valida.ESPACO, e);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == cmbxUF){
			if(e.getStateChange() == ItemEvent.SELECTED){
				cidadeModel = manipulaCombo.carregaCombo(
						cmbxCidade, 
						cidadeDao.carregaCidade(
								estadoDao.carregaId(
										cmbxUF.getSelectedItem().toString()
										)
						)
				);
		        cmbxCidade.setModel(cidadeModel);
		        cmbxCidade.setEnabled(true);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		
	}
}
