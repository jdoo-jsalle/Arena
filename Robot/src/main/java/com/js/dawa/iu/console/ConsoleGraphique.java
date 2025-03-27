package com.js.dawa.iu.console;

import java.util.List;

import javax.swing.JFrame;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.AreneProps;
import com.js.dawa.iu.arene.ObjetArene;
import com.js.dawa.ui.graphique.UIPanel;

public class ConsoleGraphique implements Console {
	
	
	
	JFrame frame;
	 
	AreneProps mProps;
	
	
	 
	int getIntProps (String pProps) {
		return 0;
	}
	
	
	public void update() {
		frame.repaint();
	}
	
	@Override
	public void init (Arene pArene) {
		mProps = pArene.getAreneProps();
		// Création de la fenêtre
        frame = new JFrame(mProps.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1500);
        
        // Création de la zone de texte
        //=> variable global      
        
        UIPanel lPanel = new UIPanel();
        lPanel.init(pArene);
    
        frame.add(lPanel);
       
       
        // Affichage de la fenêtre
        frame.setVisible(true);
    
	}


	
	
}
