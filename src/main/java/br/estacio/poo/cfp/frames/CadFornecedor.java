package br.estacio.poo.cfp.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import br.estacio.poo.cfp.dao.EstadoDao;
import br.estacio.poo.cfp.dao.FornecedorDao;
import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.JMoneyField;
import br.estacio.poo.cfp.util.TrataComboBox;
import br.estacio.poo.cfp.util.ValidaCampos;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingConstants;

public class CadFornecedor extends JInternalFrame implements ItemListener, ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;

    private Imagens imagens;
    private JLabel lblNome;
    private JTextField nome;
    private JComboBox<String> cmbxUF;
    private ComboBoxModel<String> ufModel;
    private EstadoDao estadoDao = new EstadoDao();
    private JTextField endereco;
    private JLabel lblEndereo;
    private JLabel lblUf;
    private JLabel lblCidade;
    private JComboBox<String> cmbxCidade;
    private ComboBoxModel<String> cidadeModel;
    private CidadeDao cidadeDao = new CidadeDao();
    private JLabel lblTelefone;
    private JLabel lblCelular;
    private MaskFormatter mfTelefone;
    private JFormattedTextField telefone;
    private JFormattedTextField celular;
    private JLabel lblObservaes;
    private JTextArea observacoes;
    private JButton btnCancelar;
    private JButton btnCadastrar;
    private JTextField tipo;
    private JMoneyField valor;
    private JLabel lblValor;
    private JLabel lblTipo;
    private TrataComboBox manipulaCombo = new TrataComboBox();
    private ValidaCampos valida = new ValidaCampos();
    private JLabel lblDescrio;
    private JTextArea descricao;

	public CadFornecedor() {
		getContentPane().setLayout(null);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 27, 14);
		getContentPane().add(lblNome);
		
		nome = new JTextField("");
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
		
		endereco = new JTextField("");
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
            Logger.getLogger(CadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		telefone = new JFormattedTextField(mfTelefone);
		telefone.setBounds(10, 110, 200, 20);
		getContentPane().add(telefone);
		
		celular = new JFormattedTextField(mfTelefone);
		celular.setBounds(320, 110, 200, 20);
		getContentPane().add(celular);
		
		lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setBounds(10, 299, 63, 14);
		getContentPane().add(lblObservaes);
		
		observacoes = new JTextArea("");
		observacoes.setBounds(10, 316, 510, 105);
		getContentPane().add(observacoes);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(431, 425, 89, 23);
		getContentPane().add(btnCancelar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(332, 425, 89, 23);
		btnCadastrar.addActionListener(this);
		getContentPane().add(btnCadastrar);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 135, 20, 14);
		getContentPane().add(lblTipo);
		
		lblValor = new JLabel("Valor");
		lblValor.setBounds(320, 135, 46, 14);
		getContentPane().add(lblValor);
		
		tipo = new JTextField("");
		tipo.setBounds(10, 152, 200, 20);
		getContentPane().add(tipo);
		tipo.setColumns(10);
		
		valor = new JMoneyField();
		valor.setHorizontalAlignment(SwingConstants.RIGHT);
		valor.setBounds(320, 152, 200, 20);
		getContentPane().add(valor);
		valor.setColumns(10);
		
		lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(9, 177, 46, 14);
		getContentPane().add(lblDescrio);
		
		descricao = new JTextArea("");
		descricao.setBounds(10, 194, 510, 105);
		getContentPane().add(descricao);
		
		imagens = new Imagens();		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Cadastro de Fornecedores");
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCadastrar){
			if(!nome.getText().isEmpty()){
				if(!endereco.getText().isEmpty()){
					if(cmbxCidade.isEnabled() && !cmbxCidade.getSelectedItem().toString().equals("------")){
						if(!(telefone.getText().equals("(  )     -    ") && celular.getText().equals("(  )     -    "))){
							if(!tipo.getText().isEmpty()){
								if(!valor.getText().equals("0,00")){
									if(!descricao.getText().isEmpty()){
										Fornecedor novo = new Fornecedor();
										
										novo.setNome(nome.getText());
										novo.setEndereco(endereco.getText());
										novo.setUf(estadoDao.carregaId(cmbxUF.getSelectedItem().toString()));
										novo.setCidade(cidadeDao.carregaId(cmbxCidade.getSelectedItem().toString()));
										novo.setTelefone(telefone.getText());
										novo.setCelular(celular.getText());
										valor.setText(valor.getText().replaceAll(".", ""));
										novo.setValorl(Float.parseFloat(valor.getText().replaceAll(",", ".")));
										novo.setDesc(descricao.getText());
										novo.setTipo(tipo.getText());
										novo.setObs(observacoes.getText());
										
										FornecedorDao fornecedorDao = new FornecedorDao();
										fornecedorDao.cadastraFornecedor(novo);
										
										JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!", "Cadastro realizado!", JOptionPane.INFORMATION_MESSAGE);
										
										nome.setText("");
										endereco.setText("");
										cmbxCidade.setEnabled(false);;
										telefone.setText("");
										celular.setText("");
										valor.setText("");
										descricao.setText("");
										tipo.setText("");
										observacoes.setText("");
									} else{
										JOptionPane.showMessageDialog(null, "Informe uma descrição do fornecedor!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
									}
								} else{
									JOptionPane.showMessageDialog(null, "Informe o valor!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
								}
							} else{
								JOptionPane.showMessageDialog(null, "Informe o tipo!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
							}
						} else{
							JOptionPane.showMessageDialog(null, "Ao menos um tipo de contato é obrigatório!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
						}
					} else{
						JOptionPane.showMessageDialog(null, "Selecione uma cidade!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
					}
				} else{
					JOptionPane.showMessageDialog(null, "O endereço do endereco é obrigatório!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
				}
			} else{
				JOptionPane.showMessageDialog(null, "Nome do fornecedor é obrigatório!", "Houston, temos um problema!", JOptionPane.ERROR_MESSAGE);
			}
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
}
