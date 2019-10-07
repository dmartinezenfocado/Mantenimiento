<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        
    </head>
    <body>
        
        
        <%@include file="menu.jsp" %>
         <%@include file="authheader.jsp" %>
        
        <div class="container">
            
            <ol class="breadcrumb">
                <li><a href="<c:url value="equiposview" />">Listado de Equipos</a></li>
                <li class="active">Agregar</li>
               
            </ol>
                
            <div class="panel panel-primary">
                
                <div class="panel-heading">Datos</div>
                
                <div class="panel-body">
                    
                    <form:form method="post" commandName="equipo">
                        <h1>Datos del Equipo: ${equipo.getFicha()}</h1>
                        
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>

                        <p>
                            <form:label path="tipo">Tipo: ${equipo.getTipo()}</form:label>
                     

                        </p>

                         <p>
                            <form:label path="marca">Marca: ${equipo.getMarca()}</form:label>
                 
                            
                        </p>

                         <p>
                            <form:label path="modelo">Modelo: ${equipo.getModelo()}</form:label>
           
                        </p>

                         <p>
                            <form:label path="ano">Ano: ${equipo.getAno()}</form:label>
                        </p>
                        
                          <p>
                            <form:label path="serie">Serie: ${equipo.getSerie()}</form:label>
                        </p>
                        
                          <p>
                            <form:label path="ficha">Ficha ${equipo.getFicha()}</form:label>
                        </p>
                        <hr/>


                    </form:form>
                        
                        
      
                        
                </div>    
                
  

            </div>

                
                 <hr/>
                 

          
        </div>
                
                
                 <div class="container">
            
            <jsp:useBean id="pagedListHolder" scope="request"
                         type="org.springframework.beans.support.PagedListHolder" />
            <c:url value="verequipo?id=${equipo.getId()}" var="pagedLink">
                <c:param name="p" value="~" />
            </c:url>
            
            
            <div class="row">
                
                 <p>
                    <a href="<c:url value="neworden?id=${equipo.getId()}"/>" class="btn btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Agregar</a>
                </p>
                
                <h1>Lista de Ordenes</h1>
              
               
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                            <th># Orden</th>
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
                                   
                                    <a href="<c:url value="verorden?id=${dato.getId()}&ficha=${equipo.getFicha()}"/>"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>&nbsp;&nbsp;
                                    
                                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <a href="<c:url value="editorden?id=${dato.getId()}"/>" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>&nbsp;&nbsp;
                                    <a href="<c:url value="deleteorden?id=${dato.getId()}"/>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>&nbsp;&nbsp;&nbsp;
                                    </sec:authorize>
                                    <a href="<c:url value="completeorden?id=${dato.getId()}"/>"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></a>
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
