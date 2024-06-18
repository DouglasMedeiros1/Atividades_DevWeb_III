
# ATV III

## Como rodar?

### Como abrir o Projeto:

Antes dde tudo verifique se possui o eclipse e o insominia instalados em  sua maquina. (Sem eles não será possivel executar o projeto)

Inicie abrindo o Eclipse, e no canto superior esquerdo clique em "files" e em seguida em "Open Porjects from Files Systems"

Navegue até onde o repositorio foi clonado e digite finish

### Como executar:

Acesse o arquivo AutomanagerApplication.java, que se encontra nas pastas automanager/ src/ main/ java/ com/ autobots/ automanager

Clique no botão verde com um simbolo de "play" e defina: Run As Java Application

### Como testar:

Abra o Insominia e crie uma nova coleção (O nome não importa)

Em seguida crie um http request, defina o metodo que deseja utilizar e defina a rota que deseja acessar (Todas devem começar com "http://localhost:8080/")



## Proposta da Atividade
Contextualização: 
<br>

Os níveis de maturidade, para os micro-serviços, foram implementados e a aplicação
automanager está cada vez melhor – isto animou a equipe de marketing da empresa,
que partiu em busca de investidores e grupos empresariais para, finalmente, lançar o
produto no mercado.
<br>

Após duas semanas de apresentações, e apesar da animação, o retorno obtido pela
equipe de marketing não foi positivo. Todos os investidores solicitaram mudanças na
aplicação que não atende, ainda, as necessidades básicas de uma loja de manutenção
veicular e venda de autopeças.
<br>

Foram várias solicitações de atualizações no software, mas nem todas serão atendidas.
Inteligentemente, a equipe de marketing selecionou alguns parceiros específicos,
investidores promissores que deram sugestões importantíssimas e irão ajudar muito no
lançamento do software. Os novos parceiros da autobots são: Toyota Motor
Corporation e o grupo Volkswagen.
<br>

Toyota Motor Corporation é um fabricante automotivo japonês com sede na Toyota,
província de Aichi, no Japão. O grupo Volkswagen é o maior fabricante de automóveis
do mundo e tem a sua sede na cidade de Wolfsburg, na Baixa Saxônia. Os novos
parceiros pretendem instalar o automanager nas suas redes de manutenção veicular e
lojas de autopeças no Brasil. Isto é uma oportunidade imperdível.
<br>

### Atividade: 
<br>

Após várias reuniões com os investidores e com sua equipe de desenvolvimento
definiu-se o que é preciso adicionar no sistema para que o primeiro MVP seja lançado.
<br>

As atualizações foram divididas em duas partes, denominadas de “atualização de base”
e “atualização de segurança”. Por decisão unânime da equipe de desenvolvimento,
primeiro será feito a “atualização de base” e você será o responsável pelo seu
desenvolvimento, enquanto o restante da equipe avalia os melhores fornecedores de
serviços cloud, para implantação do sistema no Brasil.
<br>

A “atualização de base” consiste em adicionar mais capacidades no sistema, que
atualmente apenas registra clientes e informações como número de documentos,
telefone e endereço. Agora o sistema deverá ser preparado para armazenar
informações como usuários, veículos, serviços, peças, vendas e o que mais for
minimamente necessário para a operação de uma loja de manutenção veicular.
<br>

Após conversas com a equipe de desenvolvimento, chegou-se a uma estrutura, um
diagrama de classes, onde apresenta-se os relacionamentos entre as classes e
evidencia-se as novas capacidades do sistema. Este diagrama é apresentado na Figura
1.
<br>

Figura 1. Novo diagrama de classes do sistema.
<br>

A equipe de desenvolvimento preparou um projeto com os códigos das classes
apresentadas na Figura 1. O objetivo é implementar estas classes no sistema, bem
como todo o CRUD para as entidades e HATEOAS.
<br>

Você tem liberdade para modificar os códigos, que foram sugeridos pela equipe de
desenvolvimento, afinal, você é o fundador da empresa e o maior idealizador do
produto. Contudo, as funcionalidades não devem ser negligenciadas, porque foram
solicitações dos investidores, as novas funcionalidades são:
<br>

• Cadastro de empresa, ou seja, a unidade comercial que oferece o serviço de
manutenção e venda de mercadorias.
<br>

• Inclusão associação de usuários a uma determinada empresa.
<br>

• Definição dos tipos de usuário no sistema, que pode ser cliente, fornecedor ou
funcionário.
<br>

• Cadastro de veículos, mercadorias e serviços, para gerenciamento de vendas e
serviços prestados a um usuário e seu veículo.
<br>

• Inclusão de credencial para acesso de um usuário ao sistema.
<br>
<br>
