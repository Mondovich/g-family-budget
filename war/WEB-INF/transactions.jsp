<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<div id="transactions">
	<div id="content">
		<table id="bankaccountdetails">
			<tr>
				<th colspan="8"><s:property value="%{bankAccount.name}" /></th>
			</tr>
			<tr>
				<th>Date</th>
				<th>Payee</th>
				<th>Status</th>
				<th>Category</th>
				<th>Withdrawal</th>
				<th>Deposit</th>
				<th>Balance</th>
				<th>Note</th>
			</tr>
		</table>
		<a id="newTransaction" class="ovalbutton clickable"><span>Add</span></a>
	</div>
	
	<div id="dialog-modal" title="Basic modal dialog">
		<p>Adding the modal overlay screen makes the dialog look more
			prominent because it dims out the page content.</p>
	</div>
	<script type="text/javascript">
		$(function()
	       {
			$("#newTransaction").click(
				function(e){
					$( "#dialog:ui-dialog" ).dialog( "destroy" );
					
					$( "#dialog-modal" ).dialog({
						height: 140,
						modal: true
					});
					
					return false;
				}
			);
	       });
	</script>
</div>

