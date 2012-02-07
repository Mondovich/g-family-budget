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
		<button id="FamilyAdd" href="/family/edit" title="<s:text name='title.add_person' />" class="dialog float plus">&nbsp;</button>
		<button id="FamilyEdit" href="/family/edit" title="<s:text name='title.edit_person' />" class="dialog float edit" >&nbsp;</button>
		<button id="FamilyDel" href="/family/delete" title="<s:text name='title.delete_person'/>" class="dialog float minus" >&nbsp;</button>
	</div>

	<div class="title ui-widget-header ui-corner-all">
		<s:text name="title.bankaccounts" />
	</div>
	
	<div id="bankAccount" class="body last_item ui-corner-all">
		<s:iterator value="listOfBankAccount">
			<div class="item clickable ui-corner-all" id='<s:property value="%{key.id}" />'>
				<s:property value="name" />
				<div class="float_r money">
					&euro;&nbsp;<s:property value="initialValue" />
				</div>
			</div>
		</s:iterator>
	</div>
	
	<button id="BankAccountAdd" href="/bankaccount/edit" title="<s:text name='title.add_bankaccount' />" class="dialog float plus">&nbsp;</button>
	<button id="BankAccountEdit" href="/bankaccount/edit" title="<s:text name='title.edit_bankaccount' />" class="dialog float edit">&nbsp;</button>
	<button id="BankAccountDel" href="/bankaccount/delete" title="<s:text name='title.delete_bankaccount' />" class="dialog float minus">&nbsp;</button>

</div>

<div class="shadow main ui-corner-all"></div>

<div class="main_content ui-corner-all">

</div>

<script type="text/javascript">
	$(function() {
		$("#family").load("/family/list");
		
		$(document).on('click', "button:enabled.dialog", function() {
			var that = $(this);
			var href = that.attr("href");
			var title = that.attr("title"); 
			var id = that.attr("item_id") || "";
			// show a spinner or something via css
            var dialog = $('<div id="dialog" class="loading"></div>').appendTo('body');
            // open the dialog
            dialog.dialog({
                // add a close listener to prevent adding multiple divs to the document
                close: function(event, ui) {
                    // remove div with all data and events
                    dialog.remove();
                },
                modal: true,
                title: title,
                buttons: {
                	Save: function() {
               		    var $form = $("#ajaxForm"), url = $form.attr( 'action' );
               		    /* Send the data using post and put the results in a div */
               		    $.post( url, $form.serialize(),
               		      function( data ) {
               		    	$("#family").load("/family/list");
               		    	dialog.dialog("close");
               		      }
               		    );
                	},
                	Cancel: function() {
                		dialog.dialog("close");
                	}
            	}
            });
            // load remote content
            dialog.load(
            	href, 
                {id: id}, // omit this param object to issue a GET request instead a POST request, otherwise you may provide post parameters within the object
                function (responseText, textStatus, XMLHttpRequest) {
                    // remove the loading class
                    dialog.removeClass('loading');
                }
            );
		});
		$(document).on('click', ".clickable", function() {
			var that = $(this);
			var parent = that.parents(".body");
			var id = that.attr("id") || "";
			
			$(".main_content").html("<div class='loading'>&nbsp;</div>");
			
			if (parent.attr("id") == "bankAccount") {
				$.ajax({
	       			data: {id: id}, 
					url: 'bankaccount/transaction/',
					cache: false,
					error: function(){
						$(".main_content").html("Could not retrieve transactions on this bank account.");
					},
					success: function(html){
						$(".main_content").html(html);
						$("#bankAccount .selected").removeClass("selected");
						that.addClass("selected");
						$("#BankAccountEdit").button("enable");
						$("#BankAccountDel").button("enable");
					}
	      		});
			}
			if (parent.attr("id") == "family") {
				$("#FamilyEdit").attr("item_id", id);
				$("#family .selected").removeClass("selected");
				that.addClass("selected");
				$("#FamilyEdit").button("enable");
				$("#FamilyDel").button("enable");
			}
			
			return false;
		});
		
	});
	$("#div_home").addClass("selected");
</script>
