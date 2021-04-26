package af.sql;

import java.util.ArrayList;
import java.util.List;

public class AfSqlUpdate
{
	String table = "";
	
	List<String> sss = new ArrayList<>();
	
	public AfSqlUpdate(String table)
	{
		this.table = table;
	}
	
	public void add(String expr)
	{
		sss.add( expr );
	}
	
	public void add(String name, String value)
	{
		sss.add( AfSql.name(name) + "=" + AfSql.value(value));
	}
	
	////////////////////////////////
	public void add2(String name, String value)
	{
		add(name, value);
	}	
	public void add2(String name, Integer value)
	{
		add(name, value.toString());
	}
	public void add2(String name, Long value)
	{
		add(name, value.toString());
	}
	public void add2(String name, Short value)
	{
		add(name, value.toString());
	}
	public void add2(String name, Boolean value)
	{
		add(name, value?"1":"0");
	}	
	
	@Override
	public String toString()
	{
		String sql = " UPDATE " + AfSql.name(table) 
			+ " SET ";

		for(int i=0; i<sss.size(); i++)
		{
			if(i > 0) sql += ",";
			sql += sss.get(i);
		}	
		sql += " ";
		
		return sql;
	}
}
