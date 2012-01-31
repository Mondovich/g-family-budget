<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="editWindow"><img class="imgWaitAnim" src="/images/loader_anim.gif"></div>

<div id="addBankAccountDiv" class="buttonwrapper float_r v_margin">
	<a id="addBankAccount" class="ovalbutton clickable"><span>Add</span></a>
</div>
<div id="header">
	<table id="bankaccount">
		<tr>
			<th colspan="3">Bank Accounts</th>
		</tr>
		<tr>
			<th>Account name</th>
			<th>Initial Value</th>
			<th>Owners</th>
		</tr>
		<s:iterator value="listOfBankAccount">
			<tr class="clickable" id='<s:property value="%{key.id}" />'>
				<td><s:property value="name" /></td>
				<td>&euro;&nbsp;<input class="flatInput money" disabled="disabled" value="<s:property value="initialValue" />"></input></td>
				<td><s:property value="getOwnersList(person)" /></td>
			</tr>
		</s:iterator>
	</table>
	<s:actionerror />
</div>
<div id="transactions"></div>
	
<div class="contextMenu" id="editingMenu">
     <ul>
       <li id="Edit">
       	<span>Edit</span>
	</li>
       <li id="Delete">
		<span>Delete</span>
	</li>
     </ul>
   </div>
   <script type="text/javascript">

	function openBankAccountDialog(id) {
    	var ajaxUrl = "edit";
    	var title = (id == undefined) ? 'Add Bank Account' : 'Edit Bank Account';
     	jQuery("#editWindow").dialog({
           	title: title,
           	modal: true,
           	autoOpen: true
           });
		$.ajax({
			type: "GET",
			cache: false,
			data: ({id: id}),
			url: ajaxUrl,
			dataType: "html",
			error:function()
			{
				jQuery("#editWindow").html("Error loading data");
			},
			success:function(data)
			{
				jQuery("#editWindow").html(data);
			}
		});
	}
	
	function resizeTransaction() {
		//console.log($(window).height());
		//console.log($("#transactions #content").position().top);
		var h = $(window).height()-$("#transactions #content").position().top - 60;
		$("#transactions #content").height(h);
	}

	$("#addBankAccountOwner").click(function() {
		$("#addBankAccountOwnerForm").submit();
	});
	
	$(function()
       {
           $('.money').maskMoney({thousands:',', decimal:'.', allowZero: true, allowNegative: true});
           $('.money').mask();
           $(".clickable").hover(
      		  function (event) {
      		    $(this).addClass("hover");
      		  },
      		  function () {
      		    $(this).removeClass("hover");
      		  }
      		);
           $("#bankaccount .clickable").live('click', 
           	function(e) {
           		var state = {},
           		id = 'account', url = $(this).attr( 'id' );
           		state[ id ] = url;
           		$.bbq.pushState( state );
           		
           		return false;
           	}
           );
           $(window).bind( 'hashchange', function(e) {
           	var that = $(this), url = $.bbq.getState( 'account' ) || '';
           	if (url == '') return;
           	$("#transactions").html('<div id="content"><img class="imgWaitAnim" src="/images/loader_anim.gif"></div>')
           	resizeTransaction();
			$("#transactions").show();
           	$.ajax({
       			data: {id: url}, 
				url: 'transaction/',
				cache: false,
				error: function(){
					$("#transactions").html("Could not retrieve transactions on this bank account.");
				},
				success: function(html){
					$("#transactions").replaceWith(html);
					resizeTransaction();
				}
      			});
           });
           
           jQuery("#editWindow").hide();
           jQuery("#transactions").hide();
           
           $('#bankaccount .clickable').contextMenu('editingMenu', {
              	bindings: {
               	'Edit': function(t) {
              	  		openBankAccountDialog(t.id);
               	},
               	'Delete': function(t) {
              	  		window.location.href = "delete?id="+t.id;
               	}
              	}
            });
           
           $("#addBankAccount").live('click', 
               function(e) {
   				openBankAccountDialog();
           	}
   		);
           
		$(window).resize(function() {
			resizeTransaction();
		});
           
           $(window).trigger( 'hashchange' );
       });
	$("#div_bankaccount").addClass("selected");
</script>
