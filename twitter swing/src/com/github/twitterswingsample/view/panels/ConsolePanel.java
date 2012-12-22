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
		String tmp = messageHeader();
		for (int i = 0; i < message.length; i++) {
			tmp += "\n" + message[i];
		}
		area.insert(tmp, area.getText().length());
	}
	
	public void printException(String description, TwitterException e){
		area.insert(messageHeader() +
				"\nTwitter Exception" +
				"\n" + description +
				"\n" + e.getAccessLevel() +
				"\n" + e.getErrorCode() +
				"\n" + e.getExceptionCode() +
				"\n" + e.getStatusCode() +
				(e.isErrorMessageAvailable() ? "\n" + e.getErrorMessage() : "") +
				"\n" + e.getMessage() +
				"\n" + e.getLocalizedMessage() +
				"\ncaused by network issue: " + e.isCausedByNetworkIssue() +
				"\nretry after: " + e.getRetryAfter(),
				area.getText().length());
	}
	
	public void printException(String description, HTTPException e){
		area.insert(messageHeader() +
				"\nException" +
				"\n" + description +
				"\n" + e.getMessage() +
				"\n" + e.getLocalizedMessage() +
				"\n" + e.getStatusCode() +
				"\n" + e.getCause(), area.getText().length());
	}
	
	public void printException(String description, Exception e){
		area.insert(messageHeader() +
				"\nException" +
				"\n" + description +
				"\n" + e.getMessage() +
				"\n" + e.getLocalizedMessage() +
				"\n" + e.getCause(), area.getText().length());
	}
	
	private String messageHeader(){
		return "\n\n" + new Date() + ":";
	}
}