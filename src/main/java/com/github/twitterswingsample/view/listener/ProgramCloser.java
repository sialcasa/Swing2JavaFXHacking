package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener that terminates the program
 * 
 * @author sourcefranke
 */
public class ProgramCloser implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}