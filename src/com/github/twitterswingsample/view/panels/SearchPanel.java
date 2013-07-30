package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import twitter4j.Twitter;

import com.github.twitterswingsample.view.TwitterButton;
import com.github.twitterswingsample.view.listener.authorized.timelineloader.SearchListener;

public class SearchPanel extends JPanel {
	
	public SearchPanel(Twitter twitter, ClientUserPanel userPanel) {
		setLayout(new BorderLayout(2, 2));
		TimelinePanel timelinePanel = new TimelinePanel(userPanel, false);
		JScrollPane pane = new JScrollPane(timelinePanel);
//		pane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
//		pane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		add(pane, BorderLayout.CENTER);
		JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JTextComponent query = new JTextField(30);
		south.add(query);
		TwitterButton send = new TwitterButton("search");
		send.addActionListener(new SearchListener(timelinePanel, twitter, query));
		south.add(send);
		add(south, BorderLayout.SOUTH);
	}
}