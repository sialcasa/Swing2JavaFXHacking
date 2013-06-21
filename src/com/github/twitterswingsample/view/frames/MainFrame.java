package com.github.twitterswingsample.view.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.listener.ProgramCloser;
import com.github.twitterswingsample.view.listener.SwitchUserListener;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.ShortInfoPanel;

/**
 * The main window of the client
 * 
 * @author multiprogger
 */
public class MainFrame extends JFrame{
	
	public MainFrame() {
		setBounds(50, 0, 600, 700);
		setTitle("Twitter4J Swing Sample");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(2, 2));
		
		Font menuFont = new Font("Arial", Font.BOLD, 13);
		JMenuBar jmb = new JMenuBar();
		JMenu file = new JMenu("File"),
		switchUser = new JMenu("Switch user");
		JMenuItem exit = new JMenuItem("Close");
		switchUser.setFont(menuFont);
		file.add(switchUser);
		file.addSeparator();
		exit.setFont(menuFont);
		exit.addActionListener(new ProgramCloser());
		file.add(exit);
		file.setFont(menuFont);
		jmb.add(file);
		setJMenuBar(jmb);
		
		try {
			setIconImage(ImageIO.read(getClass().getResource("images/icon.png")));
		} catch (IOException e) {}

		JSplitPane pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		pane.setBottomComponent(ConsolePanel.getInstance());
		pane.setDividerLocation(1.);
		pane.setResizeWeight(1.);
		pane.setOneTouchExpandable(true);
		add(pane, BorderLayout.CENTER);
		try {
			Credentials creds = new Credentials();
			CardLayout layout = new CardLayout();
			JPanel cardPanel = new JPanel(layout);
			ButtonGroup group = new ButtonGroup();
			for (int i = 0; i < creds.getNumberOfUsers(); i++) {
				JCheckBoxMenuItem item = new JCheckBoxMenuItem(creds.getName(i));
				item.setFont(menuFont);
				item.addActionListener(new SwitchUserListener(layout, cardPanel, creds.getName(i)));
				group.add(item);
				switchUser.add(item);
				cardPanel.add(creds.getName(i), new ClientUserPanel(creds.getTwitter(i)));
			}
			group.getElements().nextElement().setSelected(true);
			pane.setTopComponent(cardPanel);
		} catch (FileNotFoundException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"File 'login.xml' not found",
				"See the project homepage for further information",
				e.getLocalizedMessage()
			});
		} catch (IOException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Unknown error",
				"Please report this bug!",
				e.getLocalizedMessage()
			});
		} catch (SAXException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"damaged xml structure in file 'login.xml'",
				"Repair it!",
				e.getLocalizedMessage()
			});
		} catch (ParserConfigurationException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Internal Parsing Error",
				"Please report this bug!",
				e.getLocalizedMessage()
			});
		} catch (Exception e) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Unspecified Error",
				"Perhaps 'login.xml' not filled",
				"Otherwise, please report this bug!",
				e.getLocalizedMessage()
			});
		}
		
		add(ShortInfoPanel.getInstance(), BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) throws Exception {
		new MainFrame().setVisible(true);
	}
}