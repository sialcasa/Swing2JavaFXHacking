package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Abstract ActionListener that starts a new thread
 * (to be used for server requests)
 * 
 * @author sourcefranke
 */
public abstract class MultithreadingListener implements ActionListener, Runnable {

	public void actionPerformed(ActionEvent arg0) {
		new Thread(this).start();
	}
}