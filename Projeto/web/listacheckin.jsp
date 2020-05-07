<%-- 
    Document   : listacheckin
    Created on : 02/06/2015, 08:33:20
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
        <link href="css/signin.css" rel="stylesheet">
        <c:if test="${efetuado == true}">
            <script>
                $(document).ready(function () {
                    $('#myModal').modal('show');
                });
            </script>
        </c:if>
        <title>Listagem de CheckIn</title>
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
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">CheckIn Feitos</div>
                <!-- Table -->
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th class="col-sm-4">Cerveja</th>
                            <th class="col-sm-2">Comentário</th>
                            <th class="col-sm-2">Nota</th>
                            <th class="col-sm-2">Data</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty lista}">
                            <tr class="row">
                                <th class="col-sm-2"> 
                                    <i>Não há checkin feitos.</i>
                                </th>
                            </tr>
                        </c:if>                         
                        <c:forEach var="checkin" items="${lista}">
                            <tr>                                
                                <td class="col-sm-4"><a href="editarcerveja?codigo=${checkin.codigoCerveja}">${checkin.nomeCerveja}</a></td>
                                <td class="col-sm-2">${checkin.cometario}</td> 
                                <td class="col-sm-2">${checkin.nota}</td> 
                                <td class="col-sm-2">${checkin.datas}</td>                                                        
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>      
        
        <div id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title alert-success" >Cadastro de Usuário</h4>
                    </div>
                    <div class="modal-body">
                        <p>${mensagem}</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                        
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </body>
</html>
