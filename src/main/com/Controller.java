package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


@WebServlet("/Controller")
public class Controller  extends HttpServlet{

	public Controller() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection conn=null;
		Statement stmt;
		ArrayList all=new ArrayList();
		SessionFactory factory=new Configuration.configure().addAnnotatedClass(employee.class).buildSessionFactory();
		Session session=new factory.getCurrentSession();
		try
		{
		session.beginTransaction();
		Query query=new session.createQuery("from employee");
		all=(ArrayList) query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		session.close();
		factory.close();
		out.println("select completed.");
		request.setAttribute("All Members", all);
		RequestDispatcher rd=request.getRequestDispatcher("Display.jsp");
		rd.forward(request, response);
	}
}
