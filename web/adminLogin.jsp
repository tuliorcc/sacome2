<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Área Administrativa</jsp:attribute>
    <jsp:body>
        <div class="box pBox">
            <div>Área Administrativa</div>
            <div>
                <c:choose>
                    <c:when test="${requestScope.adminInvalid}">
                        <span>Usuário ou senha inválidos.</span>
                    </c:when>
                    <c:otherwise>
                        <span>Informe seu Usuário e Senha para acessar a Área Administrativa</span>
                    </c:otherwise>
                </c:choose>
                <hr>
                <form method="post" action="${pageContext.request.contextPath}/admin" class="boxForm" id="adminForm">
                    <label for="usuario">Usuário:</label>
                    <input type="text" name="usuario" id="usuario" />
                    <label for="senha">Senha:</label>
                    <input type="password" name="senha" id="senha" />
                    <input type="submit" value="Acessar" name="login" />
                </form>
            </div>
        </div>
    </jsp:body>
</t:template>
