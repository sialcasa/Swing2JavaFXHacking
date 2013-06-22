package com.github.twitterswingsample.view.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

public class TabPopupMenuOpener implements MouseListener {
	
	private JPopupMenu popup;
	
	public TabPopupMenuOpener(JTabbedPane pane) {
		popup = new JPopupMenu("Options");
		JMenuItem item = popup.add("Close selected tab");
		item.addActionListener(new TabCloser(pane));
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}