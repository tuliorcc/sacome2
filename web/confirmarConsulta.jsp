<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Área Administrativa</jsp:attribute>
    <jsp:body>
        <div class="box pBox">
            <div>Confirmação de Agendamento</div>
            <div>
                Atenção! Deseja cadastrar esta consulta no sistema?
                <br/>
                Verifique se todas as informações estão corretas:
                <hr>
                <span class="campo">CPF:</span> ${sessionScope.novaConsulta.cpf}<br/>
                <span class="campo">CRM:</span> ${sessionScope.novaConsulta.crm}<br/>
                <span class="campo">Data:</span> ${sessionScope.novaConsulta.dataString}<br/>
                <div class="btts">
                    <a href="${pageContext.request.contextPath}/paciente/gravarConsulta">Confirmar</a>
                    <a href="${pageContext.request.contextPath}/paciente">Alterar/Retornar</a>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>