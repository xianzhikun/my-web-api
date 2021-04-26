package af.sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AfSqlInsert
{
	String table = "";
	
	List<String> names = new ArrayList<>();
	List<String> values =  new ArrayList<>();
	
	public AfSqlInsert(String table)
	{
		this.table = table;
	}
	
	// 不提供列名，则SQL里只写值，不写列名
	public void add(String value)
	{
		values.add(value);
	}
	
	// 提供列名和值
	public void add(String name, String value)
	{
		names.add(name);
		values.add(value);
	}
	
	// 按类型
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
		String ccc = "";
		if(names.size() > 0)
		{
			if(names.size() != values.size())
				return "SQL拼写出错! 列和值个数不一致!";
			
			ccc = "(";
			for(int i=0; i<names.size(); i++)
			{
				String name = names.get(i);
				if(i > 0) ccc += ",";
				ccc += AfSql.name( name );
			}
			ccc += ")";
		}
		
		String vvv = "";
		if(values.size() > 0)
		{
			vvv = "(";
			for(int i=0; i<values.size(); i++)
			{
				String str = values.get(i);
				if(i > 0) vvv += ",";
				vvv += AfSql.value( str );
			}
			vvv += ")";
		}
		
		String sql = " INSERT INTO " + AfSql.name(table) 
			+ ccc
			+ " VALUES "
			+ vvv;
		
		return sql;
	}
}
