
# ATV I

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

#### Exemplos de Testes



1. Cadastrar um Telefone

Método: POST

URL: http://localhost:8080/telefones/{clienteId}

Body: (JSON)
{
    "ddd": "11",
    "numero": "987654321"
}

<br>


2. Ver um Endereço

Método: GET

URL: http://localhost:8080/enderecos/{clienteId}

<br>


3. Deletar um Documento
Método: DELETE

URL: http://localhost:8080/documentos/{clienteId}

Body: (JSON)
{
    "id": 1
}

## Proposta da Atividade
Contextualização: 
<br>

O ramo de parques aquáticos é um mercado em expansão no mundo e o Brasil também faz parte 
disto. No Brasil estima-se que o setor fatura R$ 3 bilhões por ano, com 30 milhões de visitantes 
e gera 15 mil empregos diretos e 100 mil indiretos, dados de 2020. 
<br>

A Themed Entertainment Association (TEA), associação internacional do segmento, apontou que 
em 2020 o total de visitantes nos grandes parques do mundo ultrapassou, pela primeira vez, 
meio bilhão de visitantes, isto é quase 7% da população mundial. 
<br>

O destaque sobre os parques aquáticos no Brasil é grande, o país é predominantemente quente, 
visitar parques aquáticos é uma das melhores maneiras de enfrentar o calor e, ainda, se divertir 
bastante. Por isso, esse é um destino procurado por muitas famílias e traz muito mais atrações 
que as tradicionais piscinas e toboáguas. 
<br>

Há vários parques famosos no Brasil, um deles é o Beach Park, de Fortaleza, no Ceará. O Beach 
Park talvez seja um dos parques aquáticos mais conhecidos do Brasil. Além disso, é considerado 
o maior da América Latina, são mais de 20.000 metros quadrados de extensão. 
<br>

Outros atrativos do Beack Park são seus resorts, praias particulares, piscinas com correnteza, 
cabanas para relaxar e saunas. Fora da água, o parque oferta diversos restaurantes, que servem 
os mais variados tipos de refeições — desde pizza a comida caseira. 
<br>

Outro parque aquático famoso é o Thermas dos Laranjais, que fica em São Paulo, na cidade de 
Olímpia. Este parque foi considerado o quarto parque aquático mais visitado do mundo, em 
2014.  

<br>

Além das suas atrações, o Thermas dos Laranjais possui uma localização privilegiada, o parque 
fica a cerca de 30 minutos do aeroporto de São José do Rio preto. 
<br>

Dado o conteúdo supra exposto, não é difícil pensar que administrar um parque aquático exige 
muito trabalho e as melhores ferramentas de gestão, as melhores tecnologias. Portanto, a 
expansão deste tipo de negócio instigou a abertura de empresas de tecnologia da informação, 
especializadas na gestão de parques, clubes ou hotéis. 
<br>

## Atividade: 
<br>

Você é um empreendedor, muito atento as tendencias de mercado e percebeu a crescente dos 
parques aquáticos. 
<br>

Existem muitos softwares disponíveis para auxiliar proprietários ou grupos empresariais na 
gestão do seu negócio, mas a maioria é caro demais e com limitações. Portanto você decidiu 
criar uma empresa, com novas ideias e propostas melhores, a Ocean Solutions. 
<br>

Depois de muito pensar, debater e ouvir seus consultores, decidiu-se pelo desenvolvimento de 
um sistema novo, batizado de Atlantis, uma homenagem a cidade de Atlântida. 
<br>

O Atlantis está em sua faze de concepção, comumente chamada de minimum viable product 
(MVP). O MVP é uma estratégia, que visa construir um sistema pequeno e simples por primeiro, 
para testar se a solução terá ou não sucesso antes de partir para uma escala maior. O importante 
neste ponto é que você não é apenas o fundador da Ocean Solutions, também é um dos 
engenheiros de software e desenvolvedor do Atlantis. 
<br>

A primeira proposta do Atlantis é que ele seja um sistema generalista, para gerenciar parques 
aquáticos, clubes e hotéis. Portanto, decidiu-se criar um primeiro módulo para ele, um que sirva 
para cadastrar clientes e seus dependentes. 
<br>

Um cliente é uma pessoa, que irá usufruir dos serviços do parque, clube ou hotel. Contudo, o 
cliente pode trazer consigo convidados, que estarão com ele durante sua estadia. Estes 
convidados são de responsabilidade do cliente e serão cadastrados como dependentes. Como 
todo dependente tem um responsável, os dados de endereço e telefones do dependente devem 
ser iguais aos dados do responsável. 
<br>

Depois de discutir sobre esta questão com sua equipe de desenvolvimento, a opção escolhida 
para implementar este comportamento no sistema foi aplicar o padrão de projeto denominado 
de protótipo. O módulo criado pela equipe de desenvolvimento seguiu o diagrama de classe da 
Figura 1. 
<br>
