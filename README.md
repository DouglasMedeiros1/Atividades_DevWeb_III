
# ATV IV

## Como rodar?

### Como abrir o Projeto:

Antes dde tudo verifique se possui o eclipse e o insominia e o MysqlWorkbench instalados em  sua maquina. (Sem eles não será possivel executar o projeto)

Inicie abrindo o Eclipse, e no canto superior esquerdo clique em "files" e em seguida em "Open Porjects from Files Systems"

Navegue até onde o repositorio foi clonado e digite finish

### Como executar:

Abra o MysqlWorkbench e crie uma nova base de dados chamada base.

Acesse o arquivo AutomanagerApplication.java no eclipse, que se encontra nas pastas automanager/ src/ main/ java/ com/ autobots/ automanager

Clique no botão verde com um simbolo de "play" e defina: Run As Java Application

### Como testar:

Abra o Insominia e crie uma nova coleção (O nome não importa)

Em seguida crie um http request, defina o metodo que deseja utilizar e defina a rota que deseja acessar (Todas devem começar com "http://localhost:8080/")


## Proposta da Atividade 
<br>

Contextualização:
<br>

Segurança pode ser definida como ação ou efeito de tornar algo ou a si mesmo seguro, 
com estabilidade ou firmeza. Também pode-se imputar o significado de estado, 
qualidade ou condição de quem ou do que está livre de perigos, incertezas, assegurado 
de danos e riscos eventuais; situação em que nada há a temer.
<br>

Quando transportado para o ambiente da computação e sistema de informação o 
significado de segurança torna-se mais específico, partindo do conceito de segurança da 
informação.
<br>

A segurança da informação está diretamente relacionada com a proteção de um 
conjunto de informações, dados, no sentido de preservar o valor que possuem para um 
indivíduo ou uma organização. Portanto, a segurança da informação é o conceito por 
trás da defesa dos dados, detalhes e afins para assegurar que eles estejam acessíveis 
somente aos seus responsáveis de direito ou as pessoas às quais foram enviados. Para 
isto, seguem-se os seguintes pilares: confidencialidade, integridade, disponibilidade, 
autenticidade e legalidade.
<br>

Cada pilar descreve um aspecto, que se desdobra em uma junção de partes que vão 
desde a concepção, chegando ao desenvolvimento e por fim na implantação e 
disponibilização de um sistema ou aplicação. Dentre estas partes destacam-se dois 
conceitos denominados de autorização e autenticação.
<br>

Autenticação e autorização são dois termos, que correspondem a duas técnicas de 
aplicação comuns para a maioria dos sistemas de informação e aplicações. Com estas 
técnicas garante-se mais segurança aos recursos, informações ou dados, que devem ser 
protegidos.
<br>

A autenticação verifica a identidade digital do usuário, ou seja, processo de verificação 
de uma identidade. Em termos mais simples, é quando o usuário prova de fato quem 
ele é. Sendo que, para sistemas e aplicações, usuários podem ser pessoas, outras 
máquinas ou softwares que solicitem comunicação e/ou conexão.
<br>

A autorização é o processo que ocorre após validação da autenticação. Diz respeito aos 
privilégios, que são concedidos a determinado usuário ao utilizar uma aplicação ou 
sistema. Serve para verificar se determinado usuário terá permissão para acessar, incluir 
ou modificar alguma informação/dado. Também, defini quais ações o usuário pode 
executar, o que é de fundamental importância dentro de uma aplicação ou sistema.
<br>

Quando se entra na ceara de micro-serviços percebe-se mais de uma forma de 
implementar autenticação e autorização, mas, uma comum é conhecida como Json Web 
Token (JWT). O JWT é um padrão da Internet para a criação de dados com assinatura 
opcional e/ou criptografia cujo carga contém o JSON, que armazena um determinado 
número de informações. Este JSON é transmitido no cabeçalho das requisições como 
um token, uma cadeia de caracteres criptografada. Os tokens são assinados usando um 
segredo privado ou uma chave pública/privada.
<br>

### Atividade:
<br>

Dado que a “atualização base” foi concluída, o próximo passo é implementar a 
“atualização segurança”, que consiste em incluir no sistema o processo de autenticação 
e autorização via Json Web Token (JWT).
<br>

A implementação da “atualização segurança” é fundamental, lembre-se que os
investidores são dois grupos empresariais com exigências de nível internacional. Sem 
adição de segurança o possível aporte dos investidores será cancelado.
<br>

Os investidores sugeriram um padrão para os perfis de usuários e suas autorizações. 
Este padrão é o utilizado no sistema atual da maioria das lojas da Toyota. Os perfis e 
suas autorizações são descritos na Tabela 1.
<br>

Tabela 1. Perfis de usuários.
<br>

<br>

