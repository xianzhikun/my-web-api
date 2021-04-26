package af.sql;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AfSql
{
	static SimpleDateFormat sdf_dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdf_d = new SimpleDateFormat("yyyy-MM-dd");
	
	// 名字加反引号
	public static String name(String str)
	{
		if(str.indexOf('.')>=0) return str; // "db.table.column"
		if(str.indexOf('`')>=0) return str;
		return "`" + str + "`";		
	}
	
	// 值加单引号
	public static String value(String str)
	{
		if(str.startsWith("\'")) return str;
		return "'" + str + "'";
	}
	
	public static String date(Date d)
	{
		return sdf_d.format(d);
	}
	
	public static String datetime(Date d)
	{
		return sdf_dt.format(d);
	}
	
}
