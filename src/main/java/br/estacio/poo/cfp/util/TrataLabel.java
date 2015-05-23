package br.estacio.poo.cfp.util;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JLabel;

public class TrataLabel {
    public TrataLabel(){
    }
    
    public JLabel criaJLabel(String texto){
        return new JLabel(texto);
    }
    
    public JLabel criaJLabel(String texto, Color foreground){
        JLabel l = new JLabel(texto);
        l.setForeground(foreground);
        return l;
    }
    
    public JLabel criaJLabel(String texto, Color foreground, Color background){
        JLabel l = new JLabel(texto);
        l.setForeground(foreground);
        l.setBackground(background);
        return l;
    }
    
    public JLabel criaJLabel(String texto, Color foreground, int alinhamento){
        JLabel l = new JLabel(texto, alinhamento);
        l.setForeground(foreground);
        return l;
    }
    
    public JLabel criaJLabel(Icon icone){
        return new JLabel(icone);
    }
    
    public JLabel criaJLabel(Icon icone, int alinhamento){
        return new JLabel(icone, alinhamento);
    }
    
    public JLabel criaJLabel(String texto, Icon icone, int alinhamento){
        return new JLabel(texto, icone, alinhamento);
    }
}