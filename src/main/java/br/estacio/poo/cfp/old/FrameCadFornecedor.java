package br.estacio.poo.cfp.old;

import br.estacio.poo.cfp.dao.CidadeDao;
import br.estacio.poo.cfp.dao.EstadoDao;
import br.estacio.poo.cfp.dao.FornecedorDao;
import br.estacio.poo.cfp.entidades.Fornecedor;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.JMoneyField;
import br.estacio.poo.cfp.util.TrataComboBox;
import br.estacio.poo.cfp.util.TrataLabel;
import br.estacio.poo.cfp.util.ValidaCampos;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class FrameCadFornecedor extends JInternalFrame implements ItemListener, ActionListener, KeyListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3129385081204415417L;
	JFormattedTextField nome;
    JTextField endereco;
    JComboBox<String> cidade;
    JComboBox<String> uf;
    ComboBoxModel<String> ufModel;
    ComboBoxModel<String> cidadeModel;
    JFormattedTextField telefone;
    JFormattedTextField celular;
    MaskFormatter format;
    JTextField tipo;
    JMoneyField valor;
    JTextArea obs;
    JTextArea desc;
    JButton cadastrar;
    JButton cancelar;
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();
    Imagens imgs;
    TrataLabel mLabel;
    EstadoDao estadoDao = new EstadoDao();
    CidadeDao cidadeDao = new CidadeDao();
    TrataComboBox manipulaCombo = new TrataComboBox();
    ValidaCampos valida = new ValidaCampos();
    
    public FrameCadFornecedor(){
        super("Cadastro de Fornecedores");
        imgs = new Imagens();
        nome = new JFormattedTextField();
        endereco = new JTextField(38);
        cidade = new JComboBox<String>();
        cidade.setEnabled(false);
        uf = new JComboBox<String>();
        ufModel = manipulaCombo.carregaCombo(uf, estadoDao.carregaUf());
        uf.setModel(ufModel);
        
        try {
            format = new MaskFormatter("(##) ####-####");
        } catch (ParseException ex) {
            Logger.getLogger(FrameCadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        telefone = new JFormattedTextField(format);
        celular = new JFormattedTextField(format);
        tipo = new JTextField(50);
        valor = new JMoneyField();
        desc = new JTextArea(15, 50);
        obs = new JTextArea(15, 50);
        cadastrar = new JButton("Cadastrar");        
        cancelar = new JButton("Cancelar");
        imgs = new Imagens();
        cadastrar.setIcon(imgs.getImagens(12));
        cancelar.setIcon(imgs.getImagens(1));
        mLabel = new TrataLabel();
        
        nome.addKeyListener(this);
        uf.addItemListener(this);
        cadastrar.addActionListener(this);
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(mLabel.criaJLabel("Nome:"), 0, 0, 1, 1);
        addComponent(nome, 1, 0, 5, 1);
        
        addComponent(mLabel.criaJLabel("Endereço:"), 2, 0, 1, 1);        
        addComponent(mLabel.criaJLabel("UF:"), 2, 3, 1, 1);
        addComponent(mLabel.criaJLabel("Cidade:"), 2, 4, 1, 1);
        
        addComponent(endereco, 3, 0, 3, 1);
        addComponent(uf, 3, 3, 1, 1);
        addComponent(cidade, 3, 4, 1, 1);
        
        addComponent(mLabel.criaJLabel("Telefone:"), 4, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Celular:"), 4, 3, 1, 1);
        
        addComponent(telefone, 5, 0, 2, 1);
        addComponent(celular, 5, 3, 2, 1);
        
        addComponent(mLabel.criaJLabel("Tipo:"), 6, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Valor:"), 6, 3, 1, 1);
        
        addComponent(tipo, 7, 0, 2, 1);        
        addComponent(valor, 7, 3, 2, 1);
             
        addComponent(mLabel.criaJLabel("Descrição:"), 8, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Observações:"), 10, 0, 1, 1);
        
        addComponent(desc, 9, 0, 5, 1);
        addComponent(obs, 11, 0, 5, 1);
        
        constraints.fill = GridBagConstraints.EAST;
        addComponent(cadastrar, 12, 3, 1, 1);
        constraints.fill = GridBagConstraints.WEST;
        addComponent(cancelar, 12, 4, 1, 1);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);       
        setSize(300, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imgs.getImagens(0));
        setLayout(layout);
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

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == uf){
			if(e.getStateChange() == ItemEvent.SELECTED){
				cidadeModel = manipulaCombo.carregaCombo(
						cidade, 
						cidadeDao.carregaCidade(
								estadoDao.carregaId(
										uf.getSelectedItem().toString()
								)
						)
				);
		        cidade.setModel(cidadeModel);
		        cidade.setEnabled(true);
			}
		}		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cadastrar){
			if(!nome.getText().isEmpty()){
				if(!endereco.getText().isEmpty()){
					if(cidade.isEnabled() && !cidade.getSelectedItem().toString().equals("------")){
						if(!(telefone.getText().equals("(  )     -    ") && celular.getText().equals("(  )     -    "))){
							if(!tipo.getText().isEmpty()){
								if(!valor.getText().equals("0,00")){
									if(!desc.getText().isEmpty()){
										Fornecedor novo = new Fornecedor();
										
										novo.setNome(nome.getText());
										novo.setEndereco(endereco.getText());
										novo.setUf(estadoDao.carregaId(uf.getSelectedItem().toString()));
										novo.setCidade(cidadeDao.carregaId(cidade.getSelectedItem().toString()));
										novo.setTelefone(telefone.getText());
										novo.setCelular(celular.getText());
										valor.setText(valor.getText().replaceAll(".", ""));
										novo.setValorl(Float.parseFloat(valor.getText().replaceAll(",", ".")));
										novo.setDesc(desc.getText());
										novo.setTipo(tipo.getText());
										novo.setObs(obs.getText());
										
										FornecedorDao fornecedorDao = new FornecedorDao();
										fornecedorDao.cadastraFornecedor(novo);
										
										JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!", "Cadastro realizado!", JOptionPane.INFORMATION_MESSAGE);
										
										nome.setText("");
										endereco.setText("");
										uf.setSelectedIndex(0);
										cidade.setSelectedIndex(0);
										telefone.setText("");
										celular.setText("");
										desc.setText("");
										tipo.setText("");
										obs.setText("");
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

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		if(e.getSource() == nome){
			valida.validaSomenteCom(valida.LETRAS + valida.ACENTOS + valida.ESPACO, e);
		}		
	}   
}
