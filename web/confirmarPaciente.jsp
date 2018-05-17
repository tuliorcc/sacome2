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
                Atenção! Deseja cadastrar este paciente no sistema?
                <br/>
                Verifique se todas as informações estão corretas:
                <hr>
                <span class="campo">CPF:</span> ${sessionScope.novoPaciente.cpf}<br/>
                <span class="campo">Nome:</span> ${sessionScope.novoPaciente.nome}<br/>
                <span class="campo">Senha:</span> ${sessionScope.novoPaciente.senha}<br/>
                <span class="campo">Telefone:</span> ${sessionScope.novoPaciente.telefone}<br/>
                <span class="campo">Nascimento:</span> ${sessionScope.novoPaciente.dataString}<br/>
                <span class="campo">Sexo:</span> ${sessionScope.novoPaciente.sexo}<br/>
                <div class="btts">
                    <a href="${pageContext.request.contextPath}/admin/gravarPaciente">Confirmar</a>
                    <a href="${pageContext.request.contextPath}/admin">Alterar/Retornar</a>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>