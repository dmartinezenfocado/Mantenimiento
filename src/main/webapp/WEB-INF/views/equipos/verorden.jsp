<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
                    
                    <form:form method="post" commandName="orden">
                        <h1>Datos del la orden ${orden.getId()} para el equipo: ${ficha}</h1>
                        
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>

                        <p>
                            <form:label path="fechaCreacion">Fecha de creacion: ${orden.getFechaCreacion()}</form:label>
                     

                        </p>

                         <p>
                            <form:label path="fechaCierre">Fecha de Cierre: ${orden.getFechaCierre()}</form:label>
                 
                            
                        </p>

                         <p>
                            <form:label path="tipoOrden">Tipo: ${orden.getTipoOrden()}</form:label>
           
                        </p>

                         <p>
                            <form:label path="descripcion">Descripcion: ${orden.getDescripcion()}</form:label>
                        </p>
                        
                          <p>
                            <form:label path="estatus">Status: ${orden.getEstatus()}</form:label>
                        </p>
                        
                        
                        <hr/>


                    </form:form>
                </div>    

            </div>
                
                 <hr/>
       
                
        <div class="container">
            
            <jsp:useBean id="pagedListHolder" scope="request"
                         type="org.springframework.beans.support.PagedListHolder" />
            <c:url value="verorden?id=${orden.getId()}&ficha=${ficha}" var="pagedLink">
                <c:param name="p" value="~" />
            </c:url>
            
            
            <div class="row">
   
                <h1>Lista de Materiales</h1>
              
               
                <table class="table table-bordered table-striped table-hover">
                    <thead>
                        <tr>
                     
                            <th>Material</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Total</th>
                            <th>Condicion</th>
                            <th>Accion</th>
                        </tr>
                    </thead>
                    <tbody> 
                        <c:forEach items="${pagedListHolder.pageList}" var="dato">
                            <tr>
                                
                                <td><c:out value="${dato.getMaterial()}" /></td>
                                <td><c:out value="${dato.getPrecio()}" /></td>
                                <td><c:out value="${dato.getCantidad()}" /></td>
                                <td><c:out value="${dato.getTotal()}" /></td>
                                <td><c:out value="${dato.getCondicion()}" /></td>
                                <td>
                                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                                    <a href="<c:url value="deletematerial?id=${dato.getId()}&ficha=${ficha}"/>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                                    </sec:authorize>
                                </td>
                            </tr>
                            
                        </c:forEach>
                    </tbody>
                    
                    
                </table>
                
                <h3>Total Facturado: ${total}</h3>
                
                  <tg:paging pagedListHolder="${pagedListHolder}"
                           pagedLink="${pagedLink}" />
                
             
            </div>
        </div>
            
          
        </div>
                
        
    </body>
</html>
