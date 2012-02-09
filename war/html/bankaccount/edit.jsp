<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<s:form id="ajaxForm" action="save" method="post">
	<s:push value="bankAccount">
		<s:textfield key="bankaccount.name" name="name" value="%{bankAccount.name}"></s:textfield>
		<s:textfield key="bankaccount.initialvalue" name="initialValue" value="%{bankAccount.initialValue}"></s:textfield>
	 	<s:select key="bankaccount.owners" name="owners" listKey="key.id" listValue="completeName" list="listOfPersons" value="owners" multiple="true"></s:select>
		<div class="buttonwrapper">
			<s:if test="%{key.id != null}">
				<s:hidden name="id" value="%{key.id}"></s:hidden>
			</s:if>
		</div>
	</s:push>
</s:form>

<s:actionerror/>
