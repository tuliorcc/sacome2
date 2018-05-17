<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:remove scope="session" var="novoMedico" />
<c:remove scope="session" var="novoPaciente" />

<t:template>
    <jsp:attribute name="title">SACoMe - Início</jsp:attribute>
    <jsp:body>
        <c:if test="${!empty mensagem}">
            ${mensagem}
            <hr>
        </c:if>
    </jsp:body>
</t:template>