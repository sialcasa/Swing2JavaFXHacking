package com.github.twitterswingsample.view.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Scrollable;

import com.github.twitterswingsample.view.listener.authorized.statusbased.StatusMouseListener;

import twitter4j.Status;
import twitter4j.Twitter;

/**
 * Panel containing several {@link StatusPanel}
 * 
 * @author multiprogger
 */
public class TimelinePanel extends JPanel implements Scrollable {
	
	private List<StatusPanel> statusPanelList;
	private List<MouseListener> listener;
	private List<StatusMouseListener> statusListener = new LinkedList<StatusMouseListener>();
	
	public TimelinePanel(){
		statusPanelList = new LinkedList<StatusPanel>();
		listener = new LinkedList<MouseListener>();
		setLayout(new GridBagLayout());
	}

	public TimelinePanel(Twitter twitter, List<Status> statusList) throws CloneNotSupportedException {
		this();
		addContent(twitter, statusList);
	}
	
	public void addContent(Twitter twitter, List<Status> statusList) throws CloneNotSupportedException{
		setVisible(false);
		removeAll();
		if(statusPanelList.size() > 0){
			long id = statusPanelList.get(0).getStatus().getId();
			for (int i = 0; statusList.get(i).getId() > id; i++) {
				StatusPanel panel = new StatusPanel(twitter, statusList.get(i));
				addListeners(panel);
				statusPanelList.add(i, panel);
			}
		}
		else {
			for (int i = 0; i < statusList.size(); i++) {
				StatusPanel panel = new StatusPanel(twitter, statusList.get(i));
				addListeners(panel);
				statusPanelList.add(panel);
			}
		}
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(3, 0, 3, 0);
		if(statusPanelList.size() > 0) {
			for (int i = 0; i < statusPanelList.size(); i++) {
				gbc.gridy = i + 1;
				add(statusPanelList.get(i), gbc);
			}
		}
		else {
			gbc.gridy = 1;
			add(new JLabel("no tweets"), gbc);
		}
		setVisible(true);
	}
	
	public void addMouseListenerToStatusProfileImages(MouseListener l) {
		listener.add(l);
		for (StatusPanel panel : statusPanelList) {
			panel.addMouseListenerToProfileImage(l);
		}
	}
	
	public void addStatusMouseListenerToStatusProfileImages(StatusMouseListener l) throws CloneNotSupportedException {
		statusListener.add(l);
		for (StatusPanel panel : statusPanelList) {
			panel.addStatusMouseListenerToProfileImage(l);
		}
	}
	
	private void addListeners(StatusPanel panel) throws CloneNotSupportedException{
		for (StatusMouseListener l : statusListener) {
			panel.addStatusMouseListenerToProfileImage(l);
		}
		for (MouseListener l : listener) {
			panel.addMouseListenerToProfileImage(l);
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