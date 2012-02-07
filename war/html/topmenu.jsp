<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.google.appengine.api.users.UserService"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>

<% UserService userService = UserServiceFactory.getUserService(); %>
<div id="topMenu">
	<div id="div_home" class="button">
		<a href="/home"><s:text name="menu.home" /></a>
	</div>
	<div id="div_family" class="button">	
		<a href="/family/"><s:text name="menu.family" /></a>
	</div>
	<div id="div_bankaccount" class="button">	
		<a href="/bankaccount/"><s:text name="menu.bankaccount" /></a>
	</div>
	<div id="div_reports" class="button">	
		<a href="/report/"><s:text name="menu.reports" /></a>
	</div>
	<div class="button float_r">
		<a href="<%= userService.createLogoutURL("/home") %>"><s:text name="menu.signout" /></a>
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