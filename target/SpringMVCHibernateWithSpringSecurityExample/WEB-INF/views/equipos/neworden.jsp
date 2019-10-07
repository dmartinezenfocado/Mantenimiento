<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Nueva Orden</title>
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
                
                <div class="panel-heading">Agregar</div>
                
                <div class="panel-body">
                    
                    <form:form method="post" commandName="orden">
                        <h1>Nueva Orden</h1>
                        
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
                            <form:input type="hidden" path="equipoFk" cssClass="form-control" value="${fk}" />
                        </p>


                        
                        <hr/>
                        <input type="submit" value="Guardar" class="btn btn-danger" />


                    </form:form>
                </div>    

            </div>
            
          
        </div>
    </body>
</html>
