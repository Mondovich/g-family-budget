<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<s:form id="ajaxForm" action="save" method="post">
	<s:push value="transaction">
		<s:select id="transactionType" key="transaction.edit.type" name="transactionType" listValue="%{getText('transaction.type.' + toString())}" list="transactionTypes"></s:select>
		
		<tr>
			<td>
				<label for="addTransactionForm_" class="label"><s:text name="transaction.payee" /></label>
			</td>
		    <td>
		    	<div>
		    		<button id="PayeeSelect" href="editPayee" title="<s:text name='title.add_bankaccount' />" buttons="['select','cancel']" class="dialog float edit"><s:text name="transaction.edit.select.payee" /></button>
				</div>
			</td>
		</tr>
		
		<s:textfield id="datepicker" key="transaction.edit.date" ame="date" value="%{date}"></s:textfield>
		<s:textfield id="amount" key="transaction.edit.amount" name="amount" value="%{amount}"></s:textfield>
		<div class="buttonwrapper">
			<s:if test="%{key.id != null}">
				<s:hidden name="id" value="%{key.id}"></s:hidden>
			</s:if>
		</div>
	</s:push>
</s:form>

<script type="text/javascript">

$(function() {
	$("button.edit").button();
	
	$( "#datepicker" ).datepicker();
});

</script>