<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Encontre um Médico</jsp:attribute>
    <jsp:body>
        <div id="lista" class="box">
            <div>Encontre um Médico</div>
            <div>        
                <form method="post">
                    <label for="especialidade">Filtrar por especialidade:</label>
                    <select name="especialidade" id="especialidade">
                        <option value="">Todas</option>
                        <option value="Clínico Geral">Clínico Geral</option>
                        <option value="Acupuntura">Acupuntura</option>
                        <option value="Cardiologia">Cardiologia</option>
                        <option value="Dermatologia">Dermatologia</option>
                        <option value="Geriatria">Geriatria</option>
                        <option value="Ginecologia">Ginecologia</option>
                        <option value="Neurologia">Neurologia</option>
                        <option value="Oftalmologia">Oftalmologia</option>
                        <option value="Ortopedia">Ortopedia</option>
                        <option value="Otorrinolaringologia">Otorrinolaringologia</option>
                        <option value="Pediatria">Pediatria</option>    
                        <option value="Pneumologia">Pneumologia</option>
                        <option value="Urologia">Urologia</option>           
                    </select>
                    <input type="submit" value="Filtrar"/>
                </form>
                <hr>
                <c:if test="${empty requestScope.listaMedicos}">
                    Nenhum médico encontrado
                </c:if>
                <c:if test="${!empty requestScope.listaMedicos}">
                    <table>
                        <tr>
                            <th>CRM</th>
                            <th>Nome</th>
                            <th>Especialidade</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${requestScope.listaMedicos}" var="medico">
                            <tr>
                                <td>${medico.crm}</td>
                                <td>${medico.nome}</td>
                                <td>${medico.especialidade}</td>
                                <td>
                                    <c:if test="${!empty sessionScope.acessoPaciente}">
                                        <a href="${pageContext.request.contextPath}/paciente?crm=${medico.crm}">agendar</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:template>