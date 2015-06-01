package br.estacio.poo.cfp.frames;

import br.estacio.poo.cfp.util.Imagens;
import br.estacio.poo.cfp.util.TrataJInternalFrame;
import br.estacio.poo.cfp.util.TrataPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FramePrincipal extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5145420352202863880L;
	JLabel tituloLogo;
    JLabel titulo;
    JLabel rodape;
    ImageIcon logo;
    ImageIcon money;
    ImageIcon cad;
    ImageIcon search;
    ImageIcon system;
    ImageIcon sob;
    Color fundo;
    JDesktopPane dsktPane = new JDesktopPane();
    
    JMenuBar menu;
    
    JMenu cadastrar;
    JMenuItem cadPagamento;
    JMenuItem cadRecebimento;
    JMenuItem cadFornecedor;
    JMenuItem cadCliente;
    
    JMenu busca;
    JMenuItem buscPagamento;
    JMenuItem buscRecebimento;
    JMenuItem buscFornecedor;
    JMenuItem buscCliente;    
    
    JMenu excluir;
    JMenuItem excPagamento;
    JMenuItem excRecebimento;
    JMenuItem excFornecedor;
    JMenuItem excCliente;
    
    JMenuItem sobre;
    JMenuItem altSenha;
    JMenuItem logof;
    JMenuItem sair;
    
    JMenu sistema;
    JMenuItem sobSobre;
    
    Imagens imgs = new Imagens();
    TrataPanel manipulaPanel = new TrataPanel();
    TrataJInternalFrame trataJInternalFrame = new TrataJInternalFrame();
    
    CadPagamento cPagamento = new CadPagamento(dsktPane);
    CadRecebimento cRecebimento = new CadRecebimento(dsktPane);
    CadFornecedor cFornecedor = new CadFornecedor();
    CadCliente cCliente = new CadCliente();
    BuscPagamento bPagamento = new BuscPagamento(dsktPane);
    BuscRecebimento bRecebimento = new BuscRecebimento();
    BuscFornecedor bFornecedor = new BuscFornecedor();
    BuscCliente bCliente = new BuscCliente();
    FrameRecSenha senha = new FrameRecSenha();
    
    public FramePrincipal(){
        logo = imgs.getImagens(4);
        money = imgs.getImagens(5);
        cad = imgs.getImagens(0);
        search = imgs.getImagens(7);
        system = imgs.getImagens(9);
        sob = imgs.getImagens(8);
        fundo = new Color(7, 35, 66);
        
        tituloLogo = new JLabel(logo);
        tituloLogo.setHorizontalAlignment(JLabel.CENTER);        
        
        titulo = new JLabel("Controle Financeiro Pessoal", JLabel.CENTER);
        titulo.setFont(new Font("arial",Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        
        rodape = new JLabel("2015 - Netto Cavalcante");
        rodape.setFont(new Font("Serif", Font.PLAIN, 12));
        rodape.setHorizontalAlignment(JLabel.CENTER);
        
        dsktPane.add(tituloLogo);
        
        menu = new JMenuBar();
        
        cadastrar = new JMenu("Cadastrar");
        cadastrar.setMnemonic('C');
        cadastrar.setIcon(cad);
        cadPagamento = new JMenuItem("Pagamento");
        cadRecebimento = new JMenuItem("Recebimento");
        cadFornecedor = new JMenuItem("Fornecedor");
        cadCliente = new JMenuItem("Cliente");
        
        busca = new JMenu("Buscar");
        busca.setIcon(search);
        busca.setMnemonic('B');
        buscPagamento = new JMenuItem("Pagamento");
        buscRecebimento = new JMenuItem("Recebimento");
        buscFornecedor = new JMenuItem("Fornecedor");
        buscCliente = new JMenuItem("Cliente");
        
        sistema = new JMenu("Sistema");
        sistema.setIcon(system);
        sistema.setMnemonic('S');
        altSenha = new JMenuItem("Alterar senha");
        logof = new JMenuItem("Logof");
        sair = new JMenuItem("Sair do sistema");
        
        sobre = new JMenuItem("Sobre");
        sobre.setIcon(sob);
        
        cadastrar.add(cadPagamento);
        cadastrar.add(cadRecebimento);
        cadastrar.add(cadFornecedor);
        cadastrar.add(cadCliente);
        
        busca.add(buscPagamento);
        busca.add(buscRecebimento);
        busca.add(buscFornecedor);
        busca.add(buscCliente);
        busca.add(altSenha);
        
        sistema.add(altSenha);
        sistema.add(logof);
        sistema.add(sair);
        
        menu.add(cadastrar);
        menu.add(busca);
        menu.add(sistema);
        menu.add(sobre);
        
        setTitle("Controle Financeiro Pessoal - C.F.P");
        setLayout(new BorderLayout());
        setBackground(fundo);
        
        cadCliente.addActionListener(this);
        cadFornecedor.addActionListener(this);
        cadPagamento.addActionListener(this);
        cadRecebimento.addActionListener(this);
        buscCliente.addActionListener(this);
        buscFornecedor.addActionListener(this);
        buscPagamento.addActionListener(this);
        buscRecebimento.addActionListener(this);
        altSenha.addActionListener(this);
        sobre.addActionListener(this);
        sair.addActionListener(this);
        logof.addActionListener(this);
        
        setJMenuBar(menu);
        add(dsktPane, BorderLayout.CENTER);
        add(manipulaPanel.criaPanel(rodape, Color.LIGHT_GRAY, new FlowLayout()), BorderLayout.SOUTH);
        
        setIconImage(money.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dsktPane.setBackground(new Color(7, 35, 66));
        setSize(300, 410);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        JInternalFrame[] frames = dsktPane.getAllFrames();
        
        if(e.getSource() == cadPagamento){
            if(!trataJInternalFrame.buscaFrame(cPagamento, frames)){
            	cPagamento = new CadPagamento(dsktPane);
                dsktPane.add(cPagamento);
                try {
                    cPagamento.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                cPagamento.moveToFront();
            } else{
                
            }            
        } else if(e.getSource() == cadRecebimento){
            if(!trataJInternalFrame.buscaFrame(cRecebimento, frames)){
            	cRecebimento = new CadRecebimento(dsktPane);
                dsktPane.add(cRecebimento);
                try {
                    cRecebimento.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                cRecebimento.moveToFront();
            } else{
                
            }
        } else if(e.getSource() == cadFornecedor){
            if(!trataJInternalFrame.buscaFrame(cFornecedor, frames)){     
            	cFornecedor = new CadFornecedor();
                dsktPane.add(cFornecedor);
                try {
                    cFornecedor.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                cFornecedor.moveToFront();
            } else{
                
            }
        } else if(e.getSource() == cadCliente){
            if(!trataJInternalFrame.buscaFrame(cCliente, frames)){   
            	cCliente = new CadCliente();
                dsktPane.add(cCliente);
                try {
                    cCliente.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                cCliente.moveToFront();
            } else{
                
            }
            
        } else if(e.getSource() == buscPagamento){
            if(!trataJInternalFrame.buscaFrame(bPagamento, frames)){       
            	bPagamento = new BuscPagamento(dsktPane);
                dsktPane.add(bPagamento);
                try {
                    bPagamento.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                bPagamento.moveToFront();
            } else{
                
            }
        } else if(e.getSource() == buscRecebimento){
            if(!trataJInternalFrame.buscaFrame(bRecebimento, frames)){  
            	bRecebimento = new BuscRecebimento();
                dsktPane.add(bRecebimento);
                try {
                    bRecebimento.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                bRecebimento.moveToFront();
            } else{
                
            }
        } else if(e.getSource() == buscFornecedor){
            if(!trataJInternalFrame.buscaFrame(bFornecedor, frames)){
            	bFornecedor = new BuscFornecedor();
                dsktPane.add(bFornecedor);
                try {
                    bFornecedor.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                bFornecedor.moveToFront();
            } else{
                
            }
        } else if(e.getSource() == buscCliente){
            if(!trataJInternalFrame.buscaFrame(bCliente, frames)){    
            	bCliente = new BuscCliente();
                dsktPane.add(bCliente);
                try {
                    bCliente.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                bCliente.moveToFront();
            } else{
                
            }            
        } else if(e.getSource() == altSenha){
            if(!trataJInternalFrame.buscaFrame(senha, frames)){
            	senha = new FrameRecSenha();
                dsktPane.add(senha);
                try {
                    senha.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                senha.moveToFront();
            } else{
                JOptionPane.showMessageDialog(null, "VocÍ j· est· com esta janela aberta!", "Alerta!", JOptionPane.ERROR_MESSAGE);
            }
        } else if(e.getSource() == logof){
            if(JOptionPane.
                    showConfirmDialog(
                            null, 
                            "Deseja fazer logof do sistema?", 
                            "Logof?", 
                            JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION){
                new FrameLogin();
                this.dispose();
            }    
        } else if(e.getSource()== sobre){
            JOptionPane.showMessageDialog(
                    null, 
                    "Sistema de Controle financeiro Pessoal\n"
                    + "Desenvolvido por Netto Cavalcalcante na \n"
                    + "disciplina de Programa√ß√£o Orientada a \n"
                    + "Objetos da Faculdade Est√°cio FIC durante \n"
                    + "o semestre de 2015.1", 
                    "Sobre", 
                    JOptionPane.INFORMATION_MESSAGE);
        } else if(e.getSource() == sair){
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
}