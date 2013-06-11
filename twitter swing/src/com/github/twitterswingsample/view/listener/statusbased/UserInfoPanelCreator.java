package com.github.twitterswingsample.view.listener.statusbased;

import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.MyScrollBarUI;
import com.github.twitterswingsample.view.panels.UserInfoPanel;

/**
 * MouseListener that creates a frame with {@link UserInfoPanel} in it
 * 
 * @author multiprogger
 */
public class UserInfoPanelCreator extends StatusMouseListener implements Runnable {

	private Twitter twitter;
	private JComponent container;
	private boolean insideHomeTimeline;
	
	public UserInfoPanelCreator(Status status, Twitter twitter, JComponent container, boolean insideHomeTimeline) {
		this(twitter, container, insideHomeTimeline);
		setStatus(status);
	}
	
	public UserInfoPanelCreator(Twitter twitter, JComponent container, boolean insideHomeTimeline) {
		this.twitter = twitter;
		this.container = container;
		this.insideHomeTimeline = insideHomeTimeline;
	}

	public void run() {
		try {
			User user = getStatus().getUser();
			UserInfoPanel panel;
			if (insideHomeTimeline) {
				panel = new UserInfoPanel(twitter, user, true);
			}
			else {
				panel = new UserInfoPanel(twitter, user);
			}
			JScrollPane pane = new JScrollPane(panel);
			pane.getVerticalScrollBar().setUI(new MyScrollBarUI());
			pane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
			container.add("@" + user.getScreenName(), pane);
		} catch (TwitterException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Could not create user information",
				e.getMessage()
			});
		}
	}

	public void mouseClicked(MouseEvent e) {
		new Thread(this).start();
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
}