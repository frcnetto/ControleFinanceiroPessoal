package br.estacio.poo.cfp.util;

import javax.swing.JInternalFrame;

public class TrataJInternalFrame {
	
	public TrataJInternalFrame(){}
	
	public boolean buscaFrame(JInternalFrame frame, JInternalFrame[] frames){    	
        for (JInternalFrame f : frames) {
            if(f.getClass().equals(frame.getClass()))
                return true;
        }        
        return false;
    }
}
