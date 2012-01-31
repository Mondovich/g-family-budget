<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="editWindow">Loading please wait...</div>

<div id="addPersonDiv" class="buttonwrapper float_r v_margin">
	<button id="addPerson" class="clickable"><span>Add</span></button>
</div>
<div id="header">
	<table id="family">
		<tr>
			<th colspan="2">Family</th>
		</tr>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
		</tr>
		<s:iterator value="listOfPerson">
			<tr class="clickable" id='<s:property value="%{key.id}" />'>
				<td><s:property value="firstName" /></td>
				<td><s:property value="lastName" /></td>
			</tr>
		</s:iterator>
	</table>
</div>

<div class="contextMenu" id="editingMenu">
	<ul>
		<li id="Edit"><button>Edit</button></li>
		<li id="Delete"><button>Delete</button></li>
	</ul>
</div>

<script type="text/javascript">

	function openEditDialog(id) {
    	var ajaxUrl = "edit";
    	var title = (id == undefined) ? 'Add Family Member' : 'Edit Family Member';
     	jQuery("#editWindow").dialog({
        	title: title,
        	modal: true,
        	autoOpen: true,
			buttons: {
				"Save": function() {
					$( this ).dialog( "close" );
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			}
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
	
	$(function (){
		$(".clickable").hover(
      		  function (event) {
      		    $(this).addClass("hover");
      		  },
      		  function () {
      		    $(this).removeClass("hover");
      		  }
      		);
		
		$("#editWindow").hide();
		$('#family .clickable').contextMenu('editingMenu', {
           	bindings: {
            	'Edit': function(t) {
           	  		openEditDialog(t.id);
            	},
            	'Delete': function(t) {
           	  		window.location.href = "delete?id="+t.id;
            	}
           	}
         });
		
		$("button").button();
         
         $("#addPerson").live('click', 
              function(e) {
  				openEditDialog();
          	}
  		);
	
	});
	
	$("#div_family").addClass("selected");
</script>