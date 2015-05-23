package br.estacio.poo.cfp.frames;

import br.estacio.poo.cfp.persistence.Conexao;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.TrataPanel;
import br.estacio.poo.cfp.util.Util;
import br.estacio.poo.cfp.dao.UsuarioDao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrameLogin extends JFrame implements ActionListener, FocusListener{  
    /**
	 * 
	 */
	private static final long serialVersionUID = -6379022971117851324L;
	
	JTextField user;
    JPasswordField senha;
    JButton entrar;
    JButton sair;
    JLabel titulo;
    JLabel rodape;
    JLabel[] menssagens = {
        new JLabel(""),
        new JLabel("Capslock ativado!"),
        new JLabel("Senha errada!")
    };
    JPanel centro;
    
    Color fundo = new Color(7, 35, 66);
    
    Imagens imgs = new Imagens();
    TrataPanel manipulaPainel = new TrataPanel();
    Util util = new Util();
    UsuarioDao usuarioDao = new UsuarioDao();
    Conexao conexao = new Conexao();
    
    public FrameLogin(){
        super("Tela de acesso ao CFP");
        setIconImage(imgs.getImagens(5).getImage());
        setLayout(new BorderLayout());
        
        user = new JTextField("Usuário...", 20);
        user.setToolTipText("Usuário...");
        
        senha = new JPasswordField("", 20);
        senha.setToolTipText("Senha...");
        
        entrar = new JButton("Entrar");
        entrar.setIcon(imgs.getImagens(12));
        sair = new JButton("Sair");
        sair.setIcon(imgs.getImagens(1));
        
        titulo = new JLabel("Controle Financeiro Pessoal", JLabel.CENTER);
        titulo.setFont(new Font("arial",Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        
        rodape = new JLabel("2015 - Netto Cavalcante");
        rodape.setFont(new Font("Serif", Font.PLAIN, 12));
        rodape.setHorizontalAlignment(JLabel.CENTER);
        
        centro = new JPanel();
        centro.setLayout(new GridLayout(4, 1, 5, 5));
        centro.add(titulo);
        centro.add(manipulaPainel.criaPanel(imgs.getJlImagens(11), user, fundo, new FlowLayout()));
        centro.add(manipulaPainel.criaPanel(imgs.getJlImagens(3), senha, fundo, new FlowLayout()));
        centro.add(manipulaPainel.criaPanel(manipulaPainel.criaPanel(entrar, sair, fundo, new GridLayout(1, 2, 5, 5)), fundo));
        centro.setBackground(fundo);
        
        user.addFocusListener(this);
        entrar.addActionListener(this);
        sair.addActionListener(this);
        
        add(imgs.getJlImagens(4), BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(manipulaPainel.criaPanel(rodape, Color.LIGHT_GRAY), BorderLayout.SOUTH);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 400);
        setResizable(false);
        getContentPane().setBackground(fundo);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }   

    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == entrar){        	
            if(usuarioDao.validaLoginSenha(user.getText(), senha.getText())){
                new FramePrincipal();
                this.dispose();
            } else{
                util.tremer(this);
                JOptionPane.showMessageDialog(rootPane, "Usuário ou senha incorretos!", "Falha!", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getSource() == sair){
            if(JOptionPane.
                    showConfirmDialog(
                            null, 
                            "Deseja sair no programa?", 
                            "Sair?", 
                            JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION){                
                System.exit(0);
            }
        }
    }

    public void focusGained(FocusEvent e) {
        if(e.getSource() == user){            
            if(user.getText().contains("Usuário...")){
                user.setText("");
            }            
        }
    }

    public void focusLost(FocusEvent e) {
        if(e.getSource() == user){            
            if(user.getText().equals("") || (user.getText().contains("Usuário..."))){
                user.setText("Usuário...");
            }            
        }
    }
}