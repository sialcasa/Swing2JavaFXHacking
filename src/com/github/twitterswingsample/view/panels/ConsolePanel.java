package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;
import java.util.Date;

import javax.swing.JPanel;

/**
 * Panel displaying messages about all the processes of the client
 * 
 * @author multiprogger
 */
public class ConsolePanel extends JPanel{

	private static ConsolePanel SINGLETON;
	private TextArea area;
	
	public static synchronized ConsolePanel getInstance(){
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
				"\nby multiprogger" +
				"\nhosted on GitHub: http://multiprogger.github.com/twitter4j-swing-sample/");
		
		add(area);
	}
	
	/**
	 * Inserts a message into the textarea
	 * 
	 * @param message text to be displayed in the console. every field of the array gets a new line.
	 */
	public synchronized void printMessage(String[] message){
		String tmp = "\n\n" + new Date() + ":";
		for (int i = 0; i < message.length; i++) {
			tmp += "\n" + message[i];
		}
		area.insert(tmp, area.getText().length());
	}
}