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
 * Servlet implementation class SubjectsServlet
 */
public class SubjectsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private SessionFactory factory;
	
    public SubjectsServlet() {
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
		if ("Subject lists".equals(button)) {

			Session session = factory.openSession();
			String hql = "Select s.subjects FROM Subject s";
			TypedQuery<String> query = session.createQuery(hql);
			List<String> subjects = query.getResultList();
	        session.close();
			
			RequestDispatcher rd = request.getRequestDispatcher("subjects.html");
			PrintWriter out = response.getWriter();
			rd.include(request, response);
			
			for (String subj: subjects) {
				out.println(" <br/> " + subj);	
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("subjects.html");
		String subject = request.getParameter("subject");
		
		Subject sub = new Subject(subject);

			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			Integer serialNo = (Integer) session.save(sub);
			transaction.commit();
	        session.close();

		
		PrintWriter out = response.getWriter();
		rd.include(request, response);
		out.println("<br/> <centre> <span style = 'color:blue'> " + serialNo + " " + subject +  " added in Subject list</span> </centre>");

	}

}
