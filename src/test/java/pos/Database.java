package pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.openqa.selenium.WebDriver;

import pos.Base;

public class Database extends Base {
	
	static Connection con;
	private static Statement statement;
	String dbClass = "com.mysql.jdbc.Driver";
	
	public Database(WebDriver driver) {
		super(driver);
	}
	
	public void connectToDb(String db_url, String db_user, String db_pass) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(dbClass).newInstance();
		con = DriverManager.getConnection(db_url, db_user, db_pass);
	}
	
	public List<String> executeQuery(String query, int columns) throws SQLException {
		statement = con.createStatement();
		List<String> resultSet = new ArrayList<String>();
		ResultSet res;
		res = statement.executeQuery(query);
		int i = 1;
		
		while (res.next()) {
	
			for (int ct = 1; ct <= columns; ct++) {
				resultSet.add(res.getString(ct));
			}
			
			i++;
		}
			
		return resultSet;
	}
	
	public void executeUpdate(String query) throws SQLException {
		statement = con.createStatement();
		statement.executeUpdate(query);
	}

	public void closeDbConnection() throws SQLException {
		if (con != null) {
			con.close();
		}
	}
	
}
