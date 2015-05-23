package br.estacio.poo.cfp.util;

import javax.swing.JFrame;

public class Util {
    public Util(){
    }
    
    public void tremer (JFrame jframe){
        try{
            int originalX = jframe.getLocation().x;
            int originalY = jframe.getLocation().y;
            long sleepTime = 25;

            for(int i =0; i <=2 ; i++){
                jframe.setLocation(originalX + 5, originalY );
                Thread.sleep(sleepTime);
                jframe.setLocation(originalX + 5, originalY);
                Thread.sleep(sleepTime);
                jframe.setLocation(originalX, originalY + 5);
                Thread.sleep(sleepTime);
                jframe.setLocation(originalX, originalY + 5);
                Thread.sleep(sleepTime);
                jframe.setLocation(originalX - 5, originalY);
                Thread.sleep(sleepTime);
                jframe.setLocation(originalX - 5, originalY);
                Thread.sleep(sleepTime);
                jframe.setLocation(originalX, originalY - 5);
                Thread.sleep(sleepTime);
                jframe.setLocation(originalX, originalY - 5);
                Thread.sleep(sleepTime);
            }

            jframe.setLocation(originalX, originalY);

        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
}