package my;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import af.sql.AfSqlWhere;

public class TestQuery
{
	

	
	public static void main(String[] args) throws Exception
	{
		// 注册MySQL驱动 (可以省略这一步)
		// Class.forName("com.mysql.jdbc.Driver");

		// 连接MySQL服务器
		String username= "root";
		String password = "a1b2c3";
		String connectionUrl = "jdbc:mysql://127.0.0.1:3306/af_school?useUnicode=true&characterEncoding=UTF-8";
		
		Connection conn = DriverManager.getConnection(connectionUrl, username, password);
		System.out.println("连接成功!");
		
		//////////////////////////////////////////////	
		
		// 构造查询语句
		AfSqlWhere where = new AfSqlWhere();
		where.add("id>20180003");
		where.add2("sex", true);
		
		String sql = "SELECT * FROM student" 
				+ where
				+ " ORDER BY id ASC ";
		System.out.println("SQL:" + sql);
		
		// 数据库查询, Statement语句  ResultSet结果集
		Statement stmt = conn.createStatement(); 
		ResultSet rs = stmt.executeQuery("SELECT * FROM student");
		
		//如果有数据，rs.next()返回true
	    while(rs.next())
	    {
	    	// 取出这一行记录
	    	int id = rs.getInt("id");
	    	String name = rs.getString("name");
	    	String phone = rs.getString("phone"); // 可能为null
	    	Date birthday = rs.getDate("birthday");
	    	
	    	System.out.println(id + "\t" + name + "\t" + phone );	           
	    }
	    
	    //////////////////////////////////////////////
		conn.close();

	}

}
