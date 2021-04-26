package freemarker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class ZkssiController
 */
//@WebServlet("*.html")
public abstract class ZKsimpleMvc extends HttpServlet
{
	
	Configuration cfg;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String servletPath = req.getServletPath();
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		try{
			HashMap<String,Object> model = new HashMap<String,Object>();
			Template tp=null;
			if(excute(model,req,resp)!=null)
			{
				 tp = cfg.getTemplate(excute(model,req,resp));
			}
			else{
				 tp = cfg.getTemplate(servletPath);
			}
			
			excute(model,req,resp);
			tp.process(model, resp.getWriter()); // 输出给客户端
		}catch(Exception e)
		{
			e.printStackTrace();
			resp.sendError(500, e.getMessage());
		}
	}
	@Override
	public void init() throws ServletException
	{
		File appRoot = new File(getServletContext().getRealPath("/"));
		
		cfg = new Configuration(Configuration.VERSION_2_3_28);
		try
		{
			cfg.setDirectoryForTemplateLoading(appRoot);
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 设置模板根目录
		
	}
	public abstract String excute(HashMap<String, Object> model,HttpServletRequest req, HttpServletResponse resp);
}
