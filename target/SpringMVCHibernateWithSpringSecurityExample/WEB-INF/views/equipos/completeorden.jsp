<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="tg" tagdir="/WEB-INF/tags"%>
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
                
                <div class="panel-heading">Modificar</div>
                
                <div class="panel-body">
                    
                    <form:form method="post" commandName="orden">
                        <h1>Completando Orden</h1>
                        
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>

                          <p>
                            <form:label path="fechaCreacion">Fecha de Creacion</form:label>
                            <form:input path="fechaCreacion" type="date" cssClass="form-control"/>

                        </p>

                         <p>
                            <form:label path="fechaCierre">Fecha de Cierre</form:label>
                            <form:input path="fechaCierre" type="date" cssClass="form-control"/>
                        </p>

                          <p>
                            <form:label path="tipoOrden">Tipo de Orden</form:label>
                            <form:select path="tipoOrden" cssClass="form-control">
                                <form:option value="None">None</form:option>
                                <form:options items="${mantenimientosLista}"/>
                            </form:select>
                        </p>
                        
                         <p>
                            <form:label path="descripcion">Descripcion</form:label>
                            <form:input path="descripcion" cssClass="form-control" />
                        </p>

                        
                        <p>
                            <form:label path="estatus">Estado</form:label>
                            <form:select path="estatus" cssClass="form-control">
                                <form:option value="ABIERTA">ABIERTA</form:option>
                                <form:options items="${estatusLista}"/>
                            </form:select>
                        </p>
                        
                        <p>
                            <form:input type="hidden" path="equipoFk" cssClass="form-control" value="${orden.getEquipoFk()}" />
                        </p>
                        
                        <hr/>
                        
                  <hr/>
                  <center> <input type="submit" value="Completar Orden " class="btn btn-danger" /> </center> 



                    </form:form>
                        
                        
            
            <jsp:useBean id="pagedListHolder" scope="request"
                         type="org.springframework.beans.support.PagedListHolder" />
            <c:url value="completeorden?id=${orden.getId()}" var="pagedLink">
                <c:param name="p" value="~" />
            </c:url>
            
            
              <div class="panel-body">
                    <form method="get" action="addmaterial">
                        <input type="submit" value="Agregar" class="btn btn-success"/>
                        <hr/>
                        
                                Material
                                <input name="material" type="text" cssClass="form-control"/>
                                Precio
                                <input name="precio" type="text" cssClass="form-control"/>
                                Cantidad
                                <input name="cantidad" type="number" cssClass="form-control"/>
                                Condicion
                                <input name="condicion" type="text" cssClass="form-control"/>
                              
                                <input name="id" type="hidden" value="${orden.getId()}" cssClass="form-control"/>
                    </form>
                </div>  
            
            
   
                <h1>Materiales</h1>
              
               
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
                                    <a href="<c:url value="deletematerialc?id=${dato.getId()}"/>"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
                
                  <tg:paging pagedListHolder="${pagedListHolder}"
                           pagedLink="${pagedLink}" />
  

                        
                       
                </div>    

            </div>

        </div>
    </body>
</html>
