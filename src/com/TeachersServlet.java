package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Servlet implementation class TeachersServlet
 */
public class TeachersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private SessionFactory factory;
	
    public TeachersServlet() {
        super();
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
        factory = meta.getSessionFactoryBuilder().build();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String button = request.getParameter("button");
		if ("Teachers lists".equals(button)) {

			Session session = factory.openSession();
			String hql = "Select t.teachers FROM Teacher t";
			TypedQuery<String> query = session.createQuery(hql);
			List<String> teachers = query.getResultList();
	        session.close();
			
			RequestDispatcher rd = request.getRequestDispatcher("teacher.html");
			PrintWriter out = response.getWriter();
			rd.include(request, response);
			
			for (String teacher: teachers) {
				out.println(" <br/> " + teacher);	
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("teacher.html");
		String teacher = request.getParameter("teacher");
		
		Teacher teach = new Teacher(teacher);

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		Integer empNo = (Integer) session.save(teach);
		transaction.commit();
	    session.close();

		
		PrintWriter out = response.getWriter();
		rd.include(request, response);
		out.println("<br/> <centre> <span style = 'color:blue'> " + empNo + " " + teacher +  " added in Teacher's list</span> </centre>");
	}

}
