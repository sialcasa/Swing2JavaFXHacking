package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.github.twitterswingsample.view.TwitterButton;
import com.github.twitterswingsample.view.listener.RemainingCharsListener;
import com.github.twitterswingsample.view.listener.authorized.SendStatusListener;

import twitter4j.Twitter;

/**
 * Panel for writing statuses
 * 
 * @author sourcefranke
 */
public class StatusWritePanel extends JPanel{

	private JTextArea tweetArea;
	
	public StatusWritePanel(Twitter twitter) {
		setLayout(new BorderLayout(5, 5));
		JLabel countLabel = new JLabel("140");
		tweetArea = new JTextArea();
		tweetArea.addCaretListener(new RemainingCharsListener(tweetArea, countLabel, 140));
		add(tweetArea, BorderLayout.CENTER);
		
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
		TwitterButton send = new TwitterButton("tweet");
		send.addActionListener(new SendStatusListener(twitter, this));
		south.add(countLabel);
		south.add(send);
		add(south, BorderLayout.SOUTH);
	}
	
	public String getStatusText(){
		return tweetArea.getText();
	}
	
	public void setStatusText(String text){
		tweetArea.setText(text);
	}
}