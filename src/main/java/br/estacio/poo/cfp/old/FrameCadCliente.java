package br.estacio.poo.cfp.old;

import br.estacio.poo.cfp.dao.CidadeDao;
import br.estacio.poo.cfp.dao.ClienteDao;
import br.estacio.poo.cfp.dao.EstadoDao;
import br.estacio.poo.cfp.entidades.Cliente;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class FrameCadCliente extends JInternalFrame implements ItemListener, KeyListener, ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7879497380183570742L;
	JFormattedTextField nome;
    JTextField endereco;
    JComboBox<String> cidade;
    JComboBox<String> uf;
    ComboBoxModel<String> ufModel;
    ComboBoxModel<String> cidadeModel;
    JFormattedTextField telefone;
    JFormattedTextField celular;
    MaskFormatter mfTelefone;
    JTextField servico;
    JMoneyField vlrMensal;
    JCheckBox contrato;
    JTextArea obs;
    JButton cadastrar;
    JButton cancelar;
    GridBagConstraints constraints;
    GridBagLayout layout = new GridBagLayout();
    Imagens imgs;
    TrataLabel mLabel;
    EstadoDao estadoDao = new EstadoDao();
    CidadeDao cidadeDao = new CidadeDao();
    TrataComboBox manipulaCombo = new TrataComboBox();
    ValidaCampos valida = new ValidaCampos();
    
    public FrameCadCliente(){
        super("Cadastro de clientes");        
        constraints = new GridBagConstraints();
        nome = new JFormattedTextField();
        endereco = new JTextField(38);
        cidade = new JComboBox<String>();
        cidade.setEnabled(false);
        uf = new JComboBox<String>();
        ufModel = manipulaCombo.carregaCombo(uf, estadoDao.carregaUf());
        uf.setModel(ufModel);
        
        try {
            mfTelefone = new MaskFormatter("(##) ####-####");
        } catch (ParseException ex) {
            Logger.getLogger(FrameCadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        telefone = new JFormattedTextField(mfTelefone);
        celular = new JFormattedTextField(mfTelefone);
        servico = new JTextField();
        vlrMensal = new JMoneyField();
        contrato = new JCheckBox("Contrato?");
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
        addComponent(nome, 1, 0, 4, 1);
        
        addComponent(mLabel.criaJLabel("Endereço:"), 2, 0, 1, 1);
        addComponent(mLabel.criaJLabel("UF:"), 2, 2, 1, 1);
        addComponent(mLabel.criaJLabel("Cidade:"), 2, 3, 1, 1);
        
        addComponent(endereco, 3, 0, 2, 1);
        addComponent(uf, 3, 2, 1, 1);
        addComponent(cidade, 3, 3, 1, 1);
        
        addComponent(mLabel.criaJLabel("Telefone:"), 4, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Celular:"), 4, 2, 1, 1);
        
        addComponent(telefone, 5, 0, 2, 1);
        addComponent(celular, 5, 2, 2, 1);
        
        addComponent(mLabel.criaJLabel("Serviço:"), 6, 0, 1, 1);
        
        addComponent(servico, 7, 0, 4, 1);
        
        addComponent(mLabel.criaJLabel("Valor mensal:"), 8, 0, 1, 1);
        
        addComponent(vlrMensal, 9, 0, 1, 1);
        addComponent(contrato, 9, 2, 1, 1);
        
        addComponent(mLabel.criaJLabel("Observações:"), 10, 0, 1, 1);
        addComponent(obs, 11, 0, 4, 1);
        
        constraints.fill = GridBagConstraints.EAST;
        addComponent(cadastrar, 12, 2, 1, 1);
        constraints.fill = GridBagConstraints.WEST;
        addComponent(cancelar, 12, 3, 1, 1);
        
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

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		if(e.getSource() == nome){
			valida.validaSomenteCom(valida.LETRAS + valida.ACENTOS + valida.ESPACO, e);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cadastrar){
			if(!nome.getText().isEmpty()){
				if(!endereco.getText().isEmpty()){
					if(cidade.isEnabled() && !cidade.getSelectedItem().toString().equals("------")){
						if(!(telefone.getText().equals("(  )     -    ") && celular.getText().equals("(  )     -    "))){
							if(!servico.getText().isEmpty()){
								if(!vlrMensal.getText().equals("0,00")){
									Cliente novo = new Cliente();
									novo.setNome(nome.getText());
									novo.setEndereco(endereco.getText());
									novo.setUf(estadoDao.carregaId(uf.getSelectedItem().toString()));
									novo.setCidade(cidadeDao.carregaId(cidade.getSelectedItem().toString()));
									novo.setTelefone(telefone.getText());
									novo.setCelular(celular.getText());
									vlrMensal.setText(vlrMensal.getText().replaceAll(".", ""));
									novo.setVlrMensal(Float.parseFloat(vlrMensal.getText().replaceAll(",", ".")));
									novo.setContrato(contrato.isSelected());
									novo.setServico(servico.getText());
									novo.setObs(obs.getText());
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
}
