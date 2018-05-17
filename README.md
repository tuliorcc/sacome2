# Desenvolvimento de Software para Web
## Atividade Avaliativa 1 - 20680 - TURMA B - Quinta-feira
## Sistema para agendamento de consultas médicas

O sistema deve possuir um cadastro de pacientes, com os seguintes dados: 
#### CPF, nome, senha, telefone, sexo e data de nascimento.

O sistema deve possuir um cadastro de médicos, com os seguintes dados: 
#### CRM, nome, senha e especialidade.

O sistema deve possuir um cadastro de consultas, com os seguintes dados: CPF do
paciente, CRM do médico e data do exame.

O sistema deve atender aos seguintes requisitos:
- R1: Cadastro de médicos (requer login de administrador)
- R2: Cadastro de pacientes (requer login de administrador)
- R3: Listagem de todos os médicos em uma única página (não requer login)
- R4: Listagem de todos os médicos por especialidade (não requer login)
- R5: Agendamento de exame com um médico (requer login do paciente via
CPF + senha). Depois de fazer login, o paciente pode cadastrar uma consulta.
Para isso, deve escolher um médico (digitando seu CRM ou escolhendo a
partir de uma lista), uma data, e deve ser gravado o exame na base de dados.
- R6: Listagem de todas as consultas de um paciente (requer login do paciente
via CPF + senha). Depois de fazer login, o paciente pode visualizar todas as
suas consultas gravadas.
- R7: O sistema não deve permitir o cadastro de consultas de um mesmo
médico ou de um mesmo paciente em uma mesma data.
- R8: Listagem de todas as consultas de um médico (requer login do médico via
CRM + senha). Depois de fazer login, o médico pode visualizar todas as suas
consultas gravadas.
O sistema deve tratar todos os erros possíveis (cadastros duplicados, problemas
técnicos, etc) mostrando uma página de erros amigável ao usuário e registrando o
erro no console, em arquivo ou na base de dados.
