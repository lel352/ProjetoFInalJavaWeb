<%-- 
    Document   : consultaTipo
    Created on : 01/06/2015, 13:28:05
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
        <title>Consultar Tipo de Cerveja</title>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-slide-dropdown">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-slide-dropdown">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cerveja <span class="caret"></span></a>				
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="cadastrotipo">Cadastro de Tipo de Cerveja</a></li>
                                <li><a href="cadastrocerveja">Cadastro de Cerveja </a></li>
                                <li><a href="consultartipo">Consular Tipo de Cerveja</a></li>
                                <li><a href="consultarcerveja">Consular Cerveja</a></li>
                            </ul>                
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Checkin <span class="caret"></span></a>				
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="origemcheckin">Efetuar Checkin</a></li>
                                <li><a href="listacheckin">Checkin Feitos </a></li>                                
                            </ul>                
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuario <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="cadastrousuario">Dados do Usuario</a></li>
                                <li class="divider"></li>
                                <li><a href="sair">Sair</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>        
        <div class="container">
            <form class="form-inline" action="consultartipo" method="POST">
                <div class="form-group">
                    <label for="consulta"> Buscar tipo de cerveja: </label>
                    <input id="consulta" type="text" name="consulta" value="${param.consulta}" >
                    <input type="submit" value="...">
                </div>
                <div class="form-group">
                    <a href="consultartipo"> Listar todos</a>
                </div>
            </form>
            <div><p><i class="alert-danger">${erro}</i></p></div> 
            <div class="panel panel-default" method="POST">
                <!-- Default panel contents -->
                <div class="panel-heading">Tipos de Cerveja</div>
                <!-- Table -->
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-sm-2">#</th>
                            <th class="col-sm-5">Tipo de Cerveja</th>
                            <th class="col-sm-1"></th>
                            <th class="col-sm-1"></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <c:if test="${empty lista}">
                            <tr>
                                <th class="col-sm-2"> 
                                    <i>Não há tipo de cerveja cadastrado.</i>
                                </th>
                            </tr>
                        </c:if> 
                        <c:forEach var="tipoCerveja" items="${lista}" >
                            <tr>
                                <td class="col-sm-2">${tipoCerveja.codigo}</td>
                                <td class="col-sm-5">${tipoCerveja.nome}</td>
                                <td class="col-sm-1">
                                    <a id="aeditar" href="editartipo?codigo=${tipoCerveja.codigo}">Editar</a>
                                </td>    
                                <td class="col-sm-1">
                                    <a id="aexcluir" href="excluirtipo?codigo=${tipoCerveja.codigo}">Excluir</a>
                                </td>                
                            </tr>
                            </c:forEach>
                    </tbody>
                </table>    
            </div>
        </div> 
    </body>
</html>
