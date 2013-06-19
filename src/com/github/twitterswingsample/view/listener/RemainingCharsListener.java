package com.github.twitterswingsample.view.listener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class RemainingCharsListener implements CaretListener {

	private JTextArea area;
	private JLabel label;
	
	public RemainingCharsListener(JTextArea area, JLabel label) {
		this.area = area;
		this.label = label;
	}

	public void caretUpdate(CaretEvent e) {
		label.setText((140 - area.getText().length()) + "");
	}
}