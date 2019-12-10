package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMag {
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		//register and load driver
		
		Class.forName("com.mysql.jdbc.Driver");
		
		//create connection
		
		Connection con=null;  //create object 
		
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shopping","root","");
		
		if(con!=null) //check the connection
		{
			return con;
		}
		else
		{
			return null;
		}
	}

}
