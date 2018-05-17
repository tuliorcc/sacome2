<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Área do Médico</jsp:attribute>
    <jsp:body>
        <div class="box pBox">
            <div>Área do Médico</div>
            <div>
                <c:choose>
                    <c:when test="${requestScope.docInvalid}">
                        <span>CRM ou senha inválidos.</span>
                    </c:when>
                    <c:otherwise>
                        <span>Informe seu CRM e Senha para acessar a Área do Médico</span>
                    </c:otherwise>
                </c:choose>
                <hr>
                <form method="post" action="${pageContext.request.contextPath}/medico" class="boxForm" id="medicoForm">
                    <label for="crm">CRM:</label>
                    <input type="text" name="crm" id="crm" />
                    <label for="senha">Senha:</label>
                    <input type="password" name="senha" id="senha" />
                    <input type="submit" value="Acessar" name="login" />
                </form>
            </div>
        </div>
    </jsp:body>
</t:template>

