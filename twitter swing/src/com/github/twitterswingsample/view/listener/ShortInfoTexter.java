package com.github.twitterswingsample.view.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.github.twitterswingsample.view.panels.ShortInfoPanel;

public class ShortInfoTexter implements MouseListener {

	private String text;
	
	public ShortInfoTexter(String text) {
		this.text = text;
	}

	public void mouseEntered(MouseEvent e) {
		ShortInfoPanel.getInstance().setInfo(text);
	}

	public void mouseExited(MouseEvent e) {
		ShortInfoPanel.getInstance().clearInfo();
	}

	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}