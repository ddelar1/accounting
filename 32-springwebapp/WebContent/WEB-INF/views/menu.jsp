<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Menu</h2>

<sec:authorize access="!isAuthenticated()">
	Por favor iniciar session.
</sec:authorize>


<sec:authorize access="isRememberMe()">
	Usuario a iniciado session
	<sec:authentication property="principal" var="principal"/>
	<c:set var="username" value="${principal.usermane}"/>
	<c:out value="${username}"></c:out>
	<br>
	<a href="<c:url value='/logout'/>">Cerrar session</a>
</sec:authorize>

<sec:authorize access="isFullyAuthenticated()">
	Usuario a iniciado session
	<sec:authentication property="principal" var="principal"/>
	<c:set var="username" value="${principal}"/>
	<c:out value="${username}"></c:out>
	<br>
	<a href="<c:url value='/logout'/>">Cerrar session</a>
</sec:authorize>

<%-- <sec:authorize access="isAuthenticated()"> --%>
<!-- 	Usuario a iniciado session -->
<%-- 	<sec:authentication property="principal" var="principal"/> --%>
<%-- 	<c:set var="username" value="${principal}"/> --%>
<%-- 	<c:out value="${username}"></c:out> --%>
<!-- 	<br> -->
<%-- 	<a href="<c:url value='/logout'/>">Cerrar session</a> --%>
<%-- </sec:authorize> --%>


<a href='<c:url value="/usuario" />'>Registrar Usuario</a> <br/>