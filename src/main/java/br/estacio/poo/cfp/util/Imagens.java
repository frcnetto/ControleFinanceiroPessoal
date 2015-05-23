package br.estacio.poo.cfp.util;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Imagens {
    private final ImageIcon[] imagens = {
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/0cad.gif")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/1cancel.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/2entrar.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/3key.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/4logo.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/5money.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/6sair.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/7search.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/8sobre.png")),        
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/9system.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/10update.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/11user.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/12ok.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/13setaSimplesDireita.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/14setaDuplaDireita.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/15setaSimplesEsquerda.png")),
        new ImageIcon(getClass().getResource("/br/estacio/poo/cfp/frames/imagens/16setaDuplaEsquerda.png"))
    };
    private final JLabel[] jlImagens = {
        new JLabel(imagens[0]),
        new JLabel(imagens[1]),
        new JLabel(imagens[2]),
        new JLabel(imagens[3]),
        new JLabel(imagens[4]),
        new JLabel(imagens[5]),
        new JLabel(imagens[6]),
        new JLabel(imagens[7]),
        new JLabel(imagens[8]),
        new JLabel(imagens[9]),
        new JLabel(imagens[10]),
        new JLabel(imagens[11]),
        new JLabel(imagens[12]),
        new JLabel(imagens[13]),
        new JLabel(imagens[14]),
        new JLabel(imagens[15]),
        new JLabel(imagens[16])
    };
    
    public Imagens(){
    }
    
    public ImageIcon getImagens(int i) {
        return imagens[i];
    }

    public JLabel getJlImagens(int i) {
        return jlImagens[i];
    }
}