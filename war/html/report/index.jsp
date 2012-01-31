<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
		Reports
	</div>
	<script type="text/javascript">
		$("#div_reports").addClass("selected");
	</script>
</body>
</html>