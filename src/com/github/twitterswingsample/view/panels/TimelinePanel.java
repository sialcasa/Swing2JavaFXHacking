package com.github.twitterswingsample.view.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Scrollable;

import twitter4j.Status;
import twitter4j.Twitter;

/**
 * Panel containing several {@link StatusPanel}
 * 
 * @author sourcefranke
 */
public class TimelinePanel extends JPanel implements Scrollable {
	
	private ClientUserPanel userPanel;
	private List<StatusPanel> statusPanelList;
	private boolean homeTimeline;
	
	public TimelinePanel(ClientUserPanel panel, boolean homeTimeline){
		this.userPanel = panel;
		this.homeTimeline = homeTimeline;
		statusPanelList = new LinkedList<StatusPanel>();
		setLayout(new GridBagLayout());
	}

	public TimelinePanel(Twitter twitter, List<Status> statusList, ClientUserPanel panel, boolean homeTimeline) {
		this(panel, homeTimeline);
		addContent(twitter, statusList);
	}
	
	public void addContent(Twitter twitter, List<Status> statusList){
		setVisible(false);
		removeAll();
		if(statusPanelList.size() > 0){
			long id = statusPanelList.get(0).getStatus().getId();
			for (int i = 0; statusList.get(i).getId() > id; i++) {
				StatusPanel statusPanel = new StatusPanel(twitter, statusList.get(i), userPanel, homeTimeline);
				statusPanelList.add(i, statusPanel);
			}
		}
		else {
			for (int i = 0; i < statusList.size(); i++) {
				StatusPanel panel = new StatusPanel(twitter, statusList.get(i), userPanel, homeTimeline);
				statusPanelList.add(panel);
			}
		}
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(3, 0, 3, 0);
		if(statusPanelList.size() > 0) {
			gbc.gridy = 0;
			add(statusPanelList.get(0), gbc);
			if(statusPanelList.size() > 1) {
				for (int i = 1; i < statusPanelList.size(); i++) {
					gbc.gridy++;
					add(new JSeparator(JSeparator.HORIZONTAL), gbc);
					gbc.gridy++;
					add(statusPanelList.get(i), gbc);
				}
			}
		}
		else {
			gbc.gridy = 1;
			add(new JLabel("no tweets"), gbc);
		}
		setVisible(true);
	}
	
	public void replaceContent(Twitter twitter, List<Status> statusList){
		statusPanelList = new LinkedList<StatusPanel>();
		addContent(twitter, statusList);
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