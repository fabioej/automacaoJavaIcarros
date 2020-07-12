# automacaoJavaIcarros
Repositorio com automacao do site icarros usando selenium java e tambem cucumber


Utilizando o site iCarros (https://www.icarros.com.br/principal/index.jsp)

  - Foi Criada uma consulta que retorne uma lista com pelo menos 3 carros usados da mesma marca e modelo.
  - O teste valida o modelo e o valor à vista do primeiro e do segundo carro da lista produzida pela consulta
         Valor deve estar entre o max e o min fornecidos
	       Modelo deve estar contido no titulo de cada anuncio
  - Foi criado um script que deve ler a lista de resultados e criar um arquivo de dados contendo marca modelo, ano, km, cor, 
    câmbio e valor à vista de cada veiculo retornado (apenas da primeira página de retorno)
  -  Outro script deve ler a lista de carros criada na tabela anterior, buscar pelos veiculos e validar se os dados, 
     especialmente o valor à vista continuam os mesmos


# Teste Cucumber que foi feito

    - Criada uma consulta que retorne uma lista com pelo menos 3 carros usados da mesma marca modelo
    - Valida o modelo e o valor à vista do primeiro e do segundo carro da lista produzida pela consulta

