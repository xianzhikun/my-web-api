package jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ZKc3p0Connection
{
	public ComboPooledDataSource cpds;
	public	ZKc3p0Connection()	
	{
		 cpds = new ComboPooledDataSource();
	}
	public Connection c3connect() throws SQLException
	{
		// 一个DataSource指代一个数据源, 内部有一个连接池
		// 一个Connection代表一次访问
		Connection conn = cpds.getConnection();
		return conn;
	}
	public void close()
	{
		cpds.close();
	}
	public List<Object> excutequery(String query,Class target) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException
	{
		Method[] me=target.getMethods();
		ResultSet set=excutequery(query);
		ResultSetMetaData mata=set.getMetaData();
		HashMap<String,Method> red=new HashMap<String, Method>();
		int colum=mata.getColumnCount();
		String[] names=new String[colum];
		Method[] methods=new Method[colum];

		for(int i=0;i<colum;i++)
		{
			names[i]=mata.getColumnName(i+1);
		}
		
		for(int i=0;i<colum;i++)
		{
			for(int j=0;j<me.length-1;j++)
			{
				String a=me[j].getName();
				String b=a.toLowerCase().substring(0,3);
				if(b.equals("set"))
				{
					String c=a.toLowerCase().substring(3);
					if(c.equals(names[i]))
					{
//						red.put(names[i], methods[j]);
						methods[i]=me[j];
					}
				}
			}
		}
		ArrayList<Object> value=new ArrayList<Object>();
		while(set.next())
		{
			Object a=target.newInstance();
			for(int j=0;j<colum;j++)
			{
				String hh=set.getString(names[j]);
				Class[] parameterTypes = methods[j].getParameterTypes();
				Class c = parameterTypes[0];
				Object arg0 = null;
				String val=set.getString(names[j]);
				if(c.equals(String.class))
				{
					
					arg0=val;
				}
				else if(c.equals(int.class)||c.equals(Integer.class))
				{
					arg0=Integer.valueOf(val);		
				}
				else if(c.equals(double.class)||c.equals(Double.class))
				{
					arg0=Double.valueOf(val);				
				}
				else if(c.equals(long.class)||c.equals(Long.class))
				{
					arg0=Long.valueOf(val);					
				}
				else if(c.equals(Date.class))
				{
					SimpleDateFormat dat=new SimpleDateFormat("yyyy-MM-dd");
					if(val!=null)arg0=dat.parse(val);
					else arg0=null;
				}
				methods[j].invoke(a, arg0);
			}
			value.add(a);
		}
		
		return value;
	}
	
	public ResultSet excutequery(String query) throws SQLException
	{
		Connection a=c3connect();
		Statement sat=a.createStatement();
		return sat.executeQuery(query);
	}
}
