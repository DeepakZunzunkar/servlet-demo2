<%@page import="com.dz.app.utility.DateUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.dz.app.model.entity.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="header.jsp"%>
<%!
    Integer currentPage = 1;
    Integer nextPage;
    Integer previousPage; 

    Long totalEmployeeCount = 0l;
    Integer pageSize = 5;
    Double totalPages = 0.0;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");%>

<%
    currentPage = (Integer) request.getAttribute("currentPage");

    pageSize = (Integer) request.getAttribute("pageSize");
    totalEmployeeCount = (Long) request.getAttribute("totalEmployeeCount");
    totalPages = (double) Math.ceil(totalEmployeeCount.doubleValue() / pageSize.doubleValue());

    nextPage = (currentPage + 1 > totalPages) ? totalPages.intValue() : currentPage + 1;
    previousPage = (currentPage - 1 == 0) ? 1 : currentPage - 1;
    

%>

<ul class="nav justify-content-center">
    <li class="nav-item"><a class="nav-link active"
        aria-current="page" href="./testParams">Employee List</a></li>
    <li class="nav-item"><a class="nav-link" href="#">Upload
            Employee</a></li>
</ul>
<div class="card" style="margin-top: 5px">
    <div class="card-body">

        <table class="table table-dark table-hover">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">STATUS</th>
                    <th scope="col">AGE</th>
                    <th scope="col">SALARY</th>
                    <th scope="col">CREATED ON</th>
                    <th scope="col" colspan="2">ACTION</th>
                </tr>
            </thead>
            <tbody>
            
                <c:forEach items="${ empList }" var="emp">
                  <tr>
                    <td>${ emp.getEid() }</td>
                    <td>${ emp.getFirstName()   emp.getLastName() }</td>
                    <td>${ emp.getStatus() }</td>
                    <td>${ DateUtils.getAge(DateUtils.convertJUtilDateTimeToString(emp.getBirthDate())) }</td>
                    <td>${ df.format(emp.getSalary()) }</td>
                    <td>${ sdf.format(emp.getBaseProperties().getCreatedOn()) }</td>
                    <td><a href="EditEmployeeForm?eid=${ emp.getEid() }">Edit</a></td>
                    <td><a href="DeleteEmployeeForm?eid=${ emp.getEid() }">Delete</a></td>
                  </tr>
                </c:forEach>
            </tbody>
        </table>

        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li class="page-item"><a class="page-link"
                    href="./employee?currentPage=<%=previousPage%>">Previous</a></li>
                <%
                    for (int i = 1; i <= totalPages; i++) {
                %>

                <li class="page-item "><a class="page-link"
                    href="./employee?currentPage=<%=i%>"> <%=i%></a></li>
                <%
                    }
                %>
                <li class="page-item"><a class="page-link"
                    href="./employee?currentPage=<%=nextPage%>">Next</a></li>
            </ul>
        </nav>
    </div>
</div>
