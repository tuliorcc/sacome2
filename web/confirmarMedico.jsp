<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Área Administrativa</jsp:attribute>
    <jsp:body>
        <div class="box pBox">
            <div>Confirmação de Cadastro</div>
            <div>
                Atenção! Deseja cadastrar este médico no sistema?
                <br/>
                Verifique se todas as informações estão corretas:
                <hr>
                <span class="campo">CRM:</span> ${sessionScope.novoMedico.crm}<br/>
                <span class="campo">Nome:</span> ${sessionScope.novoMedico.nome}<br/>
                <span class="campo">Senha: </span> ${sessionScope.novoMedico.senha}<br/>
                <span class="campo">Especialidade:</span> ${sessionScope.novoMedico.especialidade}<br/>
                <div class="btts">
                    <a href="${pageContext.request.contextPath}/admin/gravarMedico">Confirmar</a>
                    <a href="${pageContext.request.contextPath}/admin">Alterar/Retornar</a>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>
