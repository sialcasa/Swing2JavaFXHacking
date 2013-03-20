package com.github.twitterswingsample.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import twitter4j.Twitter;
import com.github.twitterswingsample.view.listener.authorized.timelineloader.HomeTimelineLoader;

public class ClientUserPanel extends JPanel{
    
	public ClientUserPanel(Twitter twitter) {
		setLayout(new BorderLayout(5, 5));
		
		TimelinePanel homeTimeline = new TimelinePanel(twitter);
		HomeTimelineLoader homeTimelineLoader = new HomeTimelineLoader(homeTimeline, twitter);
		homeTimelineLoader.actionPerformed(null);
		JScrollPane homeTimelineScrollPane = new JScrollPane(homeTimeline);
		homeTimelineScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		homeTimelineScrollPane.getHorizontalScrollBar().setUI(new MyScrollBarUI());
		add(homeTimelineScrollPane, BorderLayout.CENTER);
		
		JButton reloadHomeTimeline = new JButton("reload Hometimeline");
		reloadHomeTimeline.addActionListener(homeTimelineLoader);
		add(reloadHomeTimeline, BorderLayout.SOUTH);
	}
	
	public class MyScrollBarUI extends BasicScrollBarUI {

	        private JButton b = new JButton() {

	            @Override
	            public Dimension getPreferredSize() {
	                return new Dimension(0, 0);
	            }

	        };

	        @Override
	        protected void paintTrack(Graphics g, JComponent c,
	                Rectangle trackBounds) {
	            g.setColor(Color.GRAY);
	            g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width,
	                    trackBounds.height);
	        }

	        @Override
	        protected void paintThumb(Graphics g, JComponent c,
	                Rectangle thumbBounds) {
	            g.setColor(Color.LIGHT_GRAY);
	            g.fillRect(thumbBounds.x + 2, thumbBounds.y + 2, thumbBounds.width - 3,
	                    thumbBounds.height - 3);
	        }

	        @Override
	        protected JButton createDecreaseButton(int orientation) {
	            return b;
	        }

	        @Override
	        protected JButton createIncreaseButton(int orientation) {
	            return b;
	        }
	    }

}