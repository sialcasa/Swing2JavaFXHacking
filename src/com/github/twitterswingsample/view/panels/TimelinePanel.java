package com.github.twitterswingsample.view.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

import com.github.twitterswingsample.view.listener.statusbased.StatusMouseListener;

import twitter4j.Status;
import twitter4j.Twitter;

/**
 * Panel containing several {@link StatusPanel}
 * 
 * @author multiprogger
 */
public class TimelinePanel extends JPanel implements Scrollable {
	
	private List<Status> statusList;
	private List<MouseListener> listener = new LinkedList<>();
	private List<StatusMouseListener> statusListener = new LinkedList<>();
	
	public TimelinePanel(){
		statusList = new LinkedList<>();
		setLayout(new FlowLayout());
		add(new JLabel("no content"));
	}

	public TimelinePanel(Twitter twitter, List<Status> statusList) throws CloneNotSupportedException {
		setContent(twitter, statusList);
	}
	
	public void setContent(Twitter twitter, List<Status> statusList) throws CloneNotSupportedException{
		setVisible(false);
		removeAll();
		this.statusList = statusList;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		for (int i = 0; i < statusList.size(); i++) {
			gbc.gridy = i;
			StatusPanel panel = new StatusPanel(twitter, statusList.get(i));
			for (StatusMouseListener l : statusListener) {
				panel.addStatusMouseListenerToProfileImage(l);
			}
			for (MouseListener l : listener) {
				panel.addMouseListenerToProfileImage(l);
			}
			add(panel, gbc);
		}
		setVisible(true);
	}
	
	public List<Status> getStatusList(){
		return statusList;
	}
	
	public synchronized void addMouseListenerToStatusProfileImages(MouseListener l) {
		listener.add(l);
	}
	
	public synchronized void addStatusMouseListenerToStatusProfileImages(StatusMouseListener l) {
		statusListener.add(l);
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