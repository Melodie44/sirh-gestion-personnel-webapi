<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Exemple</title>
<link rel="stylesheet" href="webjars/bootstrap/3.3.7/css/bootstrap.css" />
</head>
<body class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>NOM</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listeDepartements}" var="departement">
				<tr>
					<td>${departement.id}</td>
					<td>${departement.nom}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>