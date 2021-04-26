package freemarker;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.TemplateException;

/**
 * Servlet implementation class Zkgen
 */
//@WebServlet("/Zkgen")
public abstract class ZKhtmlGen extends HttpServlet {
	
	@Override
	public void init() throws ServletException
	{
		String modelroot=getServletContext().getRealPath("/");
		ZK_html_gen.hgen.init(modelroot);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String modelroot=getServletContext().getRealPath("/");
		String modelpath=modelpath(request, response);
		String genpath=genpath(request, response);
		model=genmodel(request,response);
		try
		{
			ZK_html_gen.hgen.genhtml(modelpath, genpath, model);
		} catch (TemplateException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	Object model;
	public abstract String genpath(HttpServletRequest request, HttpServletResponse response);
	public abstract String modelpath(HttpServletRequest request, HttpServletResponse response);
	public abstract Object genmodel(HttpServletRequest request, HttpServletResponse response);
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
