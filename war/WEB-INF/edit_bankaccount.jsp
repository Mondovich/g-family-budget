<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<s:form id="addBankAccountForm" action="newBankAccount" method="post">
	<s:push value="bankAccount">
		<s:textfield label="Account Name" name="name" value="%{bankAccount.name}"></s:textfield>
		<s:textfield label="Initial Value" name="initialValue" value="%{bankAccount.initialValue}"></s:textfield>
		<s:select label="Owners" name="owners" multiple="true" listKey="key.id" listValue="completeName" list="listOfPersons" value="%{bankaccount.owner}"></s:select>
		<div class="buttonwrapper">
			<s:if test="%{key.id != null}">
				<s:hidden name="id" value="%{key.id}"></s:hidden>
			</s:if>
			<s:submit cssClass="ovalbutton clickable" value="Save"></s:submit>
		</div>
	</s:push>
</s:form>
