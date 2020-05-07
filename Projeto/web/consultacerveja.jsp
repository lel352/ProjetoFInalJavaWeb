<%-- 
    Document   : consultacerveja
    Created on : 02/06/2015, 08:21:00
    Author     : Cássio, Leandro
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
        <script type="text/javascript" src="js/jquery-2.1.4.js"></script>
        <link href="css/signin.css" rel="stylesheet">
        <c:if test="${origem == 1}">
            <title>Consultar Cerveja</title>
        </c:if>    
        <c:if test="${origem == 2}">
            <title>CheckIn Cerveja</title>
        </c:if>        
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
            <form class="form-inline" action="consultarcerveja" method="POST">
                <div class="form-group">
                    <label for="consulta"> Buscar cerveja: </label>
                    <input id="consulta" type="text" name="consulta" value="${param.consulta}" >
                    <input type="submit" value="...">
                </div>                
            </form>
                    <div><p><i class="alert-danger">${erro}</i></p></div>         
            <div class="panel panel-default"method="POST">
                <!-- Default panel contents -->
                <div class="panel-heading">Cerveja</div>
                <!-- Table -->
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-sm-1">#</th>
                            <th class="col-sm-4">Cerveja</th>
                            <th class="col-sm-4">Estilo</th>
                            <th class="col-sm-1"></th>
                            <th class="col-sm-1"></th>
                            <th class="col-sm-1"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty lista}">
                            <tr>
                                <th class="col-sm-2"> 
                                    <i>Não há Cervejas cadastradas.</i>
                                </th>
                            </tr>
                        </c:if> 
                        <c:if test="${origem == 1}">
                            <c:forEach var="cerveja" items="${lista}" >
                                <tr>
                                    <td class="col-sm-1">${cerveja.codigo}</td>
                                    <td class="col-sm-4">${cerveja.nome}</td>
                                    <td class="col-sm-4"><a href="editartipo?codigo=${cerveja.codigoTipo}">${cerveja.nomeTipo}</a></td>                        
                                    <td class="col-sm-1">
                                        <a id="aeditar" href="editarcerveja?codigo=${cerveja.codigo}">Editar</a>
                                    </td>    
                                    <td class="col-sm-1">
                                        <a id="aexcluir" href="excluircerveja?codigo=${cerveja.codigo}">Excluir</a>
                                    <td>                
                                    <td class="col-sm-1">
                                        <a id="aeditar" href="checkin?codigo=${cerveja.codigo}">Checkin</a>
                                    </td>    
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${origem == 2}">
                            <c:forEach var="cerveja" items="${lista}">
                                
                                <tr>
                                    <td class="col-sm-2">${cerveja.codigo}</td>
                                    <td class="col-sm-4">${cerveja.nome}</td>
                                    <td class="col-sm-4"><a href="editartipo?codigo=${cerveja.codigoTipo}">${cerveja.nomeTipo}</a></td>                        
                                    <td class="col-sm-2">
                                        <a id="acheckin" href="checkin?codigo=${cerveja.codigo}">Checkin</a>
                                    </td>    
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>       
    </body>
</html>
