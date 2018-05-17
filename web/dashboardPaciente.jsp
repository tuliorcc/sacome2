<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Área do Paciente</jsp:attribute>
    <jsp:body>
        <div class="box tBox">
            <div>
                Área do Paciente
                <a href="${pageContext.request.contextPath}/paciente?sair">Terminar Sessão</a>
            </div>
        </div>
        <div class="box pBox">
            <div>Agendar Consulta</div>
            <div>
                <c:if test="${!empty requestScope.mensagens}">
                    <ul class="erro">
                    <c:forEach items="${requestScope.mensagens}" var="mensagem">
                        <li>${mensagem}</li>
                    </c:forEach>
                    </ul>
                    <hr>
                </c:if>
                <form action="${pageContext.request.contextPath}/paciente/addConsulta" method="post" class="boxForm">
                    <input type="hidden" name="cpf" value="${sessionScope.cpfPaciente}"/>
                    <label for="data">Data:</label>
                    <input id="data" name="dataConsulta" type="date" value="${sessionScope.novaConsulta.dataConsulta}" />
                    <label for="crm">CRM: <a href="${pageContext.request.contextPath}/listaMedicos">(clique para acessar a lista)</a></label>
                    <input id="crm" name="crm" type="text" value="${(empty sessionScope.novaConsulta.crm)?param.crm:sessionScope.novaConsulta.crm}" />
                    <input type="submit" value="Agendar Consulta"/>
                </form>
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
                            <th>Médico</th>
                            <th>CRM</th>
                            <th>Data</th>
                        </tr>
                        <c:forEach items="${requestScope.listaConsultas}" var="consulta">
                            <tr>
                                <td>${consulta.medico.nome}</td>
                                <td>${consulta.crm}</td>
                                <td>${consulta.dataString}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:template>