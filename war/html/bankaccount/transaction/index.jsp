<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<img id="waiting" style="display: none;" class="imgWaitAnim" src="/images/loader_anim.gif">

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
</div>

