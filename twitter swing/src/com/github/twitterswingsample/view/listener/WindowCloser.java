package com.github.twitterswingsample.view.listener;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowCloser implements ActionListener {

	private Window window;
	
	public WindowCloser(Window window) {
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) {
		window.dispose();
	}
}