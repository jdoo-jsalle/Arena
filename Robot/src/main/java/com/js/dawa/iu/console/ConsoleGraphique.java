package com.js.dawa.iu.console;

import java.awt.Font;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.js.dawa.iu.arene.render.CaseAreneRender;

public class ConsoleGraphique implements Console {
	
	
	JTextArea textArea = new JTextArea();
	 
	Map<String, String> mProps;
	 
	int getProps (String pProps) {
		return 0;
	}
	
	public void init (Map<String, String> pProps) {
		mProps = pProps;
		// Création de la fenêtre
        JFrame frame = new JFrame(pProps.get("titre"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Création de la zone de texte
        //=> variable global        
       
        
        // Définition de la police monospaces
        Font font = new Font("Courier", Font.PLAIN, 12);
        textArea.setFont(font);
        
        // Ajout de la zone de texte à la fenêtre
        frame.add(new JScrollPane(textArea));
        
        // Affichage de la fenêtre
        frame.setVisible(true);
    
	}

	@Override
	public void printRender(CaseAreneRender pCaseRender) {
		printText(pCaseRender.getStringRender());
		
	}

	@Override
	public void rc() {
		printText("\n");
		
	}

	@Override
	public void printText(String pText) {
		textArea.append(pText);
		System.out.print(pText);
		
	}

	@Override
	public void printHeader(String pDecal,int pTot) {
		printText(pDecal);
		for (int c = 'a'; c <= 'a' + pTot-1; c++) {
			
			printText(Character.toString ((char)c));
	
		}
		rc();
		    
		
	}
}
