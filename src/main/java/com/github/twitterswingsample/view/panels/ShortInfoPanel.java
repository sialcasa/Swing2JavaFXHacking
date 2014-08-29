package com.github.twitterswingsample.view.panels;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel that show short info messages.
 * 
 * @author sourcefranke
 */
public class ShortInfoPanel extends JPanel {

	private static ShortInfoPanel SINGLETON;
	private JLabel info;
	
	public static ShortInfoPanel getInstance() {
		if(SINGLETON == null){
			SINGLETON = new ShortInfoPanel();
		}
		return SINGLETON;
	}
	
	private ShortInfoPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		info = new JLabel(" ");
		add(info);
	}

	public void setInfo(String text){
		info.setText(text);
	}

	public void clearInfo(){
		info.setText(" ");
	}
}