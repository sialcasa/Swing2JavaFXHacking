package com.github.twitterswingsample.view.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Scrollable;

import twitter4j.Status;
import twitter4j.Twitter;

/**
 * Panel containing several {@link StatusPanel}
 * 
 * @author multiprogger
 */
public class TimelinePanel extends JPanel implements Scrollable {
	
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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		for (int i = 0; i < statuses.size(); i++) {
			gbc.gridy = i;
			add(new StatusPanel(twitter, statuses.get(i), pane), gbc);
		}
	}

	/* methods of interface Scrollable */

	public Dimension getPreferredScrollableViewportSize() {
		return super.getPreferredSize();
	}
	
	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 10;
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	public boolean getScrollableTracksViewportWidth() {
		return true;
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		return 10;
	}
}