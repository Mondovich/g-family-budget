<%@ taglib prefix="s" uri="/struts-tags"%>

<table id="bankaccountdetails">
	<tr>
		<th><s:text name="transaction.date" /></th>
		<th><s:text name="transaction.payee" /></th>
		<th><s:text name="transaction.status" /></th>
		<th><s:text name="transaction.category" /></th>
		<th><s:text name="transaction.withdrawal" /></th>
		<th><s:text name="transaction.deposit" /></th>
		<th><s:text name="transaction.balance" /></th>
		<th><s:text name="transaction.note" /></th>
	</tr>
</table>
<s:iterator value="listOfTransaction">
	<div class="item clickable ui-corner-all" id='<s:property value="%{key.id}" />'>
		<s:property value="name" />
		<div class="float_r money">
			&euro;&nbsp;<s:property value="initialValue" />
		</div>
	</div>
</s:iterator>