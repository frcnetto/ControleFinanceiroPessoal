package br.estacio.poo.cfp.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class TrataPanel {
    public TrataPanel(){
    }
 
    public final JPanel criaPanel(Component a){
        JPanel p = new JPanel();
        p.add(a);
        return p;
    }
    
    public final JPanel criaPanel(Component a, Color c){
        JPanel p = new JPanel();
        p.add(a);
        p.setBackground(c);
        return p;
    }
    
    public final JPanel criaPanel(Component a, Component b){
        JPanel p = new JPanel();
        p.add(a);
        p.add(b);
        return p;
    }
    
    public final JPanel criaPanel(Component a, Component b, Color c){
        JPanel p = new JPanel();
        p.add(a);
        p.add(b);
        p.setBackground(c);
        return p;
    }
    
    public final JPanel criaPanel(Component a, Component b, Color c, Color f){
        JPanel p = new JPanel();
        p.add(a);
        p.add(b);
        p.setBackground(c);
        p.setForeground(f);
        return p;
    }
    
    public final JPanel criaPanel(Component a, Color c, LayoutManager l){
        JPanel p = new JPanel();
        p.setLayout(l);
        p.setBackground(c);
        p.add(a);
        return p;
    }
    
    public final JPanel criaPanel(Component a, LayoutManager l, String align){
        JPanel p = new JPanel();
        p.setLayout(l);
        p.add(a, align);
        return p;
    }
    
    public final JPanel criaPanel(Component a, Component b, Color c, LayoutManager l){
        JPanel p = new JPanel();
        p.setLayout(l);
        p.add(a);
        p.add(b);
        p.setBackground(c);
        return p;
    }
    
    
}