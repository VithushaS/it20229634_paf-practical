<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.Employee"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/employees.js"></script>
<title>Employee management</title>
</head>
<body>
<h1>Employee management</h1>

<form id="formEmployee" name="formEmployee" method="post" action="employees.jsp">
 Employee name:
<input id="name" name="name" type="text"
 class="form-control form-control-sm">
<br> Employee nic:
<input id="nic" name="nic" type="text"
 class="form-control form-control-sm">
<br> Employee age:
<input id="agee" name="age" type="text"
 class="form-control form-control-sm">
<br> Employee contact:
<input id="contact" name="contact" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidEmployeeIDSave" name="hidEmployeeIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divEmployeesGrid">
<%
  Employee employeeObj = new Employee();
  out.print(employeeObj.readEmployee());
%>
</div>
</body>
</html>