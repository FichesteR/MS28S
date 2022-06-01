/*
 * Copyright (C) 2002-2011  Frédéric Bergeron (fbergeron@users.sourceforge.net)
 *                          and other contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package com.fbergeron.solitaire;

import com.fbergeron.util.ImagePanel;
import com.fbergeron.util.WindowManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FrameCongratulations extends JFrame {
	protected Solitaire solitaire;
    // Used for addNotify check.
    boolean fComponentsAdjusted = false;
	
	public class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           solitaire.newGame();
           solitaire.zerar();
           setVisible(false);
           
        }
    }
	public class closeGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
	
    public FrameCongratulations(long t1, long t2, int counter, Solitaire solitaire)
    {
    	this.solitaire = solitaire;
        setSize(600,300);
        setVisible(false);
        setResizable(false);

        JPanel panelImage = new JPanel();
        panelImage.setOpaque(false);

        JLabel labelCongratulations = new JLabel();
        labelCongratulations.setVerticalAlignment(SwingConstants.CENTER);
        labelCongratulations.setFont(new Font("Dialog", Font.BOLD, 24));
        labelCongratulations.setHorizontalTextPosition(JLabel.CENTER);

        panelImage.add(labelCongratulations);

        JPanel panelBottom = new JPanel();

        JButton btNewGame = new JButton("Novo Jogo");
        btNewGame.setSize(160,25);
        btNewGame.addActionListener( new NewGameListener() );
        JButton btQuit = new JButton("Sair");
        btQuit.setSize(160,25);
        btQuit.addActionListener( new closeGameListener() );

        panelBottom.add(btNewGame, BorderLayout.EAST);
        panelBottom.add(btQuit, BorderLayout.WEST);

        panelImage.add(new ImagePanel(loadImage("img/com/fbergeron/util/vitoria.jpg")));
        this.add(panelImage);
        this.add(panelBottom, BorderLayout.SOUTH);
        //this.add(labelCongratulations);

        long minutos = calculaTempo(t1, t2);
        minutos = minutos / 60;
        long segundos = calculaTempo(t1, t2);
        segundos = segundos % 60;

        String agora = " " + "Tempo: ";

        if (minutos<60) {
        	if(segundos<10) {
        		agora += "0" + minutos + ":0" + segundos;
        	}else {
        		agora += "0" + minutos + ":" + segundos;
        	}
        }else {
        	if(segundos<10) {
        		agora += "" + minutos + ":0" + segundos;
        	}else {
        		agora += "" + minutos + ":" + segundos;
        	}
        }

        agora+="!" + " ";
        String movimentos = "Movimento: " + counter + "!";
        
        labelCongratulations.setText(Solitaire.resBundle.getString( "Você Venceu" )+"\n" + agora +"\n" + movimentos);

        setTitle( (String)Solitaire.resBundle.getString( "Parabéns" ) );
        addWindowListener( new WindowManager( this, WindowManager.HIDE_ON_CLOSE ) );
    }

    /**
     * Shows or hides the component depending on the boolean flag b.
     * @param b  if true, show the component; otherwise, hide the component.
     * @see java.awt.Component#isVisible
     */
    
    public void setVisible(boolean b)
    {
        Dimension scrSize = getToolkit().getScreenSize();
        Dimension size = getSize();
        if(b)
        { 
            setLocation( (scrSize.width - size.width) / 2, (scrSize.height - size.height) / 2 );
        }
        super.setVisible(b);
    }
    
    public long calculaTempo(long t1, long t2) {
    	long resultado = t2-t1;
    	resultado = resultado/1000;
    	return resultado;
    }

    public void addNotify()
    {
        // Record the size of the window prior to calling parents addNotify.
        Dimension d = getSize();

        super.addNotify();

        if (fComponentsAdjusted)
            return;

        // Adjust components according to the insets
        setSize(getInsets().left + getInsets().right + d.width, getInsets().top + getInsets().bottom + d.height);
        Component components[] = getComponents();
        for (int i = 0; i < components.length; i++)
        {
            Point p = components[i].getLocation();
            p.translate(getInsets().left, getInsets().top);
            components[i].setLocation(p);
        }
        fComponentsAdjusted = true;
    }

    public Image loadImage(String imagePath){
        try {
            File imageFile = new File(imagePath);

            BufferedImage bufferedImage = ImageIO.read(imageFile);
            return bufferedImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException e){
            return null;
        }
    }

}


