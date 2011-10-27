<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" style="" href="css/main.css">
<title>GFamilyBudget</title>
</head>
<body>
	<%
	    UserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn()){
			response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
		} 
	%>
	<div id="topMenu">
		<div class="button float_r">
			<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign Out</a>
		</div>
		<div class="button">	
			<a href="family">Family</a>
		</div>
		<div class="button">	
			<a href="bankaccount">Bank Account</a>
		</div>
		<div class="button">	
			<a href="reports">Reports</a>
		</div>
	</div>
	<div id="content">
		
	</div>
</body>
</html>