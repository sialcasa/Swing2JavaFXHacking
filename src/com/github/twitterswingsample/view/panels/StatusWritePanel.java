package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.github.twitterswingsample.view.listener.authorized.SendStatusListener;

import twitter4j.Twitter;

/**
 * Panel for writing statuses
 * 
 * @author multiprogger
 */
public class StatusWritePanel extends JPanel{

	private JTextArea tweetArea;
	
	public StatusWritePanel(Twitter twitter) {
		setLayout(new BorderLayout(5, 5));
		tweetArea = new JTextArea();
		add(tweetArea, BorderLayout.CENTER);
		
		JButton send = new JButton("send tweet");
		send.addActionListener(new SendStatusListener(twitter, this));
		add(send, BorderLayout.SOUTH);
	}
	
	public String getStatusText(){
		String text = tweetArea.getText();
		tweetArea.setText("");
		return text;
	}
}