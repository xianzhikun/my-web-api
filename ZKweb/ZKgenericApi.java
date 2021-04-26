package genirc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ZKgenericApi
{
	public abstract String excute(HttpServletRequest req,HttpServletResponse resp) throws IOException;
	public HttpServletRequest req;
	public HttpServletResponse resp;
}
