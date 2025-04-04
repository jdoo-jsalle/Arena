package com.js.dawa.iu.console;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;


import javax.swing.JFrame;
import javax.swing.JLabel;


import com.js.dawa.iu.graphique.UIPanel;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.AreneProps;

public class ConsoleGraphique implements Console {
	
	
	
	JFrame frame;
	 
	AreneProps mProps;
	
	JLabel mText;
	
	
	
	public void update() {
		frame.repaint();
	}
	
	
	@Override
	public void init (Arene pArene) {
		mProps = pArene.getAreneProps();
		// Création de la fenêtre
        frame = new JFrame(mProps.getTitle());
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1500, 700);
        
        Container lPanel = frame.getContentPane();
        lPanel.setLayout(new BorderLayout());
        
        
        
        UIPanel lPanelArene = new UIPanel();
        lPanelArene.init(pArene);
        lPanelArene.setPreferredSize(new Dimension(1400,700));
      
      
        lPanel.add(lPanelArene,BorderLayout.CENTER);
   
        
        // Création de la zone de texte
        //=> variable global 
        mText = new JLabel("Text");
      
        lPanel.add(mText,BorderLayout.PAGE_END);
       
      
        // Affichage de la fenêtre
        frame.pack();
        frame.setVisible(true);
    
	}


	@Override
	public void setText(String pText) {
		mText.setText(pText);
		
	}


	
	
}
