package com.github.twitterswingsample.view.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.TwitterTheme;
import com.github.twitterswingsample.view.listener.ProgramCloser;
import com.github.twitterswingsample.view.listener.SwitchUserListener;
import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.ShortInfoPanel;

/**
 * The main window of the client
 * 
 * @author multiprogger
 */
public class MainFrame extends JFrame{
	
	private Credentials creds;
	
	public MainFrame() {
		setBounds(50, 0, 600, 700);
		setExtendedState(MAXIMIZED_VERT);
		setTitle("Twitter4J Swing Sample");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(2, 2));
		try {
			MetalLookAndFeel.setCurrentTheme(new TwitterTheme());
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			ConsolePanel.getInstance().printMessage(new String[]{
				"Look-And-Feel could not be set",
				e1.getLocalizedMessage()
			});
		}
		
		JMenuBar jmb = new JMenuBar();
		JMenu file = new JMenu("File"),
		switchUser = new JMenu("Switch user");
		JMenuItem exit = new JMenuItem("Close");
		file.add(switchUser);
		file.addSeparator();
		exit.addActionListener(new ProgramCloser());
		file.add(exit);
		jmb.add(file);
		setJMenuBar(jmb);
		
		try {
			setIconImage(ImageIO.read(getClass().getResource("images/icon.png")));
		} catch (IOException e) {}

		JSplitPane vertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vertical.setBottomComponent(ConsolePanel.getInstance());
		vertical.setResizeWeight(1.);
		vertical.setOneTouchExpandable(true);
		
		try {
			creds = new Credentials();
			CardLayout layout = new CardLayout();
			JPanel cardPanel = new JPanel(layout);
			ButtonGroup group = new ButtonGroup();
			for (int i = 0; i < creds.getNumberOfUsers(); i++) {
				JCheckBoxMenuItem item = new JCheckBoxMenuItem(creds.getName(i));
				item.addActionListener(new SwitchUserListener(layout, cardPanel, creds.getName(i)));
				group.add(item);
				switchUser.add(item);
				cardPanel.add(creds.getName(i), new ClientUserPanel(creds.getTwitter(i)));
			}
			group.getElements().nextElement().setSelected(true);
			vertical.setTopComponent(cardPanel);

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

		add(vertical, BorderLayout.CENTER);
		add(ShortInfoPanel.getInstance(), BorderLayout.SOUTH);
	}
	
	public static void main(String[] args){
		new MainFrame().setVisible(true);
	}
}