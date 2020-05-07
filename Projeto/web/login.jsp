<%-- 
    Document   : login
    Created on : 11/05/2015, 10:37:52
    Author     : Delphi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Carregando o CSS do Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen" />		
        <!-- jQuery (plugins em JavaScript) -->
        <script type="text/javascript" src="https://code.jquery.com/jquery.js"></script>
        <!-- componentes JavaScript do bootstrap -->	
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link href="css/signin.css" rel="stylesheet">
        <title>Login</title>        
    </head>
    <body>
        <div class="container">
            
            <form class="form-signin" action="entrarlogin" method="post">
                <h2 class="form-signin-heading">Bem-Vindo!</h2>
                <h4><i class="alert-danger">${erro}</i><h4>
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="text" name="login" id="inputEmail" class="form-control" placeholder="login" required autofocus>
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" name="senha" id="inputPassword" class="form-control" placeholder="Senha" required>           
              <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            </form>

        </div> <!-- /container -->
    </body>
</html>
