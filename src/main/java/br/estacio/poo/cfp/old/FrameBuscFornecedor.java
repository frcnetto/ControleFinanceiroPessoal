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
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class FrameBuscFornecedor extends JInternalFrame{
    JTextField nome;
    JTextField endereco;
    JTextField cidade;
    JComboBox uf;
    JFormattedTextField telefone;
    JFormattedTextField celular;
    MaskFormatter format;
    JTextField tipo;
    JTextField valor;
    JTextArea obs;
    JTextArea desc;
    JButton cadastrar;
    JButton cancelar;
    GridBagConstraints constraints;
    GridBagLayout layout = new GridBagLayout();
    Imagens imgs;
    TrataLabel mLabel;
    public FrameBuscFornecedor(){
        super("Cadastro de Fornecedores");
        setLayout(layout);
        constraints = new GridBagConstraints();
        
        nome = new JTextField(65);
        endereco = new JTextField(38);
        cidade = new JTextField();
        uf = new JComboBox();
        
        try {
            format = new MaskFormatter("(##) ####-####");
        } catch (ParseException ex) {
            Logger.getLogger(FrameCadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        telefone = new JFormattedTextField(format);
        telefone.setColumns(10);
        celular = new JFormattedTextField(format);
        celular.setColumns(10);
        tipo = new JTextField();
        valor = new JTextField();
        desc = new JTextArea(10, 20);
        obs = new JTextArea(10, 20);
        cadastrar = new JButton("Cadastrar");
        cancelar = new JButton("Cancelar");
        imgs = new Imagens();
        mLabel = new TrataLabel();
        
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
        
        addComponent(mLabel.criaJLabel("Tipo:"), 6, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Valor:"), 6, 2, 1, 1);
        
        addComponent(tipo, 7, 0, 2, 1);
        addComponent(valor, 7, 2, 2, 1);
             
        addComponent(mLabel.criaJLabel("Descrição:"), 8, 0, 1, 1);
        addComponent(mLabel.criaJLabel("Observações:"), 10, 0, 1, 1);
        
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(desc, 9, 0, 4, 1);
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
