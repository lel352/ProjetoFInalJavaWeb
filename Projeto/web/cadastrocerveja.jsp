<%-- 
    Document   : cadastrobebida
    Created on : 18/05/2015, 09:00:29
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
        <title>Cadastro de Cerveja</title>
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
            <h2>Cadastro de Cerveja</h2>
            <form class="form-horizontal" role="form" action="salvarcerveja" method="POST">
                <div class="form-group">
                    <label class="control-label col-sm-2">Código:</label>
                    <div class="col-sm-10">
                        <c:if test="${codigoSequencial == 0}">
                            <input readonly="readonly" type="number" class="form-control" name="codigoCerveja" placeholder="" value="${cerveja.codigo}">
                        </c:if>
                        <c:if test="${codigoSequencial > 0}">
                            <input readonly="readonly" type="number" class="form-control" name="codigoCerveja" placeholder="" value="${codigoSequencial}">
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Nome:</label>
                    <div class="col-sm-10">          
                        <input type="text" class="form-control" name="descricao" placeholder="Nome da Cerveja" value="${cerveja.nome}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="tipocerveja">Tipo da Cerveja:</label>
                    <div class="col-sm-10">          
                        <select class="form-control" name="tipo">
                            <c:if test="${listaTipoCerveja == null}">
                                <option selected="selected" value="-1">Lista esta vazia, cadastre algum tipo de cerveja</option>
                            </c:if>
                            <c:if test="${listaTipoCerveja != null}">
                                <option selected="selected" value="0">Selecione um tipo</option>
                                <c:if test="${codigoSequencial == 0}">
                                    <c:forEach var="tipoCerveja" items="${listaTipoCerveja}">
                                        <c:if test="${cerveja.codigoTipo == tipoCerveja.codigo}">
                                            <option selected="selected" value="${tipoCerveja.codigo}">${tipoCerveja.nome}</option>
                                        </c:if>
                                        <c:if test="${cerveja.codigoTipo != tipoCerveja.codigo}">
                                            <option value="${tipoCerveja.codigo}">${tipoCerveja.nome}</option>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${codigoSequencial > 0}">
                                    <c:forEach var="tipoCerveja" items="${listaTipoCerveja}">
                                        <option value="${tipoCerveja.codigo}">${tipoCerveja.nome}</option>
                                    </c:forEach>
                                </c:if>    
                            </c:if>        
                        </select>
                    </div>    
                </div>
                <div class="form-group">
                    <p><i class="alert-danger col-sm-2">${erro}</i></p>
                </div>
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Salvar</button>
                    </div>
                </div>
            </form>
        </div>
                
        <div id="myModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title alert-success" >Cadastro de Cerveja</h4>
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
