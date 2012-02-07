<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<div id="transactions">
	<div class="title ui-widget-header ui-corner-all">
		<s:text name="title.transactions" />
	</div>
	<div class="body ui-corner-all">
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
	</div>

	<div class="bottom_buttons">
		<button class="float plus">&nbsp;</button>
	</div>
	
</div>
	
	<div id="editTransaction"></div>
	<script type="text/javascript">
	
		function openTransactionDialog(id) {
			var ajaxUrl = "edit";
			var title = (id == undefined) ? 'Add Transaction' : 'Edit Transaction';
			$("#editTransaction").html('<div id="content"><img class="imgWaitAnim" src="/images/loader_anim.gif"></div>')
			jQuery("#editTransaction").dialog({
            	title: title,
            	modal: true,
            	autoOpen: true
            });
			$.ajax({
    			data: {id: id}, 
				url: ajaxUrl,
				cache: false,
				error: function(){
					$("#editTransaction").html("Could not edit transaction.");
				},
				success: function(data){
					$("#editTransaction").html(data);
				}
   			});
		}
		
		$(function()
	       {
			$( "input:submit, a, button", ".demo" ).button();
			
			$("#editTransaction").hide();
			$("#newTransaction").live('click', 
				function(e){
					openTransactionDialog();
				}
			);
	       });
	</script>

