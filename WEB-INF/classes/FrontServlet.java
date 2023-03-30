package etu1820.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Vector;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import etu1820.framework.Mapping;
import etu1820.framework.Utility;

public class FrontServlet extends HttpServlet 
{
    HashMap<String,Mapping> mappingUrls = new HashMap<>();
    
    // function init
    public void init() throws ServletException
    {
        super.init();
        try
        {
            Vector<String> paths = Utility.readPackage(getServletContext().getResource(".").toURI().getPath() + "WEB-INF/classes");
            for (String path : paths)
            {
                Vector<Utility> utilities = Utility.readAnnotation(path);
                for (Utility utility : utilities)
                {
                    mappingUrls.put(utility.getValue(), new Mapping(utility.getClassName(), utility.getMethod()));
                }
            }
        }
        catch (URISyntaxException | MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    // method doGet
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
        try 
        {
            super.doGet(req, res);
            processRequest(res, req);
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }

    // method doPost
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
        try 
        {
            super.doPost(req, res);
            processRequest(res, req);
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
    }

    // function processRequest
    public void processRequest(HttpServletResponse res, HttpServletRequest req) throws Exception 
    {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println(mappingUrls.get(req.getServletPath()).getClassName());
        out.println("</body></html>");
    }
}