package br.estacio.poo.cfp.frames;

import br.estacio.poo.cfp.dao.UsuarioDao;
import br.estacio.poo.cfp.entidades.Usuario;
import br.estacio.poo.cfp.persistence.Conexao;
import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.TrataLabel;
import br.estacio.poo.cfp.util.TrataPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class FrameRecSenha extends JInternalFrame implements ActionListener{  
    /**
	 * 
	 */
	private static final long serialVersionUID = -591125900403140141L;
	JPasswordField antiga;
    JPasswordField nova;
    JPasswordField confirma;
    JButton alterar;
    JButton cancelar;
    JLabel titulo;
    JPanel centro;
    Color fundo = new Color(7, 35, 66);
    Imagens imgs = new Imagens();
    TrataPanel mPanel = new TrataPanel();
    TrataLabel mLabel = new TrataLabel();
    Usuario usuario = new Usuario(); 
    UsuarioDao usuarioDao = new UsuarioDao();
    Conexao conexao = new Conexao();
    
    public FrameRecSenha(){
        super("Recadastrar senha...");
        setLayout(new BorderLayout());
        
        antiga = new JPasswordField(20);
        antiga.setToolTipText("Senha atual...");
        nova = new JPasswordField(20);
        nova.setToolTipText("Senha nova...");
        confirma = new JPasswordField(20);
        confirma.setToolTipText("Confirmar nova...");
        alterar = new JButton("Alterar");
        alterar.setIcon(imgs.getImagens(12));
        cancelar = new JButton("Cancelar");
        cancelar.setIcon(imgs.getImagens(1));
        titulo = new JLabel("Controle Financeiro Pessoal", JLabel.CENTER);
        titulo.setFont(new Font("arial",Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        
        centro = new JPanel();
        centro.setLayout(new GridLayout(5, 1, 5, 5));
        centro.add(titulo);
        centro.add(mPanel.criaPanel(mLabel.criaJLabel("Senha atual:", Color.white), antiga, fundo, Color.WHITE));
        centro.add(mPanel.criaPanel(mLabel.criaJLabel("Nova senha:", Color.white), nova, fundo, Color.WHITE));
        centro.add(mPanel.criaPanel(mLabel.criaJLabel("Confirmar senha:", Color.white), confirma, fundo, Color.WHITE));
        centro.add(mPanel.criaPanel(alterar, cancelar, fundo));
        centro.setBackground(fundo);
        
        alterar.addActionListener(this);
        cancelar.addActionListener(this);
        
        add(imgs.getJlImagens(4), BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(350, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        getContentPane().setBackground(fundo);
        moveToFront();
        setVisible(true);
    }
    
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
        if(e.getSource() == alterar){
            //if(nova.getText().isEmpty() || antiga.getText().isEmpty() || confirma.getText().isEmpty()){
            	if(usuarioDao.validaSenha(antiga.getText())){
            		if(nova.getText().equals(confirma.getText())){
	            		usuarioDao.atualizaSenha(nova.getText(), antiga.getText());
	            		JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso!","Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            		} else{
                		JOptionPane.showMessageDialog(null, "A nova senha e a confirmação não conferem!","Erro!", JOptionPane.ERROR_MESSAGE);
            		}
        		} else{
        			JOptionPane.showMessageDialog(null, "Senha antiga incorreta!","Erro!", JOptionPane.ERROR_MESSAGE);
            	}
//            } else{
//            	JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!","Erro!", JOptionPane.ERROR_MESSAGE);
//            }
        } else if(e.getSource() == cancelar){
            if(JOptionPane.
                    showConfirmDialog(
                            null, 
                            "Deseja cancelar?", 
                            "Cancelar?", 
                            JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION){
                try {
                    this.setClosed(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FrameRecSenha.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}