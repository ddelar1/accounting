<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- Load jQuery UI CSS  -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css" />
<!-- Load jQuery JS -->
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<!-- Load jQuery UI Main JS  -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery(".confirm").on("click", function(){
			return confirm("Si eliminas este elemento no se podrá recuperar. ¿Continuar?");
		});
		
		var path = $('#path').val();
		jQuery('#busqueda').autocomplete({
			source: path + "/admin/json/search"
		});
	
		
	});
	
	function start(){
		var url = "/admin/json/prueba";
		var dataJson = {}
	    dataJson["tipoTercero"] = $('#message').val();
		var prueba =  $('#message').val();
		$.ajax({
			type : "POST",
			url : $('#path').val() + url,
			data : {"prueba" : JSON.stringify(dataJson)},
			success : function(data) {
				console.log("SUCCESS: ", data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}
	
</script>
</head>
<body>
	<c:import url="/WEB-INF/views/menu.jsp"></c:import>
	<h1>Admin.jsp</h1>

		<input type="hidden" id="path" value="${pageContext.request.contextPath}"/>
		Buscar administradores: <input type="text" id="busqueda"/>

	
	<sf:form action="${pageContext.request.contextPath}/admin/save" method="post" commandName="admin">
		<table>
			<c:if test="${admin.idAd ne 0}">
				<sf:input path="idAd" type="hidden"/>
				<sf:input path="fechaCreacion" type="hidden"/>
			</c:if>
			<tr>
				<td>Nombre</td>
				<td> <sf:input path="nombre" type="text"/> </td>
			</tr>
			<tr>
				<td>Cargo</td>
				<td> <sf:input path="cargo" type="text"/> </td>
			</tr>
			<tr>
				<td></td>
				<td> <input type="submit" value="Guardar cambios" /> </td>
			</tr>
		</table>
	</sf:form>
	
	<br/>
	<c:out value="${resultado}"></c:out> <br/><br/>
	
	
		<input type="text" name="message" id="message"/>
		<input type="button" value="Ajax" onclick="start()"/>
		<input/>
	
	
	
	
	<%
// 		for(Admin admin : admins){
// 			out.print(admin);
// 		}
	%>
	
	<c:forEach items="${admins}" var="admin">
		<c:out value="${admin}" />
		<a href='<c:url value="/direccion/${admin.idAd}" />'>Direcciones</a> 
		<a href='<c:url value="/admin/${admin.idAd}/update" />'>Actualizar</a>
		<a class="confirm" href='<c:url value="/admin/${admin.idAd}/delete" />'>Eliminar</a>
		<br/>
	</c:forEach>

</body>
</html>