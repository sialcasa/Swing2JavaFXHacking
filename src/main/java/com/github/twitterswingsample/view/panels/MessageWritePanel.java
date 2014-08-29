package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.github.twitterswingsample.view.TwitterButton;
import com.github.twitterswingsample.view.listener.RemainingCharsListener;
import com.github.twitterswingsample.view.listener.authorized.SendMessageListener;

import twitter4j.Twitter;
import twitter4j.User;

public class MessageWritePanel extends JPanel {

	private JTextArea center;
	
	public MessageWritePanel(Twitter twitter, User user) {
		setLayout(new BorderLayout(3, 3));
		
		JPanel north = new JPanel(new FlowLayout());
		try {
			north.add(new JLabel(new ImageIcon(new URL(user.getMiniProfileImageURL()))));
		} catch (MalformedURLException e) {}
		JLabel text = new JLabel("Writing a message to " + user.getName());
		north.add(text);
		add(north, BorderLayout.NORTH);

		JLabel countLabel = new JLabel("140");
		center = new JTextArea();
		center.addCaretListener(new RemainingCharsListener(center, countLabel, 140));
		add(center);
		
		JPanel south = new JPanel(new FlowLayout());
		TwitterButton send = new TwitterButton("send");
		send.addActionListener(new SendMessageListener(twitter, user, this));
		south.add(countLabel);
		south.add(send);
		add(south, BorderLayout.SOUTH);
	}
	
	public String getMessageText(){
		return center.getText();
	}
}