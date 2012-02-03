<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="shadow left ui-corner-all"></div>
<div class="left_menu ui-corner-all">

	<div class="title ui-widget-header ui-corner-all">
		Family
	</div>
	
	<div id="family" class="body ui-corner-all">
		<s:iterator value="listOfPerson">
			<div class="item clickable ui-corner-all" id='<s:property value="%{key.id}" />'>
				<s:property value="firstName" />&nbsp;<s:property value="lastName" /><br />
			</div>
		</s:iterator>
	</div>

	<div class="title ui-widget-header ui-corner-all">
		Bank Accounts
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
	
</div>

<div class="shadow main ui-corner-all"></div>

<div class="main_content ui-corner-all">

</div>

<script type="text/javascript">
	$(function() {
		$(".clickable").live('click', function() {
			var that = $(this);
			var parent = that.parents(".body");
			if (parent.attr("id") == "bankAccount") {
				$.ajax({
	       			data: {id: that.attr("id")}, 
					url: 'bankaccount/transaction/',
					cache: false,
					error: function(){
						$(".main_content").html("Could not retrieve transactions on this bank account.");
					},
					success: function(html){
						$(".main_content").html(html);
						$(".ui-state-active").remove("ui-state-active");
						that.addClass("ui-state-active");
					}
	      		});
			}
			
			return false;
		});
		
	});
	$("#div_home").addClass("selected");
</script>
