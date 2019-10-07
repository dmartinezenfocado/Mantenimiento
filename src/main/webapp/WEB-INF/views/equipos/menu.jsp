<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        
        <meta charset="UTF-8"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
         <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"> </script>
         <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"> </script>
         <link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
         <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
         <link href="<c:url value='/static/css/index.css' />" rel="stylesheet"></link>
        <title>Mantenimiento</title>
          <script src='https://app.five9.com/consoles/SocialWidget/five9-social-widget.min.js'></script>
          
        <script>       
         $(document).ready(function () {
  var trigger = $('.hamburger'),
      overlay = $('.overlay'),
     isClosed = false;

    trigger.click(function () {
      hamburger_cross();      
    });

    function hamburger_cross() {

      if (isClosed == true) {          
        overlay.hide();
        trigger.removeClass('is-open');
        trigger.addClass('is-closed');
        isClosed = false;
      } else {   
        overlay.show();
        trigger.removeClass('is-closed');
        trigger.addClass('is-open');
        isClosed = true;
      }
  }
  
  $('[data-toggle="offcanvas"]').click(function () {
        $('#wrapper').toggleClass('toggled');
  });  
});

  </script>
  
    </head>
    
    
    <body>
       
<div id="wrapper">
        <div class="overlay"></div>
 
        <!-- Sidebar -->
        <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
            <ul class="nav sidebar-nav">
                <li class="sidebar-brand">
                    <a href="#">
                       Menu
                    </a>
                </li>
                <li>
                    <a href="/Mantenimiento/index">Home</a>
                </li>
                <li>
                    <a href="equiposview">Equipos</a>
                </li>
                <li>
                    <a href="ordenesopen">Ordenes abiertas</a>
                </li>
                <li>
                    <a href="ordenescompletas">Ordenes Completadas</a>
                </li>
                 <li>
                    <a href="/Mantenimiento/list">Usuarios</a>
                </li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Works <span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li class="dropdown-header">Dropdown heading</li>
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li>
                <li>
                    <a href="#">Reportes</a>
                </li>
                <li>
                    <a href="#">Contact</a>
                </li>
                <li>
                    <a target="_blank" href="https://www.instagram.com/delvinmc20">Follow me</a>
                </li>
            </ul>
        </nav>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
                <span class="hamb-top"></span>
    			<span class="hamb-middle"></span>
				<span class="hamb-bottom"></span>
            </button>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                      
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
        
    

<script>
Five9SocialWidget.addWidget({
    type: "chat",
    rootUrl: "https://app.five9.com/consoles/",
    tenant: "ICQ24",
    title: "ICQ24",
    profiles: ["DMInboundMultichannel"],
    showProfiles: true,
    theme: "default-theme.css"});
</script>
    
    
    </body>
</html>
