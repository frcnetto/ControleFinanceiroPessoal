package br.estacio.poo.cfp.util;

import java.awt.Component;

import javax.swing.JFrame;

public class ManipulaThreads {
    public ManipulaThreads(){
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
    
    public void tremer (Component component){
        try{
            int originalX = component.getLocation().x;
            int originalY = component.getLocation().y;
            long sleepTime = 25;

            for(int i =0; i <=2 ; i++){
                component.setLocation(originalX + 2, originalY );
                Thread.sleep(sleepTime);
                component.setLocation(originalX - 2, originalY);
                Thread.sleep(sleepTime);
                component.setLocation(originalX + 2, originalY );
                Thread.sleep(sleepTime);
                component.setLocation(originalX - 2, originalY);
                Thread.sleep(sleepTime);
                component.setLocation(originalX + 2, originalY );
                Thread.sleep(sleepTime);
                component.setLocation(originalX - 2, originalY);
                Thread.sleep(sleepTime);
                component.setLocation(originalX + 2, originalY );
                Thread.sleep(sleepTime);
                component.setLocation(originalX - 2, originalY);
                Thread.sleep(sleepTime);
                component.setLocation(originalX + 2, originalY );
                Thread.sleep(sleepTime);
                component.setLocation(originalX - 2, originalY);
                Thread.sleep(sleepTime);
            }

            component.setLocation(originalX, originalY);

        } catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
}