<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">SACoMe - Área Administrativa</jsp:attribute>
    <jsp:body>
        <div class="box tBox">
            <div>
                Área Administrativa
                <a href="${pageContext.request.contextPath}/admin?sair">Terminar Sessão</a>
            </div>
        </div>
        <div class="box pBox">
            <div>Cadastro de Médicos</div>
            <div>
                <c:if test="${!empty requestScope.med_mensagens}">
                    <ul class="erro">
                    <c:forEach items="${requestScope.med_mensagens}" var="mensagem">
                        <li>${mensagem}</li>
                    </c:forEach>
                    </ul>
                    <hr>
                </c:if>
                <form action="${pageContext.request.contextPath}/admin/addMedico" method="post" class="boxForm">
                    <label for="med_crm">CRM:</label>
                    <input id="med_crm" name="crm" type="text" value="${sessionScope.novoMedico.crm}" />
                    <label for="med_nome">Nome:</label>
                    <input id="med_nome" name="nome" type="text" value="${sessionScope.novoMedico.nome}" />
                    <label for="med_senha">Senha:</label>
                    <input id="med_senha" name="senha" type="text" value="${sessionScope.novoMedico.senha}" />
                    <label for="med_especialidade">Especialidade:</label>
                    <select id="med_especialidade" name="especialidade">
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
                    <input type="submit" value="Cadastrar Médico"/>
                </form>
            </div>
        </div>
        <div class="box pBox">
            <div>Cadastro de Pacientes</div>
            <div>
                <c:if test="${!empty requestScope.pac_mensagens}">
                    <ul class="erro">
                    <c:forEach items="${requestScope.pac_mensagens}" var="mensagem">
                        <li>${mensagem}</li>
                    </c:forEach>
                    </ul>
                    <hr>
                </c:if>
                <form action="${pageContext.request.contextPath}/admin/addPaciente" method="post" class="boxForm">
                    <label for="pac_cpf">CPF:</label>
                    <input id="pac_cpf" name="cpf" type="text" value="${sessionScope.novoPaciente.cpf}" maxlength="14" />
                    <label for="pac_nome">Nome:</label>
                    <input id="pac_nome" name="nome" type="text" value="${sessionScope.novoPaciente.nome}" />
                    <label for="pac_senha">Senha:</label>
                    <input id="pac_senha" name="senha" type="text" value="${sessionScope.novoPaciente.senha}" />
                    <label for="pac_telefone">Telefone:</label>
                    <input id="pac_telefone" name="telefone" type="text" value="${sessionScope.novoPaciente.telefone}" />
                    <label for="pac_dataDeNascimento">Data de nascimento:</label>
                    <input id="pac_dataDeNascimento" name="dataDeNascimento" type="date" value="${sessionScope.novoPaciente.dataDeNascimento}" />
                    <label for="pac_sexo">Sexo:</label>
                    <select id="pac_sexo" name="sexo">
                        <option value="F">Feminino</option>
                        <option value="M">Masculino</option>
                        <option value="X">Não Especificado</option>          
                    </select>
                    <input type="submit" value="Cadastrar Paciente"/>
                </form>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/input.js" defer></script>
    </jsp:body>
</t:template>
