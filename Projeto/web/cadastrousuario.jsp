<%-- 
    Document   : cadastrousuario
    Created on : 30/05/2015, 20:07:55
    Author     : leandro
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
                
        <!-- Datepicker -->
        <link href="datepicker/css/datepicker.css" rel="stylesheet">
 
        <link href="css/signin.css" rel="stylesheet">
        <c:if test="${efetuado == true}">
            <script>
                $(document).ready(function () {
                    $('#myModal').modal('show');
                });    
            </script>
        </c:if>        
        
        <title>Dados do Usuário</title>
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
            <h2>Dados de Usuario</h2>
            <form class="form-horizontal" role="form" action="salvarusuario" method="POST">
                <div class="form-group">
                    <div class="col-sm-10">
                        <c:if test="${usuario == null}">
                            <input readonly="readonly" type="hidden" class="form-control" name="codigoTipo" value="0">
                        </c:if>
                        <c:if test="${usuario.codigo > 0}">
                            <input readonly="readonly" type="hidden" class="form-control" name="codigoTipo" placeholder="" value="${usuario.codigo}">
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="descricao">Nome:</label>
                    <div class="col-sm-10">          
                        <input type="text" class="form-control" name="nome" placeholder="Nome" value="${usuario.nome}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Email:</label>
                    <div class="col-sm-10">          
                        <input type="email" class="form-control" name="email" placeholder="Email" value="${usuario.email}">
                    </div>
                </div>    
                <div class="form-group">
                    <label class="control-label col-sm-2" for="nascimento">Nascimento:</label>
                    <!--'<div class="col-sm-2">          
                       < <input type="date" class="form-control" name="nascimento" placeholder="01/01/1996" value="${usuario.nascimento}">
                    </div>-->
                    <div class="col-sm-2" >
                        <input id="datepicker" data-date-format="dd/mm/yyyy" type="text" class="form-control" name="nascimento" placeholder="01/01/1996" value="${usuario.nascimentoS}"/>                        
                    </div>
                </div>        
                <div class="form-group">
                    <label class="control-label col-sm-2" for="login">Login:</label>
                    <div class="col-sm-2">          
                        <c:if test="${usuario.login == null}">
                            <input type="text" class="form-control" name="login" placeholder="login" value="${usuario.login}">
                        </c:if>
                        <c:if test="${usuario.login != null}">
                            <input readonly="readonly" type="text" class="form-control" name="login" placeholder="login" value="${usuario.login}">
                        </c:if>    
                    </div>
                </div>            
                <div class="form-group">
                    <label class="control-label col-sm-2" for="senha">Senha:</label>
                    <div class="col-sm-2">          
                        <input type="password" class="form-control" name="senha" placeholder="senha" value="${usuario.senha}">
                    </div>
                </div>                                
                <div class="form-group">
                    <p><i class="alert-danger">${erro}</i></p>
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
        
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

    <!-- Referência do arquivo JS do plugin após carregar o jquery -->
      <!-- Datepicker -->
      <script src="datepicker/js/bootstrap-datepicker.js"></script>

    <script>
      $(document).ready(function () {
        $('#datepicker').datepicker({
            format: "dd/mm/yyyy",
            language: "pt-BR"
        });
      });
    </script>        
    
    </body>
</html>
