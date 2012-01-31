<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>

<% UserService userService = UserServiceFactory.getUserService(); %>
<div id="topMenu">
	<div id="div_home" class="button">
		<a href="/home">Home</a>
	</div>
	<div id="div_family" class="button">	
		<a href="/family/">Family</a>
	</div>
	<div id="div_bankaccount" class="button">	
		<a href="/bankaccount/">Bank Account</a>
	</div>
	<div id="div_reports" class="button">	
		<a href="/reports">Reports</a>
	</div>
	<div class="button float_r">
		<a href="<%= userService.createLogoutURL("/home") %>">Sign Out</a>
	</div>
</div>
	
<script type="text/javascript">
	$(".button").hover(
	  function () {
	    $(this).addClass("hover");
	  },
	  function () {
	    $(this).removeClass("hover");
	  }
	);
</script>