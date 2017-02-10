<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>

<head>
	<title>Lista Klientów</title>
	
	<!-- reference to css files -->
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
		
		<input type="button" value="Dodaj Klienta"
			   onclick="window.location.href='formForAdd'; return false;"
			   class="add-button"/>
			
			<table>
				
				<tr>
					<th>Imię</th>
					<th>Nazwisko</th>
					<th>Email</th>
					<th>Zmień</th>
				</tr>
				
				<c:forEach var="tempCustomer" items="${customers}">
				
					<!-- JSTL: construct an "edit" link -->
					<c:url var="editLink" value="/customer/formForEdit">
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
					
					<!-- JSTL: construct an "delete" link -->
					<c:url var="deleteLink" value="/customer/delete" >
						<c:param name="customerId" value="${tempCustomer.id}"/>
					</c:url>
					
					<tr>
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						<td>
						<a href="${editLink}">Edytuj</a>
						|
						<a href="${deleteLink}"
								onclick="if (!(confirm('Czy na pewno chcesz usunąć dane Klienta?'))) return false">
								Usuń</a>
						</td>
					</tr>
					
				</c:forEach>	
				
			</table>
		</div>
	</div>
	
</body>

</html>