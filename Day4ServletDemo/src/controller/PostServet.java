package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostServet
 */
@WebServlet("/postservlet")
public class PostServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");
    	PrintWriter out = response.getWriter();
		out.println("<html> <h1>This was your SECOND MOVIE Servlet result page</h1> <ul>");
		out.println("<li> Name is "+name);
		out.println("<li> Nick Name is "+nick);
		out.println("</ul></html>");
		
	}

}
