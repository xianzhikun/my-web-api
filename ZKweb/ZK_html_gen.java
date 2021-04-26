package freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public  class ZK_html_gen
{
	private Configuration cfg;
	private String modelroot;
	
	public static ZK_html_gen hgen=new ZK_html_gen();
	public  void init(String modelroot)
	{
		if(this.modelroot!=null)return;
		
		this.modelroot=modelroot;
		cfg = new Configuration(Configuration.VERSION_2_3_28);
		File appRoot=new File(modelroot);
		try
		{
			cfg.setDirectoryForTemplateLoading(appRoot);
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public  void genhtml(String modelpath,String genpath,Object model ) throws TemplateException, IOException
	{
		Template tp=cfg.getTemplate(modelpath);
		FileWriter ww = new FileWriter(new File(modelroot+genpath+".html"));
		tp.process(model, ww);
		System.out.println("测试成功！！");
	}
	
}
