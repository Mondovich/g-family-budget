<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%
	UserService userService = UserServiceFactory.getUserService();
	if (!userService.isUserLoggedIn()) {
		response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
	}
%>