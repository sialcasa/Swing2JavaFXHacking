package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;

/**
 * Panel displaying messages about all the processes of the client
 * 
 * @author sourcefranke
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
		
		Properties properties = new Properties();
		BufferedInputStream stream;
		String text = "Welcome to Twitter4J Swing Sample \n";
		try {
			stream = new BufferedInputStream(getClass().getResourceAsStream("/version.properties"));
			properties.load(stream);
			stream.close();
			
			text += "maintained by " + properties.getProperty("maintainer_name")
				+ " (" + properties.getProperty("maintainer_email") + ") \n"
				+ "Version: " + properties.getProperty("version");
		} catch (IOException e) {
			text += "\ncouldn't find version information";
		}
		area.setText(text);
		
		add(area);
	}
	
	/**
	 * Inserts a message into the textarea
	 * 
	 * @param message text to be displayed in the console. every field of the array gets a new line.
	 */
	public synchronized void printMessage(String[] message){
		area.setVisible(false);
		String tmp = "\n\n" + new Date() + ":";
		for (int i = 0; i < message.length; i++) {
			tmp += "\n" + message[i];
		}
		area.insert(tmp, area.getText().length());
		area.setVisible(true);
	}
}