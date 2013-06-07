package com.github.twitterswingsample.view.frames;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopupFrame extends JFrame {

	private JLabel label;
	
	public PopupFrame() {
		setAlwaysOnTop(true);
		setUndecorated(true);
		setResizable(false);

		setSize(300, 100);
		
		JPanel cp = (JPanel) getContentPane();
		cp.setLayout(new FlowLayout());
		label = new JLabel();
		cp.add(label);
	}
	
	public PopupFrame(String text) {
		this();
		setText(text);
	}
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public boolean isTextSet(){
		return label.getText() != null && label.getText().length() > 0;
	}
	
	public void arrange(){
		setVisible(true);
		Point p = MouseInfo.getPointerInfo().getLocation();
		Dimension d = label.getPreferredSize();
		setBounds(p.x + 5, p.y + 5, d.width + 5, d.height + 5);
	}
}