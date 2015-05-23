package br.estacio.poo.cfp.principal;

import br.estacio.poo.cfp.frames.FrameLogin;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ControleFinanceiroPessoal {
    public static void main(String[] args) { 
        try {    
              UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");    
        } catch (   ClassNotFoundException | InstantiationException | 
                    IllegalAccessException | UnsupportedLookAndFeelException e) {    
        }
        new FrameLogin();
    }
}