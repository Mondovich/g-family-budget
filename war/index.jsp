<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/ui-lightness/jquery-ui-1.8.16.custom.css">
<link rel="javascript" href="js/jquery-1.6.4.js">
<link rel="javascript" href="js/jquery-ui-1.8.16.custom.min.js">
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
		
	</div>
</body>
</html>