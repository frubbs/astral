<%-- 
    Document   : resultado
    Created on : 02/05/2016, 19:44:19
    Author     : Rafael.Soares
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="Utilitario/estilo1.css">
        <script src="Utilitario/biblioteca.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web I</title>
    </head>
    <body>
        <div class="grp0">
            <h1 class="titulo">Portal do Astral2</h1>
                
            <div class="grp1">
                <img src="imagens/cabecalho.jpg">
            </div>            
        
            <fieldset class="grp4">
                <p class="subtitulo">Resultado apresentado após avaliação dos dados informados</p>
                <p>1. O nome informado foi: ${requestScope.previsao.nome} </p>
                <p>2. A sua data de nascimento informada foi: ${requestScope.previsao.data} </p>
                <p>3. O seu signo é: ${requestScope.previsao.signo} </p>
                <p>4. O seu número da sorte é: ${requestScope.previsao.numero} </p>
                <p>5. O seu nome criptografado é: ${requestScope.previsao.cripto} </p>
                
                <p class="subtitulo1">Imagem Sorteada</p>
                <p class="grp5"><img type="image" src="${requestScope.previsao.url}"></p>
                
                <a href="NovoPortalAstral.html" target="_self">
                    <img src="imagens/voltar3.jpg"> 
                </a>
            </fieldset>
        </div>
    </body>
</html>
