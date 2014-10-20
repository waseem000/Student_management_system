package Back_end;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.util.ArrayList;

public class User extends Db_connection{

	private int id;
	private String first_name;
	private String last_name;
	private String user_name;
	private String password;
	private String email;
	private int permission_type;  // 0 admin, 1 staff, 2 student
	private Db_connection database = new Db_connection();
	private String sql_statment =null;
	private ResultSet res;
	
	public boolean create_user(String first_name, String last_name, String user_name, String password, String email, int permission_type )
	{
		int success ;
		 sql_statment="insert into users(first_name,last_name,user_name,password,email,permission_type) values('"+first_name+"'"+","+"'"+ last_name+"',"+"'"+ user_name+"',"+ "'"+password+"',"+"'"+email+"',"+ "'"+permission_type+"')";
		 success=database.execute_statment(sql_statment);
		if(success>1)
		{
			System.out.print(success);
			return true;
		}
		else
		{
			System.out.print(success);
			return false;
		}
			
	//	return true;
	}
	public boolean update_user(int id,String first_name, String last_name, String user_name, String password, String email, int permission_type )
	{
		int success ;
		 sql_statment="UPDATE  users SET first_name = '"+first_name+"', last_name = '"+ last_name+"',user_name = '"+user_name+"', password = '" +password+"' ,email = '"+ email+"' ,	permission_type= '"+permission_type+"' WHERE  user_id = '"+id+"'";
		System.out.println(sql_statment);
		 success=database.execute_statment(sql_statment);
		if(success>1)
		{
			System.out.print(success);
			return true;
		}
		else
		{
			System.out.print(success);
			return false;
		}
	}
	public boolean delete_user(int id)
	{
		int success ;
		 sql_statment="delete from users where user_id = '"+id+"'";
		 success=database.execute_statment(sql_statment);
		if(success>1)
		{
			System.out.print(success);
			return true;
		}
		else
		{
			System.out.print(success);
			return false;
		}
	}
	public int get_user_id(String email, String user_name,int permission)
	{
		int id= 0;
		sql_statment="select user_id from users where email ='"+email+"' and user_name ='"+user_name+"' and permission_type = '"+permission+"'";
	//	System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {
					id=	res.getInt("user_id");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	 System.out.println(first_name);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return id;
	}
	public String get_first_name(int id) {
		String first_name= null;
		sql_statment="select first_name from users where user_id ='"+id+"'";
	//	System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {
					first_name=	res.getString("first_name");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	 System.out.println(first_name);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return first_name;
	}
	
	public String get_last_name(int id) {
		String last_name= null;
		sql_statment="select last_name from users where user_id ='"+id+"'";
	//	System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {
					last_name=	res.getString("last_name");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(last_name);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return last_name;
	}
	
	public String get_user_name(int id) {
		
		String user_name= null;
		sql_statment="select user_name from users where user_id ='"+id+"'";
	//	System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {
					 user_name=	res.getString("user_name");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(user_name);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user_name;
	}
	
	public String get_password(int id) {
		
		String password= null;
		sql_statment="select password from users where user_id ='"+id+"'";
	//	System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {
					 password=	res.getString("password");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/// System.out.println(password);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
	}
	
	public String get_email(int id) {
		
		String email= null;
		sql_statment="select email from users where user_id ='"+id+"'";
	//	System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {
					 email=	res.getString("email");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(email);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}
	
	public int get_permission(int id) {
		
		int permission_type= -1;
		sql_statment="select permission_type from users where user_id ='"+id+"'";
	//	System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {
					 permission_type=	res.getInt("permission_type");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(permission_type);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permission_type;
	}
	public ArrayList<String> get_user(int id)
	{
		ArrayList<String> user= new ArrayList<String>();
		sql_statment="select * from users where user_id ='"+id+"'";
		//System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {

					 user.add(Integer.toString(res.getInt("user_id")));
					 user.add(res.getString("first_name"));
					 user.add(res.getString("last_name"));
					 user.add(res.getString("user_name"));
					 user.add(res.getString("password"));
					 user.add(res.getString("email"));
					 user.add(Integer.toString(res.getInt("permission_type")));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public ArrayList<String> get_all_user()
	{
		ArrayList<String> users= new ArrayList<String>();
	//	ArrayList<ArrayList<String>> all_users= new ArrayList<ArrayList<String>>();
		sql_statment="select * from users";
	//	System.out.println(sql_statment);
				res=database.execute_query(sql_statment);
		 try {
			while(res.next())
			  {
				 try {
					 users.add(Integer.toString(res.getInt("user_id")));
					 users.add(res.getString("first_name"));
					 users.add(res.getString("last_name"));
					 users.add(res.getString("user_name"));
					 users.add(res.getString("password"));
					 users.add(res.getString("email"));
					 users.add(Integer.toString(res.getInt("permission_type")));
					 
					// all_users.add(user);
					 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
}

