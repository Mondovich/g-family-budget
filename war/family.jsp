<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>

<%@page import="it.mondovich.data.entities.Person"%>

<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/main.css">
<title>GFamilyBudget</title>
</head>
<body>
	<%
	    UserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn()){
			response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
		} 
	%>
	
	<jsp:include page="topmenu.jsp"></jsp:include>
	
	<div id="content">
		<div id="header">
			<table id="family">
				<tr><th colspan="2">Family</th></tr>
				<tr><th>First Name</th><th>Last Name</th></tr>
				<s:iterator value="listOfPersons">
					<tr>
						<td><s:property value="firstName" /></td>
						<td><s:property value="lastName" /></td>
					</tr>
				</s:iterator>
			</table>
			<div class="buttonwrapper">
				<a class="ovalbutton" href="new"><span>+</span></a>
			</div>
		</div>
		<form action="family" method="post">
			<label>First Name</label>
			<input name="firstname" type="text"></input><br />
			<label>Last Name</label>
			<input name="lastname" type="text"></input><br />
			<input name="crea" type="submit" value="Crea"></input>
		</form>
	</div>
</body>
</html>