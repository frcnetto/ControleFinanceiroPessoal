package br.estacio.poo.cfp.old;

import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.TrataLabel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class FrameBuscCliente extends JInternalFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4873163840865133437L;
	JTextField nome;
    JTextField endereco;
    JTextField cidade;
    JComboBox uf;
    JFormattedTextField telefone;
    JFormattedTextField celular;
    MaskFormatter format;
    JTextField servico;
    JTextField vlrMensal;
    JCheckBox contrato;
    JTextArea obs;
    JButton cadastrar;
    JButton cancelar;
    GridBagConstraints constraints;
    GridBagLayout layout = new GridBagLayout();
    Imagens imgs;
    TrataLabel mLabel;
    
    public FrameBuscCliente(){
        setTitle("Cadastro de clientes");
        setLayout(layout);
        constraints = new GridBagConstraints();
        imgs = new Imagens();
        nome = new JTextField(65);
        endereco = new JTextField(38);
        cidade = new JTextField();        
        uf = new JComboBox();
        
        try {
            format = new MaskFormatter("(##) ####-####");
        } catch (ParseException ex) {
            Logger.getLogger(FrameBuscCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        telefone = new JFormattedTextField(format);
        telefone.setColumns(10);
        celular = new JFormattedTextField(format);
        celular.setColumns(10);
        servico = new JTextField();
        vlrMensal = new JTextField();
        contrato = new JCheckBox("Contrato?");
        obs = new JTextArea();
        cadastrar = new JButton("Cadastrar");
        cadastrar.setIcon(imgs.getImagens(12));
        cancelar = new JButton("Cancelar");
        cancelar.setIcon(imgs.getImagens(1));
        mLabel = new TrataLabel();
        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(mLabel.criaJLabel("Nome:"), 0, 0, 1, 1);
        addComponent(nome, 1, 0, 4, 1);
        
        addComponent(mLabel.criaJLabel("Endereço:"), 2, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Cidade:"), 2, 2, 1, 1);
        addComponent(mLabel.criaJLabel("UF:"), 2, 3, 1, 1);
        
        addComponent(endereco, 3, 0, 2, 1);
        addComponent(cidade, 3, 2, 1, 1);
        addComponent(uf, 3, 3, 1, 1);
        
        addComponent(mLabel.criaJLabel("Telefone:"), 4, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Celular:"), 4, 2, 1, 1);
        
        addComponent(telefone, 5, 0, 2, 1);
        addComponent(celular, 5, 2, 2, 1);
        
        addComponent(mLabel.criaJLabel("Serviço:"), 6, 0, 1, 1);
        
        addComponent(servico, 7, 0, 4, 1);
        
        addComponent(mLabel.criaJLabel("Valor mensal:"), 8, 0, 1, 1);
        
        addComponent(vlrMensal, 9, 0, 2, 1);
        addComponent(contrato, 9, 2, 1, 1);
        
        addComponent(mLabel.criaJLabel("Observações:"), 10, 0, 1, 1);
        addComponent(obs, 11, 0, 4, 1);
        
        addComponent(cadastrar, 12, 2, 1, 1);
        addComponent(cancelar, 12, 3, 1, 1);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);       
        setSize(300, 400);
        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setFrameIcon(imgs.getImagens(0));
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
}
