package com;

import java.io.IOException;
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
 * Servlet implementation class ClassesServlet
 */
public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private SessionFactory factory;
	
    public ClassesServlet() {
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
		if ("Students Master lists".equals(button)) {

			Session session = factory.openSession();
			String hql = "FROM Classes";
			TypedQuery<Classes> query = session.createQuery(hql);
			List<Classes> classes = query.getResultList();
	        session.close();
			
			RequestDispatcher rd = request.getRequestDispatcher("classes.html");
			PrintWriter out = response.getWriter();
			rd.include(request, response);
			
			
			for (Classes cls: classes) {
				out.println(" <br/> " + cls.getClassNo() + " \t : " + cls.getStudent1() + " \t " + cls.getStudent2() + " \t " + cls.getStudent3() + " \t " + cls.getStudent4() + " \t " + cls.getStudent5());	
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("classes.html");
		String classNo = request.getParameter("classes");
		String student1 = request.getParameter("student1");
		String student2 = request.getParameter("student2");
		String student3 = request.getParameter("student3");
		String student4 = request.getParameter("student4");
		String student5 = request.getParameter("student5");
		
		
		Classes cls = new Classes(classNo, student1, student2, student3, student4, student5);

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(cls);
		transaction.commit();
	    session.close();

		
		PrintWriter out = response.getWriter();
		rd.include(request, response);
		out.println("<br/> <centre> <span style = 'color:blue'> Class details added along with Student list</span> </centre>");
	}

}
