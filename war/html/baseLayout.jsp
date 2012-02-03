<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="/css/smoothness/jquery-ui-1.8.16.custom.css">
	<!-- <link rel="stylesheet" href="/css/trontastic/jquery-ui-1.8.17.custom.css"> -->
	<link rel="stylesheet" href="/css/main.css">
	
	<!-- <script src="http://code.jquery.com/jquery-latest.js"></script>  --> 
	<script src="/js/jquery-1.7.1.js"></script>
	<script src="/js/jquery-ui-1.8.17.custom.min.js"></script>
	<script src="/js/jquery.maskMoney.js"></script>
	<script src="/js/jquery.ba-bbq.min.js"></script>
	<script src="/js/jquery.hoverIntent.js"></script>
	<script src="/js/jquery.contextmenu.r2.packed.js"></script>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<%
		UserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn()) {
			response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
		}
	%>
	
	<tiles:insertAttribute name="topmenu" />
	
	<img id="waitIndicator" style="display: none;" class="imgWaitAnim" src="/images/loader_anim.gif">
	
	<tiles:insertAttribute name="header" />
	
	<div id="content">
		<tiles:insertAttribute name="body" />
	</div>

	<tiles:insertAttribute name="footer" />

</body>
</html>