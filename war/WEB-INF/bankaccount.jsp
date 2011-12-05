<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="it.mondovich.data.entities.BankAccount"%>

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
	
	<div id="content">
		<div id="header">
			<table id="bankaccount">
				<tr>
					<th colspan="4">Bank Accounts</th>
				</tr>
				<tr>
					<th>Account name</th>
					<th>Initial Value</th>
					<th>Owners</th>
					<th>&nbsp;</th>
				</tr>
				<s:iterator value="listOfBankAccount">
					<tr class="clickable" id='<s:property value="%{key.id}" />' url='bankAccountDetails'>
						<td><s:property value="name" /></td>
						<td>&euro;&nbsp;<input class="flatInput money" disabled="disabled" value="<s:property value="initialValue" />"></input></td>
						<td><s:property value="getOwnersList(person)" /></td>
						<td>
							<div class="buttonwrapper">
								<s:url id="editUrl" action="editBankAccount">
									<s:param name="id" value="%{key.id}"></s:param>
								</s:url>
								<s:a cssClass="ovalbutton" href="%{editUrl}">
									<span>Edit</span>
								</s:a>
							</div>
							<div class="buttonwrapper">
								<s:url id="deleteUrl" action="deleteBankAccount">
									<s:param name="id" value="%{key.id}"></s:param>
								</s:url>
								<s:a cssClass="ovalbutton" href="%{deleteUrl}">
									<span>Delete</span>
								</s:a>
							</div>
						</td>
					</tr>
				</s:iterator>
				<form id="addBankAccountForm" action="newBankAccount" method="post">
					<s:push value="bankAccount">
						<tr>
							<td><input name="name" type="text"
								value="<s:property value="name" />"></input></td>
							<td>&euro;&nbsp;<input class="money" name="initialValue" type="text"
								value="<s:property value="initialValue" />"></input</td>
							<td>
								<select name="owners" size="4" multiple="multiple">
									<s:iterator value="listOfPersons">
										<option name="id" value="<s:property value="key.id"/>"><s:property value="firstName"/> <s:property value="lastName"/></option>
									</s:iterator>
								</select>
							<td>
								<div class="buttonwrapper">
									<s:if test="key != null">
										<input name="id" type="hidden"
											value="<s:property value="key.id" />">
										<a id="addBankAccount" class="ovalbutton clickable"><span>Save</span></a>
									</s:if>
									<s:else>
										<a id="addBankAccount" class="ovalbutton clickable"><span>Add</span></a>
									</s:else>
								</div>
							</td>
						</tr>
					</s:push>
				</form>
			</table>
			<s:actionerror />
		</div>
		<div id="movements">
		</div>
	</div>
	<script type="text/javascript">
		$("#addBankAccount").click(function() {
			$("#addBankAccountForm").submit();
		});
		
		$("#addBankAccountOwner").click(function() {
			$("#addBankAccountOwnerForm").submit();
		});
		
		$("#bankaccount").ready(function()
        {
            $('.money').maskMoney({thousands:',', decimal:'.', allowZero: true, allowNegative: true});
            $('.money').mask();
            $(".clickable").hover(
       		  function () {
       		    $(this).addClass("hover");
       		  },
       		  function () {
       		    $(this).removeClass("hover");
       		  }
       		);
            $(".clickable").click(
            	function() {
            		var id = $(this).attr('id');
            		window.location.hash = id;
            		var url = $(this).attr('url');
            		$.ajax({
            			data: {id: id}, 
						url: url,
						context: document.body,
						cache: false,
						error: function(){
							$("#movements").html("Could not retrieve movements on this bank account.");
						},
						success: function(html){
							$("#movements").replaceWith(html);
						}
           			});
            	}
            );
        });
		$("#div_bankaccount").addClass("selected");
	</script>
</body>
</html>