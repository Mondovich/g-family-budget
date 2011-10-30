<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>

<% UserService userService = UserServiceFactory.getUserService(); %>
<div id="topMenu">
	<div class="button">
		<a href="index.jsp">Home</a>
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
	<div class="button float_r">
		<a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign Out</a>
	</div>
</div>
	