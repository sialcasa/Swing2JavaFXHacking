package com.github.twitterswingsample.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.github.twitterswingsample.view.panels.UserSelectionPanel;

public class UserSelectionFrameCreator implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Choose an account");
		frame.setBounds(20,20,100,200);
		frame.add(new UserSelectionPanel());
		frame.setVisible(true);
	}
}