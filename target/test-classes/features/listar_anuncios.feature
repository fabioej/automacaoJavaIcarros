# language: pt
Funcionalidade: Listar Veiculos usados a venda
  Como um comprador do site
  Eu quero pesquisar por uma marca e modelo de veiculo
  e vizualizar as opcoes disponiveis
	
	@verificarTamanhoLista
  Esquema do Cenario: Deve mostrar uma lista de pelo menos tres veiculos
    Dado que estou na pagina inicial
    Quando incluo a <marca> e <modelo> do veiculo desejado
    E incluo o ano entre <anoMinimo> e <anoMaximo> desejado
    E insiro os precos <precoMinimo> e <precoMaximo> do veiculo
    E forneco o meu <cep>
    E efetuo a busca
    Entao devo retornar uma lista de no minimo <quantidade> de veiculos

    Exemplos: 
      | marca     | modelo | anoMinimo | anoMaximo | precoMinimo | precoMaximo | cep         | quantidade |
      | "Honda"   | "City" | "2010"    | "2014"    | "30"        | "35"        | "13073-010" |          3 |
      | "Peugeot" | "208"  | "2015"    | "2018"    | "26"        | "35"        | "13073-010" |          3 |

	@verificarDoisPrimeirosItens
  Esquema do Cenario: Deve verificar modelo e valor dos dois primeiros itens da pesquisa
    Dado que estou na pagina inicial
    Quando incluo a <marca> e <modelo> do veiculo desejado
    E incluo o ano entre <anoMinimo> e <anoMaximo> desejado
    E insiro os precos <precoMinimo> e <precoMaximo> do veiculo
    E forneco o meu <cep>
    E efetuo a busca
    Entao o preco do primeiro e segundo veiculos listados deve estar entre <precoMinimo> e <precoMaximo>
    E o modelo dos dois primeiro da lista devem ser <modelo>

    Exemplos: 
      | marca     | modelo | anoMinimo | anoMaximo | precoMinimo | precoMaximo | cep        |
      | "Honda"   | "City" | "2010"    | "2014"    | "30"        | "35"        | "13073-010" |
      | "Peugeot" | "208"  | "2015"    | "2018"    | "26"        | "35"        | "13073-010" |
