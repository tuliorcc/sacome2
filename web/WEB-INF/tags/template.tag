<%@tag description="Template" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>
<html>
    <head>
        <title><jsp:invoke fragment="title"/></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/default.css" />
    </head>
    <body>
        <div id="top-bar">
            <span>Sistema de Agendamento de Consultas Médicas</span>
            <a href="${pageContext.request.contextPath}/admin">administrativo</a>
        </div>
        <div class="btts">
            <a href="${pageContext.request.contextPath}/paciente">Área do Paciente</a>
            <a href="${pageContext.request.contextPath}/medico">Área do Médico</a>
            <a href="${pageContext.request.contextPath}/listaMedicos">Encontre um Médico</a>
        </div>
        <jsp:doBody/>
    </body>
</html>