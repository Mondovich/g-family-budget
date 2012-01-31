<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<s:form id="personForm" action="save" method="post">
	<s:push value="person">
		<s:textfield label="First Name" name="firstName" value="%{firstName}"></s:textfield>
		<s:textfield label="Last Name" name="lastName" value="%{lastName}"></s:textfield>
		<div class="buttonwrapper">
			<s:if test="%{key.id != null}">
				<s:hidden name="id" value="%{key.id}"></s:hidden>
			</s:if>
		</div>
	</s:push>
</s:form>