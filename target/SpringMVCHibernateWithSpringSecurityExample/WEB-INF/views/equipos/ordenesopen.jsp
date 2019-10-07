<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Ordenes Abiertas</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        
        
    </head>
    <body>
        
         <%@include file="menu.jsp" %>
         <%@include file="authheader.jsp" %>
        
        
        <div class="container">
            
            <jsp:useBean id="pagedListHolder" scope="request"
                         type="org.springframework.beans.support.PagedListHolder" />
            <c:url value="ordenesview" var="pagedLink">
                <c:param name="p" value="~" />
            </c:url>
            
            
            <div class="row">
                <h1>Ordenes Abiertas</h1>
              
                <div class="panel-body">
                    <form method="get" action = "ordenesview">
                                <input name="busqueda" type="text" cssClass="form-control"/>
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                <input type="submit" value="Buscar" class="btn btn-success"/>
                    </form>
                </div>    
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Numero de Orden</th>
                            <th>Fecha de Creacion</th>
                            <th>Fecha de Cierre</th>
                            <th>Tipo de orden</th>
                            <th>Descripcion</th>
                            <th>Estatus</th>
                            <th>Accion</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <c:forEach items="${pagedListHolder.pageList}" var="dato">
                            <tr>
                                <td><c:out value="${dato.getId()}" /></td>
                                <td><c:out value="${dato.getFechaCreacion()}" /></td>
                                <td><c:out value="${dato.getFechaCierre()}" /></td>
                                <td><c:out value="${dato.getTipoOrden()}" /></td>
                                <td><c:out value="${dato.getDescripcion()}" /></td>
                                <td><c:out value="${dato.getEstatus()}" /></td>
                                <td>
                                    <a href="<c:url value="verequipo?id=${dato.getId()}"/>"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>&nbsp;&nbsp;
                                    <a href="<c:url value="editequipo?id=${dato.getId()}"/>" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>&nbsp;&nbsp;
                                    <a href="<c:url value="deleteequipo?id=${dato.getId()}"/>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
                
                  <tg:paging pagedListHolder="${pagedListHolder}"
                           pagedLink="${pagedLink}" />
                
             
            </div>
        </div>
    </body>
</html>
