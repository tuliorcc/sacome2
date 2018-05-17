<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Área do Médico</jsp:attribute>
    <jsp:body>
        <div class="box tBox">
            <div>
                Área do Médico
                <a href="${pageContext.request.contextPath}/medico?sair">Terminar Sessão</a>
            </div>
        </div>
        <div class="box pBox">
            <div>Minhas Consultas</div>
            <div>
                <c:if test="${empty requestScope.listaConsultas}">
                    Nenhuma consulta encontrada
                </c:if>
                <c:if test="${!empty requestScope.listaConsultas}">
                    <table>
                        <tr>
                            <th>Paciente</th>
                            <th>CPF</th>
                            <th>Data</th>
                        </tr>
                        <c:forEach items="${requestScope.listaConsultas}" var="consulta">
                            <tr>
                                <td>${consulta.paciente.nome}</td>
                                <td>${consulta.cpf}</td>
                                <td>${consulta.dataString}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:template>
