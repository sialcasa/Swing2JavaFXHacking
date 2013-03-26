package com.github.twitterswingsample.view.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.TwitterUserPanel;

/**
 * MouseListener that creates a frame with {@link TwitterUserPanel} in it
 * 
 * @author multiprogger
 */
public class TwitterUserPresentation implements MouseListener, Runnable {

	private Twitter twitter;
	private User user;
	
	public TwitterUserPresentation(Twitter twitter, User user) {
		super();
		this.twitter = twitter;
		this.user = user;
	}

	public void run() {
		try {
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setTitle("@" + user.getScreenName());
			frame.setBounds(20,20,900,500);
			frame.add(new TwitterUserPanel(twitter, user));
			frame.setVisible(true);
		} catch (TwitterException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
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