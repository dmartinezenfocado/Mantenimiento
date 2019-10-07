<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Equipos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"> </script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"> </script>
        <link href="<c:url value='/static/css/index.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
         <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
         
         <link rel="stylesheet" href="https://app.five9.com/consoles/ProactiveChat/stylesheets/five9proactivechat.css">
         <script src="https://app.five9.com/consoles/ProactiveChat/javascripts/five9proactivechat.js"></script>
         
         
        
    </head>
    <body>
        
       
         <%@include file="menu.jsp" %>
         <%@include file="authheader.jsp" %>	
        <div class="container">
           
            <jsp:useBean id="pagedListHolder" scope="request"
                         type="org.springframework.beans.support.PagedListHolder" />
            <c:url value="equiposview" var="pagedLink">
                <c:param name="p" value="~" />
            </c:url>
            
            
            <div class="row">
                <h1>Equipos</h1>
                <p>
                    <a href="<c:url value="addequipo"/>" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Agregar</a>
                </p>
                <div class="panel-body">
                    <form method="get" action = "equiposview">
                                <input name="busqueda" type="text" cssClass="form-control"/>
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                <input type="submit" value="Buscar" class="btn btn-success"/>
                    </form>
                </div>    
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                     
                            <th>Tipo</th>
                            <th>Marca</th>
                            <th>Modelo</th>
                            <th>Ano</th>
                            <th>Serie</th>
                            <th>Ficha</th>
                            <th>Accion</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <c:forEach items="${pagedListHolder.pageList}" var="dato">
                            <tr>
                                
                                <td><c:out value="${dato.getTipo()}" /></td>
                                <td><c:out value="${dato.getMarca()}" /></td>
                                <td><c:out value="${dato.getModelo()}" /></td>
                                <td><c:out value="${dato.getAno()}" /></td>
                                <td><c:out value="${dato.getSerie()}" /></td>
                                <td><c:out value="${dato.getFicha()}" /></td>
                                <td>
                                    <a href="<c:url value="verequipo?id=${dato.getId()}"/>"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>&nbsp;&nbsp;
                                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <a href="<c:url value="editequipo?id=${dato.getId()}"/>" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>&nbsp;&nbsp;
                                    <a href="<c:url value="deleteequipo?id=${dato.getId()}"/>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                    </sec:authorize>
                                    <a href="<c:url value="neworden?id=${dato.getId()}"/>"><span class="glyphicon glyphicon-file" aria-hidden="true"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
                
                  <tg:paging pagedListHolder="${pagedListHolder}"
                           pagedLink="${pagedLink}" />
                
             
            </div>
        </div>
                  
                  
                   <script>  
                       
                       var options = {
restAPI: 'https://app.five9.com',
chatConsole: 'https://app.five9.com/consoles/ChatConsole/index.html',
tenant: 'ICQ24',
notificationType: 'notification'
};
Five9ProactiveChat.init(options);

                       </script>
    </body>
</html>
