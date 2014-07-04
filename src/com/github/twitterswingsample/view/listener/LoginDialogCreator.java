package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.twitterswingsample.view.LoginDataDialog;
import com.github.twitterswingsample.view.frames.MainFrame;

public class LoginDialogCreator implements ActionListener {

	private MainFrame frame;
	
	public LoginDialogCreator(MainFrame frame) {
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		new LoginDataDialog(null, this.frame).setVisible(true);
	}
}