<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="it.mondovich.data.entities.BankAccount"%>

<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/main.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="/js/jquery.maskMoney.js"></script>
<title>GFamilyBudget</title>
</head>
<body>
	<jsp:include page="restricted.jsp"></jsp:include>
	
	<jsp:include page="topmenu.jsp"></jsp:include>
	
	<div id="content">
		<div id="header">
			<table id="bankaccount">
				<tr>
					<th colspan="3">Bank Accounts</th>
				</tr>
				<tr>
					<th>Account name</th>
					<th>Initial Value</th>
					<th>&nbsp;</th>
				</tr>
				<s:iterator value="listOfBankAccount">
					<tr>
						<td><s:property value="name" /></td>
						<td><s:property value="initialValue" /></td>
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
							<td><input id="initialValue" type="text"
								value="<s:property value="initialValue" />"></input</td>
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
		</div>
	</div>
	<script type="text/javascript">
		$("#addBankAccount").click(function() {
			$("#addBankAccountForm").submit();
		});
		
		$("#addBankAccountForm").ready(function()
        {
            $('#initialValue').maskMoney({thousands:',', decimal:'.'});
        });
	</script>
</body>
</html>