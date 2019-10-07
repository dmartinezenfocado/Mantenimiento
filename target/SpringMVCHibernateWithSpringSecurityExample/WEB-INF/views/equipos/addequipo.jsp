<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Nuevo Equipo</title>
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
                    
                    <form:form method="post" commandName="equipo">
                        <h1>Nuevo Equipo</h1>
                        
                        <form:errors path="*" element="div" cssClass="alert alert-danger"/>

                        <p>
                            <form:label path="tipo">Tipo</form:label>
                            <form:input path="tipo" cssClass="form-control"/>

                        </p>

                         <p>
                            <form:label path="marca">Marca</form:label>
                            <form:input path="marca" cssClass="form-control"/>
                        </p>

                         <p>
                            <form:label path="modelo">Modelo</form:label>
                            <form:input path="modelo" cssClass="form-control" />
                        </p>
                        
                         <p>
                            <form:label path="ano">Ano</form:label>
                            <form:input path="ano" cssClass="form-control" />
                        </p>

                        
                         <p>
                            <form:label path="serie">Serie</form:label>
                            <form:input path="serie" cssClass="form-control" />
                        </p>

                         <p>
                            <form:label path="ficha">Ficha</form:label>
                            <form:input path="ficha" cssClass="form-control" />
                        </p>

                        

                        
                        <hr/>
                        <input type="submit" value="Guardar" class="btn btn-danger" />


                    </form:form>
                </div>    

            </div>
            
          
        </div>
    </body>
</html>
