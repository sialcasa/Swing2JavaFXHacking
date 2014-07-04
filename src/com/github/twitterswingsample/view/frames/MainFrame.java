package com.github.twitterswingsample.view.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

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

import twitter4j.Twitter;

import com.github.twitterswingsample.model.Credentials;
import com.github.twitterswingsample.view.TwitterTheme;
import com.github.twitterswingsample.view.listener.LoginDialogCreator;
import com.github.twitterswingsample.view.listener.ProgramCloser;
import com.github.twitterswingsample.view.listener.RemoveAccountListener;
import com.github.twitterswingsample.view.listener.SwitchAccountListener;
import com.github.twitterswingsample.view.panels.ClientUserPanel;
import com.github.twitterswingsample.view.panels.ConsolePanel;
import com.github.twitterswingsample.view.panels.ShortInfoPanel;

/**
 * The main window of the client
 * 
 * @author sourcefranke
 */
public class MainFrame extends JFrame{
	
	private JPanel cardPanel;
	private CardLayout layout;
	private ButtonGroup group;
	private JMenu switchUser,
				removeUser;
	
	public MainFrame() {
		Properties properties = new Properties();
		BufferedInputStream stream;
		String title = "Twitter4J Swing Sample ";
		try {
			stream = new BufferedInputStream(getClass().getResourceAsStream("/version.properties"));
			properties.load(stream);
			stream.close();
			title += properties.getProperty("version");
		} catch (IOException e) {
			title += "(unknown version)";
		}
		setTitle(title);
		
		setBounds(50, 0, 600, 700);
		setExtendedState(MAXIMIZED_VERT);
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
		
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Close");
		exit.addActionListener(new ProgramCloser());
		file.add(exit);
		jmb.add(file);
		
		JMenu user = new JMenu("Account");
		switchUser = new JMenu("Switch account");
		removeUser = new JMenu("Remove account");
		JMenuItem addUser = new JMenuItem("Add account ...");
		addUser.addActionListener(new LoginDialogCreator(this));
		user.add(addUser);
		user.add(switchUser);
		user.add(removeUser);
		jmb.add(user);
		
		setJMenuBar(jmb);
		
		try {
			setIconImage(ImageIO.read(getClass().getResource("images/icon.png")));
		} catch (IOException e) {}

		JSplitPane vertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vertical.setBottomComponent(ConsolePanel.getInstance());
		vertical.setResizeWeight(1.);
		vertical.setOneTouchExpandable(true);
		
		try {
			Credentials creds = new Credentials();
			layout = new CardLayout();
			cardPanel = new JPanel(layout);
			group = new ButtonGroup();
			
			List<Integer> ids = creds.getIDs();
			List<String> names = creds.getNames();
			List<Twitter> twitters = creds.getTwitters();
			for (int i = 0; i < twitters.size(); i++) {
				addItemsAndPanels(creds, names.get(i), ids.get(i), twitters.get(i));
			}
			
			if(group.getButtonCount() > 0) {
				group.getElements().nextElement().setSelected(true);
			}
			vertical.setTopComponent(cardPanel);
		} catch (SQLException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"SQL Exception thrown, there is an incorrect SQL statement",
					"Please report that bug!",
					e.getLocalizedMessage()
			});
		} catch (ClassNotFoundException e) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"SQLite driver couldn't be found",
					"Please report that bug!",
					e.getLocalizedMessage()
			});
		} catch (Exception e) {
			ConsolePanel.getInstance().printMessage(new String[]{
					"Unspecified Error",
					"Please report that bug!",
					e.getLocalizedMessage()
			});
		}

		add(vertical, BorderLayout.CENTER);
		add(ShortInfoPanel.getInstance(), BorderLayout.SOUTH);
	}
	
	public void addUser(Credentials creds, int id) throws ClassNotFoundException, SQLException {
		cardPanel.setVisible(false);
		addItemsAndPanels(creds, creds.getName(id), id, creds.getTwitter(id));
		cardPanel.setVisible(true);
	}
	
	private void addItemsAndPanels(Credentials creds, String name, int id, Twitter twitter) {
		JCheckBoxMenuItem switchItem = new JCheckBoxMenuItem(name);
		switchItem.addActionListener(new SwitchAccountListener(layout, cardPanel, name));
		group.add(switchItem);
		switchUser.add(switchItem);
		cardPanel.add(name, new ClientUserPanel(twitter));
		
		if(group.getButtonCount() == 1) {
			switchItem.setSelected(true);
		}
		
		JMenuItem removeItem = new JMenuItem(name);
		removeItem.addActionListener(new RemoveAccountListener(creds, id));
		removeUser.add(removeItem);
	}
	
	public static void main(String[] args){
		new MainFrame().setVisible(true);
	}
}