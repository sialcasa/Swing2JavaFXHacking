package com.github.twitterswingsample.view.frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.listener.ProgramCloser;
import com.github.twitterswingsample.view.listener.UserSelectionFrameCreator;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.ClientUserPanel;

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
		setLayout(new BorderLayout(5, 5));
		
		try {
			setIconImage(ImageIO.read(getClass().getResource("images/icon.png")));
		} catch (IOException e) {}
		
		Font menuFont = new Font("Arial", Font.BOLD, 13);
		JMenuBar jmb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem open = new JMenuItem("Open User Selection"),
				exit = new JMenuItem("Close");
		open.setFont(menuFont);
		open.addActionListener(new UserSelectionFrameCreator());
		file.add(open);
		file.addSeparator();
		exit.setFont(menuFont);
		exit.addActionListener(new ProgramCloser());
		file.add(exit);
		file.setFont(menuFont);
		jmb.add(file);
		setJMenuBar(jmb);
		
		try {
			add(new ClientUserPanel(new Credentials(0).getTwitter()), BorderLayout.CENTER);
		} catch (FileNotFoundException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
				"File 'login.xml' not found",
				"See the project homepage for further information"
			});
		} catch (IOException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
				"Unknown error"
			});
		} catch (SAXException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
				"damaged xml structure in file 'login.xml'",
				"Repair it!"
			});
		} catch (ParserConfigurationException e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
				"Internal Error",
				"Please report this vagina"
			});
		} catch (Exception e) {
			ConsolePanel.getSingleton().printMessage(new String[]{
				"File 'login.xml' not filled",
				"You have to insert the credentials for your application!",
				"See the project homepage for further information"
			});
		}

		add(ConsolePanel.getSingleton(), BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) throws Exception {
		new MainFrame().setVisible(true);
	}
}