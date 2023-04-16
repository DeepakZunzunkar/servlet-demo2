package com.dz.app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import com.dz.app.model.entity.BaseProperties;
import com.dz.app.model.entity.Employee;
import com.dz.app.utility.DateUtils;
import com.dz.app.utility.Constant.EmployeeStatus;


/**
 * @author dz Apr 8, 2023
 *
 */
@WebServlet(name = "empServlet", urlPatterns = "/employee",initParams= {
		@WebInitParam(name="DRIVER",value="com.mysql.jdbc.Driver"),
		@WebInitParam(name="URL",value="jdbc:mysql://localhost:3306/hibernatedemo3?createDatabaseIfNotExist=true"),
		@WebInitParam(name="USERNAME",value="root"),
		@WebInitParam(name="PASSWORD",value="root"),
		@WebInitParam(name="DIALECT",value="org.hibernate.dialect.MySQLDialect"),
		@WebInitParam(name="HBM2DDL_AUTO",value="update"),
		@WebInitParam(name="SHOW_SQL",value="true"),
		@WebInitParam(name="PAGE_SIZE",value="5")
})
public class EmployeeServlet extends HttpServlet{

	private static final long serialVersionUID = 6902982605136563082L;
	
	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;
	private static Map<String, String> dbSettings;
	
	PrintWriter printWriter;
	RequestDispatcher requestDispatcher=null;
	private static Integer pageSize=3;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Employee trn = new Employee(new BaseProperties("A",new Date(),"employer",null, null));
		
		trn.setFirstName(req.getParameter("firstName"));
//		String mname =req.getParameter("middleName");
		trn.setLastName(req.getParameter("lastName"));
		
		String dob= req.getParameter("dob");
		
		trn.setBirthDate(DateUtils.convertStringToJUtilDateTime(dob));
		
		trn.setSalary(Double.parseDouble(req.getParameter("salary")));
		trn.setGender(req.getParameter("gender"));
		
		trn.setStatus(EmployeeStatus.ACTIVE.getEmployeeStatusCode());
		
		saveEmployee(trn);
		
		resp.sendRedirect("index.jsp");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long eid= Long.parseLong(req.getParameter("eid"));
		Employee trn=findEmployeeByEid(eid);
		if(trn!=null) {
			
			trn.getBaseProperties().setUpdatedBy("employer");
			trn.getBaseProperties().setUpdatedOn(new Date());
			
			trn.setFirstName(req.getParameter("firstName"));
			trn.setLastName(req.getParameter("lastName"));
			String dob= req.getParameter("dob");
			trn.setBirthDate(DateUtils.convertStringToJUtilDateTime(dob));
			trn.setSalary(Double.parseDouble(req.getParameter("salary")));
			
			saveEmployee(trn);
			printWriter=resp.getWriter();
			
			printWriter.println("<a href='index.jsp'> Back </a>");
			printWriter.print("<b>employee updated successfully......</b>");
		
		}else {
			printWriter=resp.getWriter();
			printWriter.print("<b>employee not found...</b>");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		/*System.out.println("req url "+req.getRequestURL());
		System.out.println(req.getQueryString());
		System.out.println("req "+req.getContextPath());*/
		
		if(req.getQueryString().contains("eid")) {
			
			Long eid=Long.parseLong(req.getParameter("eid"));
			Employee emp=findEmployeeByEid(eid);
			if(emp!=null) {
				req.setAttribute("emp",emp);
				requestDispatcher=req.getRequestDispatcher("editEmployeeForm.jsp");
				requestDispatcher.forward(req, resp);
				
			}else {
				printWriter=resp.getWriter();
				printWriter.print("<b>employee not found...</b>");
			}
				
		}else {
			
			Long totalEmployeeCount = totalEmployeeCount();
			req.setAttribute("totalEmployeeCount",totalEmployeeCount);
			req.setAttribute("pageSize",pageSize);
			
			Integer currentPage=Integer.parseInt(req.getParameter("currentPage"));
			
			req.setAttribute("currentPage",currentPage);
			Integer pageVal=(currentPage!=null)?currentPage-1:0;
			pageVal=pageVal*pageSize;
			
			if(sessionFactory != null) {
				
				try(Session session = sessionFactory.openSession()) {
					
					Query<Employee>  query=session.createQuery("from Employee order by eid desc",Employee.class);
					
					query.setFirstResult(pageVal);
					query.setMaxResults(pageSize);
					
					List<Employee> employees=query.getResultList();
					
					req.setAttribute("empList",employees);
					requestDispatcher=req.getRequestDispatcher("employeeList.jsp");
					requestDispatcher.forward(req, resp);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long eid= Long.parseLong(req.getParameter("eid"));
		Employee trn=findEmployeeByEid(eid);
		if(trn!=null) {
		
			deleteEmployeeByEid(trn);
			printWriter=resp.getWriter();
			
			printWriter.println("<a href='index.jsp'> Back </a>");
			printWriter.print("<b>employee deleted successfully......</b>");
		}
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		try {
			if(sessionFactory==null) {
			
				pageSize = Integer.parseInt(config.getInitParameter("PAGE_SIZE"));
				
				//create standardServiceRegistry
				StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
				
				// Hibernate setting which is equivalent to hibernate.cfg.xml properties.
				dbSettings = new HashMap<String, String>();
				
				dbSettings.put(Environment.DRIVER,config.getInitParameter("DRIVER"));
				dbSettings.put(Environment.URL,config.getInitParameter("URL"));
				dbSettings.put(Environment.USER,config.getInitParameter("USERNAME"));
				dbSettings.put(Environment.PASS,config.getInitParameter("PASSWORD"));
				dbSettings.put(Environment.DIALECT,config.getInitParameter("DIALECT"));
				dbSettings.put(Environment.HBM2DDL_AUTO,config.getInitParameter("HBM2DDL_AUTO"));
				dbSettings.put(Environment.SHOW_SQL,config.getInitParameter("SHOW_SQL"));
//				dbSettings.put(Environment.FORMAT_SQL,config.getInitParameter("FORMAT_SQL"));
				
				standardServiceRegistryBuilder.applySettings(dbSettings);
				standardServiceRegistry= standardServiceRegistryBuilder.build();
				
				//create metaDataSource
				MetadataSources metadataSources =new MetadataSources(standardServiceRegistry);
				metadataSources.addAnnotatedClass(Employee.class);
				
				//create metaData 
				Metadata metadata= metadataSources.getMetadataBuilder().build();
				
				//create sessionFactory
				sessionFactory=metadata.getSessionFactoryBuilder().build();
				System.out.println("\n session factory initilse...\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(standardServiceRegistry!=null) {
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
				System.out.println("\n session factory destroyed... \n");
			}
		}
	}
	
	@Override
	public void destroy() {
		
		System.out.println("i am in destroy method TestInitContextParam..");
		if(standardServiceRegistry!=null) {
			StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
			System.out.println("\n session factory destroyed... \n");
		}
	}
	
	public static Long totalEmployeeCount() {
		
		Long count=0l;
		if(sessionFactory != null) {
			
			try(Session session = sessionFactory.openSession()) {
				
				Criteria cr = session.createCriteria(Employee.class);
				cr.setProjection(Projections.rowCount());
				count=(Long)cr.uniqueResult();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	private static Employee saveEmployee(Employee employee) {
		
		if(sessionFactory != null) {
			
			try(Session session = sessionFactory.openSession()) {
				
				session.beginTransaction();
				session.save(employee);
				session.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return employee;
	}
	
	private static Employee findEmployeeByEid(Long eid) {
		
		Employee employee=null;
		if(sessionFactory != null) {
			
			try(Session session = sessionFactory.openSession()) {
				
				employee= session.get(Employee.class,eid);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return employee;
	}
	
	private static Employee deleteEmployeeByEid(Employee employee) {
		
		if(sessionFactory != null) {
			
			try(Session session = sessionFactory.openSession()) {
				session.beginTransaction();
				session.delete(employee);
				session.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return employee;
	}
}
