<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<div id="transactions">
	<div class="title ui-widget-header ui-corner-all">
		<s:text name="title.transactions" />
	</div>
	<div class="body ui-corner-all">
		<div class='loading'>&nbsp;</div>
	</div>

	<div class="bottom_buttons">
		<button id="TransactionAdd" href="/bankaccount/transaction/edit" title="<s:text name='title.add_transaction' />" buttons="['add','cancel']" class="dialog float plus">&nbsp;</button>
		<button id="TransactionEdit" href="/bankaccount/transaction/edit" title="<s:text name='title.edit_transaction' />" buttons="['edit','cancel']" class="dialog float edit">&nbsp;</button>
		<button id="TransactionDel" href="/bankaccount/transaction/deleteConfirm" title="<s:text name='title.delete_transaction' />" buttons="['delete','cancel']" class="dialog float minus">&nbsp;</button>
	</div>
	
</div>
	
	<div id="editTransaction"></div>
	<script type="text/javascript">
	
		function openTransactionDialog(e, action, dialog) {
			var $form = $("#ajaxForm"), url = $form.attr( 'action' );
		
			$.post( url, $form.serialize(),
		      function( data ) {
		    	if (data.length > 0) {
		    		$(dialog).html(data);
		    	} else {
		    		$("#transactions .body").load("/bankaccount/transaction/list");
		    		$(dialog).dialog("close");
	   		    	if (action == 'delete') {
	   		    		$("#TransactionEdit").button("disable");
	   					$("#TransactionDel").button("disable");
	   		    	}
		    	}
		      }
		    );
		}
		
		$(function() {
			$("#TransactionAdd").on("buttonClickActionEvent", openTransactionDialog);
			
			$("#transactions .body").load("/bankaccount/transaction/list");
		
		});
	</script>

