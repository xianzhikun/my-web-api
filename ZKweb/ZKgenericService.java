package genirc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Servlet implementation class ZKgenericService
 */
@WebServlet("/ZKgenericService")
public class ZKgenericService extends HttpServlet {
	public HashMap<String, String> config=new HashMap<String, String>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		try
		{
			handlereq(req,resp);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void handlereq(HttpServletRequest req, HttpServletResponse resp) throws DocumentException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		loadconfig();
		String servletname=req.getServletPath();
		int index=servletname.lastIndexOf('/');
		int index2=servletname.lastIndexOf('.');
		String apiname=servletname.substring(index+1,index2);
		String classname=config.get(apiname);
		if(classname==null)
		{
			resp.sendError(404);
		}
		else
		{
			ZKgenericApi instance=null;
			Class apiclass=Class.forName(classname);
			instance=(ZKgenericApi) apiclass.newInstance();
			instance.req=req;
			instance.resp=resp;
			instance.excute(req, resp);
		}
	}
    public void loadconfig() throws DocumentException, IOException
    {
    	InputStream stream = this.getClass().getResourceAsStream("/ZKserviceConfig.xml");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(stream);
		stream.close();
		
		Element root = doc.getRootElement();
		List<Element> xServiceList = root.elements("service");
		for (Element e : xServiceList)
		{
			String name = e.attributeValue("name");
			String clazzName = e.attributeValue("class");			
			config.put(name, clazzName);
		}
    }
    
}
