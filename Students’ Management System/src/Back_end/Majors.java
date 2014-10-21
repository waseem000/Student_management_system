package Back_end;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.omg.CosNaming.IstringHelper;

public class Majors { 

	//you can't but major offered false if number of student is >0
	private int major_id;
	private String 	name;
	private int total_credits;
	private int number_of_student;
	private boolean is_offered ;
	private int number_of_courses;
	private Db_connection database = new Db_connection();
	private String sql_statment =null;
	private ResultSet res;


	public boolean add_major(String name, int total_credits,int number_of_students,int number_of_courses, int is_offered)
	{

		int success ;
		boolean conflict;
		conflict=conflict_check.add_major_conflict(name);
		if(conflict)
		{
			sql_statment="insert into majors(name,credits,number_of_students,number_of_courses,is_offered) values('"+name+"'"+","+"'"+ total_credits+"',"+"'"+ number_of_students+"','"+number_of_courses+"','"+is_offered+"')";
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
		else
		{
			System.out.println("ERROR major already exists");
			return false;
		}

	}
	public boolean update_major(int major_id, String name, int total_credits,int number_of_students,int number_of_courses, int is_offered)
	{
		int success ;
		sql_statment="UPDATE  majors SET name = '"+name+"', credits = '"+ total_credits+"',number_of_students = '"+number_of_students+"', number_of_courses= '"+number_of_courses+"', is_offered = '" +is_offered+"' WHERE  major_id = '"+major_id+"'";
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
	public boolean delete_major(int major_id) // need to make sure that there are no courses and students under the major before the delete
	{
		boolean conflict = conflict_check.delete_major_conflict(major_id);
		if(conflict)
		{
			int success ;
			sql_statment="delete from majors where major_id = '"+major_id+"'";
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
		else
		{
			System.out.println("ERROR  cant delete major, students are registered in this major");
			return false;
		}
	}
	public int get_number_of_student(int major_id) {

		int number_of_student= 0;
		sql_statment="select number_of_students from majors where major_id ='"+major_id+"'";
		//	System.out.println(sql_statment);
		res=database.execute_query(sql_statment);
		try {
			while(res.next())
			{
				try {
					number_of_student=	res.getInt("number_of_students");
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
		return number_of_student;
	}
	public boolean add_student_to_major(int major_id) {

		int is_offered = get_is_offered(major_id);
		if(is_offered==1)
		{
			int number_of_student=get_number_of_student(major_id)+1;

			sql_statment="UPDATE  majors SET number_of_students = '"+number_of_student+"' where major_id= '"+major_id+"'";
			System.out.println(sql_statment);
			database.execute_statment(sql_statment);
			return true;
		}
		else
		{
			System.out.println("major is not offered");
			return false;
		}

	}
	public int get_number_of_courses(int major_id) {

		int number_of_courses= 0;
		sql_statment="select number_of_courses from majors where major_id ='"+major_id+"'";
		//	System.out.println(sql_statment);
		res=database.execute_query(sql_statment);
		try {
			while(res.next())
			{
				try {
					number_of_courses=	res.getInt("number_of_courses");
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
		return number_of_courses;
	}
	public void add_course_to_major(int major_id) {

		int number_of_courses=get_number_of_courses(major_id)+1;
		int is_offered = get_is_offered(major_id);
		if(is_offered==1)
		{
			sql_statment="UPDATE  majors SET number_of_courses = '"+number_of_courses+"' where major_id= '"+major_id+"'";
			System.out.println(sql_statment);
			database.execute_statment(sql_statment);
		}
		else
		{
			System.out.println("major is not offered");
		}

	}
	public int get_is_offered(int major_id) {

		int is_offered= 1;

		sql_statment="select is_offered from majors where major_id ='"+major_id+"'";
		//	System.out.println(sql_statment);
		res=database.execute_query(sql_statment);
		try {
			while(res.next())
			{
				try {
					is_offered=	res.getInt("is_offered");
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
		return is_offered;
	}
	public void set_is_offered(int major_id,int is_offered) {

		int number_of_students = get_number_of_student(major_id);
		int number_of_courses = get_number_of_courses(major_id);
		if((number_of_students>0 && number_of_courses>0 && is_offered==1)||(number_of_students==0 && number_of_courses>0 && is_offered==1)||(number_of_students>0 && number_of_courses==0 && is_offered==1) )
		{
			sql_statment="UPDATE majors SET is_offered = '"+is_offered+"' where major_id= '"+major_id+"'";
			System.out.println(sql_statment);
			database.execute_statment(sql_statment);
			System.out.println("updated");
		}
		else if(number_of_students==0 && number_of_courses==0)
		{
			sql_statment="UPDATE majors SET is_offered = '"+is_offered+"' where major_id= '"+major_id+"'";
			System.out.println(sql_statment);
			database.execute_statment(sql_statment);
			System.out.println("updated 0,0 ");
		}
		else
		{
			System.out.println("student or courses are in this major ");
		}
	}
	public int get_major_id(String major_name) {

		int id= 0;
		sql_statment="select major_id from majors where name ='"+major_name+"'";
		System.out.println(sql_statment);
		res=database.execute_query(sql_statment);
		try {
			while(res.next())
			{
				try {
					id=	res.getInt("major_id");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}
	public String get_name(int major_id) {


		String name= null;
		sql_statment="select name from majors where major_id ='"+major_id+"'";
		//	System.out.println(sql_statment);
		res=database.execute_query(sql_statment);
		try {
			while(res.next())
			{
				try {
					name=	res.getString("name");
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

		return name;
	}
	public int get_total_credits(int major_id) {

		int total_credits= 0;
		sql_statment="select credits from majors where major_id ='"+major_id+"'";
		//	System.out.println(sql_statment);
		res=database.execute_query(sql_statment);
		try {
			while(res.next())
			{
				try {
					total_credits=	res.getInt("credits");
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

		return total_credits;
	}

	public ArrayList<String> get_major(int major_id)
	{
		ArrayList<String> major= new ArrayList<String>();

		sql_statment="select * from majors where major_id ='"+major_id+"'";
		//System.out.println(sql_statment);
		res=database.execute_query(sql_statment);
		try {
			while(res.next())
			{
				try {

					major.add(Integer.toString(res.getInt("major_id")));
					major.add(res.getString("name"));
					major.add(Integer.toString(res.getInt("credits")));
					major.add(Integer.toString(res.getInt("number_of_students")));
					major.add(Integer.toString(res.getInt("number_of_courses")));
					major.add(Boolean.toString(res.getBoolean("is_offered")));

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return major; 
	}

	public ArrayList<String> get_all_majors()
	{
		ArrayList<String> majors= new ArrayList<String>();

		sql_statment="select * from majors ";
		//System.out.println(sql_statment);
		res=database.execute_query(sql_statment);
		try {
			while(res.next())
			{
				try {

					majors.add(Integer.toString(res.getInt("major_id")));
					majors.add(res.getString("name"));
					majors.add(Integer.toString(res.getInt("credits")));
					majors.add(Integer.toString(res.getInt("number_of_students")));
					majors.add(Integer.toString(res.getInt("number_of_courses")));
					majors.add(Boolean.toString(res.getBoolean("is_offered")));

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return majors; 
	}
	public void drop_student_from_major(int major_id) {
		// TODO Auto-generated method stub
		
		int number_of_student=get_number_of_student(major_id)-1;

		sql_statment="UPDATE  majors SET number_of_students = '"+number_of_student+"' where major_id= '"+major_id+"'";
		System.out.println(sql_statment);
		database.execute_statment(sql_statment);
		
	}


}
