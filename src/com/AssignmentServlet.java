package com;

import java.io.PrintWriter;
import java.util.List;

import java.io.IOException;

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
 * Servlet implementation class AssignmentServlet
 */
public class AssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private SessionFactory factory;
	
    public AssignmentServlet() {
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
		if ("Get Subjects and Teachers".equals(button)) {
			String classNo = request.getParameter("classes2");
			Session session = factory.openSession();
			
			String hql = "FROM Assignment where classNo = ?";
			TypedQuery<Assignment> query = session.createQuery(hql);
			query.setParameter(0, classNo);
			List<Assignment> assignments = query.getResultList();
//			List<Assignment> assignments = (List<Assignment>) session.get(Assignment.class, classes2);
	        session.close();
			
			RequestDispatcher rd = request.getRequestDispatcher("home.html");
			PrintWriter out = response.getWriter();
			rd.include(request, response);
			
			out.println(" <br/> Following Subjects and Teachers assigned to Class : " +  classNo);
			for (Assignment assignment: assignments) {
				out.println(" <br/> " + assignment.getSubject1() + " \t : " + assignment.getTeacher1());

			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("home.html");
		String classNo = request.getParameter("classes");
		String subject1 = request.getParameter("subject1");
		String teacher1 = request.getParameter("teacher1");

		
		Assignment assign = new Assignment(classNo, subject1, teacher1);

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(assign);
		transaction.commit();
	    session.close();

		
		PrintWriter out = response.getWriter();
		rd.include(request, response);
		out.println("<br/> <centre> <span style = 'color:blue'> Subject and Teachers assigned to class : " + classNo + "</span> </centre>");
	}

}
