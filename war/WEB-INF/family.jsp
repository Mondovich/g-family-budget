<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		if (!userService.isUserLoggedIn()) {
			response.sendRedirect(userService.createLoginURL(request
					.getRequestURI()));
		}
	%>

	<jsp:include page="topmenu.jsp"></jsp:include>

	<div id="content">
		<div id="header">
			<table id="family">
				<tr>
					<th colspan="3">Family</th>
				</tr>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>&nbsp;</th>
				</tr>
				<s:iterator value="listOfPerson">
					<tr>
						<td><s:property value="firstName" /></td>
						<td><s:property value="lastName" /></td>
						<td>
							<div class="buttonwrapper">
								<s:url id="editUrl" action="editPerson">
									<s:param name="id" value="%{key.id}"></s:param>
								</s:url>
								<s:a cssClass="ovalbutton" href="%{editUrl}">
									<span>Edit</span>
								</s:a>
							</div>
							<div class="buttonwrapper">
								<s:url id="deleteUrl" action="deletePerson">
									<s:param name="id" value="%{key.id}"></s:param>
								</s:url>
								<s:a cssClass="ovalbutton" href="%{deleteUrl}">
									<span>Delete</span>
								</s:a>
							</div>
						</td>
					</tr>
				</s:iterator>
				<form action="newPerson" method="post">
					<s:push value="person">
						<tr>
							<td><input name="firstName" type="text" value="<s:property value="firstName" />"></input></td>
							<td><input name="lastName" type="text" value="<s:property value="lastName" />"></input</td>
							<td>
								<div class="buttonwrapper">
									<s:if test="key != null">
										<input name="id" type="hidden" value="<s:property value="key.id" />">
										<input name="crea" type="submit" value="Save" class="ovalbutton"></input>
									</s:if>
									<s:else>
										<input name="crea" type="submit" value="Add" class="ovalbutton"></input>
									</s:else>
								</div>
							</td>
						</tr>
					</s:push>
				</form>
			</table>
		</div>
	</div>
</body>
</html>