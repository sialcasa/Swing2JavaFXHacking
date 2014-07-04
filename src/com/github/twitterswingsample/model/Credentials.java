package com.github.twitterswingsample.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Handles consumerkey, consumersecret, accesstoken, and accesstokensecret for access to a twitter account.
 * 
 * @author sourcefranke
 */
public class Credentials {
	
	private Connection c;
	private Statement stmt;
	
	public Credentials() throws ClassNotFoundException, SQLException {
		createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS login " +
				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"name VARCHAR(50) NOT NULL, " + 
				"apikey VARCHAR(50) NOT NULL, " + 
				"apisecret VARCHAR(50) NOT NULL, " + 
				"accesstoken VARCHAR(50) NOT NULL, " + 
				"accesstokensecret VARCHAR(50) NOT NULL)");
		closeStatement();
	}
	
	public List<Integer> getIDs() throws ClassNotFoundException, SQLException {
		List<Integer> list = new LinkedList<>();
		createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id FROM login");
		while (rs.next()) {
			list.add(rs.getInt("id"));
		}
		closeStatement();
		
		return list;
	}
	
	public int addUser(String name, String apikey, String apisecret,
			String accesstoken, String accesstokensecret)
			throws ClassNotFoundException, SQLException {
		createStatement();
		stmt.executeUpdate("INSERT INTO login (name, apikey, apisecret, accesstoken, accesstokensecret)"
			+ " VALUES('" + name + "', '" + apikey + "', '" + apisecret + "', '" + accesstoken + "', '" + accesstokensecret + "')");
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()) {
			int id = rs.getInt(1);
			closeStatement();
			return id;
		}
		closeStatement();
		return -1;
	}
	
	public void removeUser(int id) throws ClassNotFoundException, SQLException {
		createStatement();
		stmt.executeUpdate("DELETE FROM login WHERE id=" + id);
		closeStatement();
	}

	public String getName(int id) throws ClassNotFoundException, SQLException {
		createStatement();
		ResultSet rs = stmt.executeQuery("SELECT name FROM login WHERE id=" + id);
		if(rs.next()) {
			String name = rs.getString("name");
			closeStatement();
			return name;
		}
		
		closeStatement();
		return "";
	}

	public List<String> getNames() throws ClassNotFoundException, SQLException {
		List<String> list = new LinkedList<>();
		createStatement();
		ResultSet rs = stmt.executeQuery("SELECT name FROM login");
		while (rs.next()) {
			list.add(rs.getString("name"));
		}
		
		closeStatement();
		return list;
	}
	
	public Twitter getTwitter(int id) throws ClassNotFoundException, SQLException {
		createStatement();
		ResultSet rs = stmt.executeQuery("SELECT apikey, apisecret, accesstoken, accesstokensecret FROM login WHERE id=" + id);
		if(rs.next()) {
			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(rs.getString("apikey"), rs.getString("apisecret"));
			twitter.setOAuthAccessToken(new AccessToken(rs.getString("accesstoken"), rs.getString("accesstokensecret")));
			
			closeStatement();
			return twitter;
		}

		closeStatement();
		return null;
	}
	
	public List<Twitter> getTwitters() throws ClassNotFoundException, SQLException {
		List<Twitter> list = new LinkedList<>();
		createStatement();
		ResultSet rs = stmt.executeQuery("SELECT apikey, apisecret, accesstoken, accesstokensecret FROM login");
		while(rs.next()) {
			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(rs.getString("apikey"), rs.getString("apisecret"));
			twitter.setOAuthAccessToken(new AccessToken(rs.getString("accesstoken"), rs.getString("accesstokensecret")));
			list.add(twitter);
		}

		closeStatement();
		return list;
	}
	
	private void createStatement() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:t4jss.db");
		stmt = c.createStatement();
	}
	
	private void closeStatement() throws ClassNotFoundException, SQLException {
		stmt.close();
		c.close();
	}
}