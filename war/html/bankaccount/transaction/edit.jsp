<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<sj:head jqueryui="true" />

<s:form id="addTransactionForm" action="newTransaction" method="post">
	<s:push value="transaction">
		<s:select label="Transaction Type" name="transactionType" listValue="%{toString()}" list="transactionTypes"></s:select>
		
		<tr>
			<td>
				<label for="addTransactionForm_" class="label">Payee:</label>
			</td>
		    <td>
		    	<div>
		    		<sj:a openDialog="payees" button="true">Select Payee</sj:a>	
				</div>
			</td>
		</tr>
		
		<sj:dialog 
			id="payees" 
			href="editPayee" 
			title="Select Payee" 
			autoOpen="false" 
			modal="true"
			buttons="{
				'Save': function() {},
				'Cancel': function() { $('#payees').dialog('close'); },
			}"
			indicator="waiting"
			onErrorTopics="Error"
			zindex="1010"
		/>
		
		<sj:datepicker label="Transaction Date" name="date" value="%{date}" displayFormat="dd/mm/yy" ></sj:datepicker>
		<s:textfield label="Amount" name="amount" value="%{amount}"></s:textfield>
		<div class="buttonwrapper">
			<s:if test="%{key.id != null}">
				<s:hidden name="id" value="%{key.id}"></s:hidden>
			</s:if>
			<s:submit cssClass="ovalbutton clickable" value="Save"></s:submit>
		</div>
	</s:push>
</s:form>
