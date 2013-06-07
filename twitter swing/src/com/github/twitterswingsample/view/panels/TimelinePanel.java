package com.github.twitterswingsample.view.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import twitter4j.Status;
import twitter4j.Twitter;

/**
 * Panel containing several {@link StatusPanel}
 * 
 * @author multiprogger
 */
public class TimelinePanel extends JPanel{
	
	private Twitter twitter;
	private JTabbedPane pane;

	public TimelinePanel(Twitter twitter, JTabbedPane pane) {
		this.twitter = twitter;
		this.pane = pane;
		setLayout(new GridBagLayout());
	}

	public TimelinePanel(Twitter twitter, List<Status> statuses, JTabbedPane pane) {
		this(twitter, pane);
		fillPanel(statuses);
	}
	
	public void setContent(List<Status> statuses){
		setVisible(false);
		removeAll();
		fillPanel(statuses);
		setVisible(true);
	}
	
	private void fillPanel(List<Status> statuses){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = gbc.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);
		for (int i = 0; i < statuses.size(); i++) {
			gbc.gridy = i;
			add(new StatusPanel(twitter, statuses.get(i), pane), gbc);
		}
	}
}