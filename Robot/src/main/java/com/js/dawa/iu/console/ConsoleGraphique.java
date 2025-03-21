package com.js.dawa.iu.console;

import java.awt.Font;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.js.dawa.iu.arene.CaseArene;
import com.js.dawa.iu.arene.ObjetArene;
import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.ui.graphique.UIPanel;

public class ConsoleGraphique implements Console {
	
	
	JTextArea textArea = new JTextArea();
	
	JFrame frame;
	 
	Map<String, String> mProps;
	
	List<ObjetArene> mLstCaseArene;
	 
	int getIntProps (String pProps) {
		return 0;
	}
	
	
	public void update() {
		frame.repaint();
	}
	
	public void init (Map<String, String> pProps) {
		mProps = pProps;
		// Création de la fenêtre
        frame = new JFrame(pProps.get("titre"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1500);
        
        // Création de la zone de texte
        //=> variable global      
        
        UIPanel lPanel = new UIPanel();
        lPanel.setLstCase(mLstCaseArene);
        frame.add(lPanel);
       
        
        // Définition de la police monospaces
        Font font = new Font("Courier", Font.PLAIN, 12);
        textArea.setFont(font);
        
        // Ajout de la zone de texte à la fenêtre
       // frame.add(new JScrollPane(textArea));
        
        // Affichage de la fenêtre
        frame.setVisible(true);
    
	}

	@Override
	public void printRender(CaseRender pCaseRender) {
		printText(pCaseRender.getStringRender());
		
	}

	@Override
	public void rc() {
		printText("\n");
		
	}

	@Override
	public void printText(String pText) {
		textArea.append(pText);
		
		
	}

	
	public void setListCase (List<ObjetArene> pLstCaseArene) {
		mLstCaseArene = pLstCaseArene;
	}
}
