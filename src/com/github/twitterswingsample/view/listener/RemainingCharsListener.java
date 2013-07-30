package com.github.twitterswingsample.view.listener;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class RemainingCharsListener implements CaretListener {

	private JTextArea area;
	private JLabel label;
	private int limit;
	
	public RemainingCharsListener(JTextArea area, JLabel label, int limit) {
		this.area = area;
		this.label = label;
		this.limit = limit;
	}

	public void caretUpdate(CaretEvent e) {
		label.setText((limit - area.getText().length()) + "");
	}
}