package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.util.Date;

import javax.swing.JPanel;
import javax.xml.ws.http.HTTPException;

import twitter4j.TwitterException;

public class ConsolePanel extends JPanel{

	private static ConsolePanel SINGLETON;
	private TextArea area;
	
	public static ConsolePanel getSingleton(){
		if(SINGLETON == null){
			SINGLETON = new ConsolePanel();
		}
		
		return SINGLETON;
	}
	
	private ConsolePanel() {
		setLayout(new BorderLayout());
		
		area = new TextArea(2, 0);
		area.setEditable(false);
		area.setBackground(Color.WHITE);
		area.setText("Welcome to Twitter4J Swing Sample" +
				"\nby javaprogger" +
				"\nhosted on GitHub: http://javaprogger.github.com/twitter4j-swing-sample/");
		
		add(area);
	}
	
	public void printMessage(String[] message){
		String tmp = "\n\n" + new Date() + ":";
		for (int i = 0; i < message.length; i++) {
			tmp += "\n" + message[i];
		}
		area.insert(tmp, area.getText().length());
	}
}