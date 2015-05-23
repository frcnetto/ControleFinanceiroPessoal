package br.estacio.poo.cfp.util;

import java.awt.event.KeyEvent;

public class ValidaCampos {
	public final String LETRAS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
	public final String ESPACO = " ";
	public final String ACENTOS = "ÁáÀàÃãÂâÉéÈèÊêÌìÍíÎîÒòÓóÕõÔôÙùÚúÛûÇç";
	public final String NUMEROS = "0987654321";
	
	public ValidaCampos(){
	}
	
	public void validaSomenteCom(String regra, KeyEvent e){
		if(!regra.contains(e.getKeyChar() + "")){
			e.consume();
		}
	}
	
	public void validaSomenteSem(String regra, KeyEvent e){
		if(regra.contains(e.getKeyChar() + "")){
			e.consume();
		}
	}
}
