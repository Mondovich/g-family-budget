<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="shadow left ui-corner-all"></div>
<div class="left_menu ui-corner-all">

	<div class="title ui-widget-header ui-corner-all">
		<s:text name="title.family" />
	</div>
	
	<div id="family" class="body ui-corner-all">
		<div class='loading'>&nbsp;</div>
	</div>
	
	<div class="float" style="margin-bottom: 5px;">
		<button id="FamilyAdd" href="/family/edit" title="<s:text name='title.add_person' />" buttons="['add','cancel']" class="dialog float plus">&nbsp;</button>
		<button id="FamilyEdit" href="/family/edit" title="<s:text name='title.edit_person' />" buttons="['edit','cancel']" class="dialog float edit" >&nbsp;</button>
		<button id="FamilyDel" href="/family/deleteConfirm" title="<s:text name='title.delete_person'/>" buttons="['delete','cancel']" class="dialog float minus" >&nbsp;</button>
	</div>

	<div class="title ui-widget-header ui-corner-all">
		<s:text name="title.bankaccounts" />
	</div>
	
	<div id="bankAccount" class="body last_item ui-corner-all">
		<div class='loading'>&nbsp;</div>
	</div>
	
	<button id="BankAccountAdd" href="/bankaccount/edit" title="<s:text name='title.add_bankaccount' />" buttons="['add','cancel']" class="dialog float plus">&nbsp;</button>
	<button id="BankAccountEdit" href="/bankaccount/edit" title="<s:text name='title.edit_bankaccount' />" buttons="['edit','cancel']" class="dialog float edit">&nbsp;</button>
	<button id="BankAccountDel" href="/bankaccount/deleteConfirm" title="<s:text name='title.delete_bankaccount' />" buttons="['delete','cancel']" class="dialog float minus">&nbsp;</button>

</div>

<div class="shadow main ui-corner-all"></div>

<div class="main_content ui-corner-all"></div>

<script type="text/javascript">
	function familyDialog (e, action, dialog) {
		var $form = $("#ajaxForm"), url = $form.attr( 'action' );
		
	    $.post( url, $form.serialize(),
	      function( data ) {
	    	if (data.length > 0) {
	    		$(dialog).html(data);
	    	} else {
	    		$("#family").load("/family/list");
   		    	$(dialog).dialog("close");
   		    	if (action == 'delete') {
   		    		$("#FamilyEdit").button("disable");
   					$("#FamilyDel").button("disable");
   		    	}
	    	}
	      }
	    );
	}
	
	function bankAccountDialog (e, action, dialog) {
		var $form = $("#ajaxForm"), url = $form.attr( 'action' );
	
	    $.post( url, $form.serialize(),
	      function( data ) {
	    	if (data.length > 0) {
	    		$(dialog).html(data);
	    	} else {
	    		$("#bankAccount").load("/bankaccount/list");
   		    	$(dialog).dialog("close");
   		    	if (action == 'delete') {
   		    		$("#BankAccountEdit").button("disable");
					$("#BankAccountDel").button("disable");
   		    		
   		    	}
	    	}
	      }
	    );
	}

	$(function() {
		$("#family").load("/family/list");
		$("#bankAccount").load("/bankaccount/list");
		
		$("#FamilyAdd").on("buttonClickActionEvent", familyDialog);
		$("#FamilyEdit").on("buttonClickActionEvent", familyDialog);
		$("#FamilyDel").on("buttonClickActionEvent", familyDialog);
		
		$("#BankAccountAdd").on("buttonClickActionEvent", bankAccountDialog);
		$("#BankAccountEdit").on("buttonClickActionEvent", bankAccountDialog);
		$("#BankAccountDel").on("buttonClickActionEvent", bankAccountDialog);
		
		$(document).on('click', "#bankAccount .clickable", function() {
			var that = $(this);
			var parent = that.parents(".body");
			var id = that.attr("id") || "";
			
			$(".main_content").html("<div class='loading'>&nbsp;</div>");
			
			$.ajax({
       			data: {id: id}, 
				url: 'bankaccount/transaction/',
				cache: false,
				error: function(){
					$(".main_content").html("Could not retrieve transactions on this bank account.");
				},
				success: function(html){
					$(".main_content").html(html);
					$("#BankAccountEdit").attr("item_id", id);
					$("#BankAccountDel").attr("item_id", id);
					$("#bankAccount .selected").removeClass("selected");
					that.addClass("selected");
					$("#BankAccountEdit").button("enable");
					$("#BankAccountDel").button("enable");
				}
      		});
			
			return false;
		});
		
		$(document).on('click', "#family .clickable", function() {
			var that = $(this);
			var parent = that.parents(".body");
			var id = that.attr("id") || "";
			
			$(".main_content").html("<div class='loading'>&nbsp;</div>");
			
			$("#FamilyEdit").attr("item_id", id);
			$("#FamilyDel").attr("item_id", id);
			$("#family .selected").removeClass("selected");
			that.addClass("selected");
			$("#FamilyEdit").button("enable");
			$("#FamilyDel").button("enable");
			
			return false;
		});
		
	});
	$("#div_home").addClass("selected");
</script>
