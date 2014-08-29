package com.github.twitterswingsample.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.github.twitterswingsample.view.frames.MainFrame;
import com.github.twitterswingsample.view.listener.AddAccountListener;

public class LoginDataDialog extends JDialog {

	private JTextField nameField, apikeyField, apisecretField,
			accesstokenField, accesstokensecretField;
	
	public LoginDataDialog(Frame parent, MainFrame frame) {
		super(parent, "Login Data", true);
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel name = new JLabel("Name");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		panel.add(name, gbc);
		
		nameField = new JTextField(40);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		panel.add(nameField, gbc);
		
		JLabel apikey = new JLabel("API Key");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		panel.add(apikey, gbc);
		
		apikeyField = new JTextField(50);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		panel.add(apikeyField, gbc);
		
		JLabel apisecret = new JLabel("API Secret");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		panel.add(apisecret, gbc);
		
		apisecretField = new JTextField(50);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		panel.add(apisecretField, gbc);
		
		JLabel accesstoken = new JLabel("Access token");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		panel.add(accesstoken, gbc);
		
		accesstokenField = new JTextField(50);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		panel.add(accesstokenField, gbc);
		
		JLabel accesstokensecret = new JLabel("Access token secret");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		panel.add(accesstokensecret, gbc);
		
		accesstokensecretField = new JTextField(50);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		panel.add(accesstokensecretField, gbc);
		
		panel.setBorder(new LineBorder(Color.GRAY));
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton ok = new TwitterButton("Add Account");
		ok.addActionListener(new AddAccountListener(frame, this));
		getContentPane().add(ok, BorderLayout.SOUTH);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}
	
	public String getName() {
		return nameField.getText();
	}
	
	public String getAPIKey() {
		return apikeyField.getText();
	}
	
	public String getAPISecret() {
		return apisecretField.getText();
	}
	
	public String getAccessToken() {
		return accesstokenField.getText();
	}
	
	public String getAccessTokenSecret() {
		return accesstokensecretField.getText();
	}
}