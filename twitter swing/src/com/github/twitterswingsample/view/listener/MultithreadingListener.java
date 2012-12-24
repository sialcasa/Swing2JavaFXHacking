package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MultithreadingListener implements ActionListener, Runnable {

	public void actionPerformed(ActionEvent arg0) {
		new Thread(this).start();
	}
}