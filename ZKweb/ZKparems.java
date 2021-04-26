package my;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ZKparems
{
	
	public static HashMap<String, String> getparems(HttpServletRequest request){
		HashMap<String, String> has=new HashMap<String, String>();
		String requri=request.getQueryString();
		if(requri==null)return has;
		String[] paremarr=requri.split("&");
		for(String par : paremarr)
		{
			String[] items=par.split("=");
			if(items[1].indexOf('%')>=0)
			{
				try
				{
					items[1]=URLDecoder.decode(items[1],"utf-8");
				} catch (UnsupportedEncodingException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			has.put(items[0], items[1]);
		}
		
//		 request.getParameterMap();
		return has;
	}
	public static HashMap<String, String> getparems(String query)
	{
		HashMap<String, String> has=new HashMap<String, String>();
		if(query==null)return has;
		String[] paremarr=query.split("&");
		for(String par : paremarr)
		{
			String[] items=par.split("=");
			if(items[1].indexOf('%')>=0)
			{
				try
				{
					items[1]=URLDecoder.decode(items[1],"utf-8");
				} catch (UnsupportedEncodingException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			has.put(items[0], items[1]);
		}
		
//		 request.getParameterMap();
		return has;
	}
}
