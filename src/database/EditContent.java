package database;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class EditContent {

	public EditContent() {
		
	}
	
	private Connection getConnection() throws NamingException, SQLException {
		
		Context initCtx = new InitialContext();
		Context ctx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) ctx.lookup("jdbc/java_connect");
		
		return (ds.getConnection());
	}
	
	public List<Zapisek> getZapisky(String user) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Zapisek> zapisky = new ArrayList<Zapisek>();
		
		try {
			String query = "SELECT * FROM content WHERE autor = ?";
			connection = getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, user);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				zapisky.add(new Zapisek(rs.getInt("id"), rs.getString("nadpis"), rs.getString("obsah")));
			}
		} catch (NamingException e) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, e);
		} catch (SQLException eSQLException) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, eSQLException);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		
		return zapisky;
	}
	
	public Zapisek getZapisek(int id, String user) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Zapisek zapisek = null;
		
		try {
			String query = "SELECT * FROM content WHERE id = ? AND autor = ?";
			connection = getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setString(2, user);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				zapisek = new Zapisek(rs.getInt("id"), rs.getString("nadpis"), rs.getString("obsah"));
			}
		} catch (NamingException e) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, e);
		} catch (SQLException eSQLException) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, eSQLException);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		
		return zapisek;
	}
	
	
	public void setZapisek(int id, String nadpis, String obsah, String user) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			String query = "UPDATE content SET nadpis = ?, obsah = ? WHERE id = ? AND autor = ?";
			connection = getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, nadpis);
			stmt.setString(2, obsah);
			stmt.setInt(3, id);
			stmt.setString(4, user);
			stmt.executeUpdate();
		} catch (NamingException e) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, e);
		} catch (SQLException eSQLException) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, eSQLException);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void addZapisek(String nadpis, String obsah, String user) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			String query = "INSERT INTO content (nadpis, obsah, autor) VALUES (?, ?, ?)";
			connection = getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setString(1, nadpis);
			stmt.setString(2, obsah);
			stmt.setString(3, user);
			stmt.executeUpdate();
		} catch (NamingException e) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, e);
		} catch (SQLException eSQLException) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, eSQLException);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void removeZapisek(int id, String user) throws SQLException {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			String query = "DELETE FROM content WHERE id = ? AND autor = ?";
			connection = getConnection();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setString(2, user);
			stmt.executeUpdate();
		} catch (NamingException e) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, e);
		} catch (SQLException eSQLException) {
			Logger.getLogger(EditContent.class.getName()).log(Level.SEVERE, null, eSQLException);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
