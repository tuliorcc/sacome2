<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Área do Paciente</jsp:attribute>
    <jsp:body>
        <div class="box pBox">
            <div>Área do Paciente</div>
            <div>
                <c:choose>
                    <c:when test="${requestScope.userInvalid}">
                        <span>CPF ou senha inválidos.</span>
                    </c:when>
                    <c:otherwise>
                        <span>Informe seu CPF e Senha para acessar a Área do Paciente</span>
                    </c:otherwise>
                </c:choose>
                <hr>
                <form method="post" action="${pageContext.request.contextPath}/paciente" class="boxForm" id="pacienteForm">
                    <label for="cpf">CPF:</label>
                    <input type="text" name="cpf" id="cpf" maxlength="14" />
                    <label for="senha">Senha:</label>
                    <input type="password" name="senha" id="senha" />
                    <input type="submit" value="Acessar" name="login" />
                </form>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/input.js" defer></script>
    </jsp:body>
</t:template>