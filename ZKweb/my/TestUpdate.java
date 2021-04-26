package my;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import af.sql.AfSqlInsert;
import af.sql.AfSqlUpdate;
import af.sql.AfSqlWhere;

public class TestUpdate
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
		
		
		AfSqlUpdate update = new AfSqlUpdate("student");
		update.add("birthday", "1997-10-02");
		
		AfSqlWhere where = new AfSqlWhere();
		where.add2("id", 20180001);
		
		String sql = update.toString() + where;
		System.out.println("SQL: "+ sql);
	
		
		Statement stmt = conn.createStatement(); 		
		stmt.execute(sql);
	    int count = stmt.getUpdateCount();
	    System.out.println("受影响的行数为: " + count);
	    
	    //////////////////////////////////////////////
		conn.close();

	}

}
