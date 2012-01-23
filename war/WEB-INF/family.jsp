<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="it.mondovich.data.entities.Person"%>

<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="header.jsp"></jsp:include>
<title>GFamilyBudget</title>
</head>
<body>
	<jsp:include page="restricted.jsp"></jsp:include>

	<jsp:include page="topmenu.jsp"></jsp:include>
	
	<div id="editWindow"><img class="imgWaitAnim" src="/images/loader_anim.gif"></div>

	<div id="content">
		<div id="addPersonDiv" class="buttonwrapper float_r v_margin">
			<a id="addPerson" class="ovalbutton clickable"><span>Add</span></a>
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
	</div>
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
		function openEditDialog(id) {
	    	var ajaxUrl = "editPerson";
	    	var title = (id == undefined) ? 'Add Family Member' : 'Edit Family Member';
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
		
		$(function (){
			$(".clickable").hover(
       		  function (event) {
       		    $(this).addClass("hover");
       		  },
       		  function () {
       		    $(this).removeClass("hover");
       		  }
       		);
			
			jQuery("#editWindow").hide();
			$('#family .clickable').contextMenu('editingMenu', {
               	bindings: {
                	'Edit': function(t) {
               	  		openEditDialog(t.id);
                	},
                	'Delete': function(t) {
               	  		window.location.href = "deletePerson?id="+t.id;
                	}
               	}
             });
		});
		
		$("#addPerson").live('click', 
               function(e) {
   				openEditDialog();
           	}
   		);
		
		$("#div_family").addClass("selected");
	</script>
</body>
</html>