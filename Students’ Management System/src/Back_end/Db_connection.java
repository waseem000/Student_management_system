package Back_end;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db_connection
{
	private static Connection connection = null;
	private static Statement statement = null;
	private String username="root";
	private String password="";
	
	public void create_connection() throws ReflectiveOperationException, SQLException
	{
		 Class.forName("com.mysql.jdbc.Driver");
		 connection = DriverManager.getConnection("jdbc:mysql://localhost/test",username,password);
		 System.out.println("database connected and ready");
		 get_statmnet_object();
	}
	public Statement get_statmnet_object()
	{
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;
	}
	public Integer execute_statment(String s)
	{
		int success=0;
		try {
			success=statement.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	public ResultSet execute_query(String sql)
	{
		ResultSet result= null;
		
		try {
			result=statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public void end_connection()
	{
		try
	      {
	        if(connection != null)
	          connection.close();
	      }
	      catch(SQLException e)
	      {
	        // connection close failed.
	        System.err.println(e);
	      }
	    }
  
}