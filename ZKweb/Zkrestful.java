package ajax;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/restful")
public abstract class Zkrestful extends HttpServlet
{
	public abstract void excute(String a,HashMap<String, String> suffix);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String h=readAsText(req.getInputStream(), "utf-8");
		
		excute(h,getitem(req));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String h=readAsText(req.getInputStream(), "utf-8");
		excute(h,getitem(req));
	}
	
	public HashMap<String, String> getitem(HttpServletRequest req)
	{
		HashMap<String, String> urlsuffix=new HashMap<String, String>();
		String value=req.getQueryString();
		if(value==null)return null;
		String[] asd=value.split("&");
		for(String a:asd)
		{
			String[] b=a.split("=");
			urlsuffix.put(b[0], b[1]);
		}
		return urlsuffix;
	}
	public String readAsText(InputStream streamIn, String charset)
			throws IOException 
	{
		ByteArrayOutputStream cache = new ByteArrayOutputStream(1024*16);  
        byte[] data = new byte[1024];  
        while (true)
        {
        	int n = streamIn.read(data); // n: 实际读取的字节数
        	if(n < 0) break; // 连接已经断开
        	if(n == 0) continue;// 数据未完 // TODO: 要防止超时 

        	// 缓存起来
        	cache.write(data, 0, n);        	
        	if(cache.size() > 1024*512) // 上限, 最多读取512K
        		break;
        }  
        
        return cache.toString(charset);
	}
	
	
}
