package com.github.twitterswingsample.view;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.plaf.InsetsUIResource;

public class TwitterButton extends JButton {

	public TwitterButton() {
		super();
		adapt();
	}
	
	public TwitterButton(Action a) {
		super(a);
		adapt();
	}

	public TwitterButton(Icon icon) {
		super(icon);
		adapt();
	}

	public TwitterButton(String text, Icon icon) {
		super(text, icon);
		adapt();
	}

	public TwitterButton(String name) {
		super(name);
		adapt();
	}

	private void adapt() {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorder(BorderFactory.createMatteBorder(0, 0, 3, 3, new Color(0, 0, 80)));
		setMargin(new InsetsUIResource(3, 3, 3, 3));
	}
}