# Plano de Teste para o Projeto CookIt

## Levantamento de possíveis cenários de Teste
* Acessar o endpoint Food via método POST
* Passar uma string com espaço como parâmetro no endpoint de busca de Ingredientes
* Passar uma string vazia no endpoint de busca de ingredientes ...


## Teste Unitário 1
Testar o endpoint Food para verificar se a consulta retorna um response positivo (código 200).

## Teste Unitário 2
Utilizando o Spy para verificar se o conteúdo retornado pelo endpoint Foods (array de Ingredientes) está de acordo.


## Teste Unitário 3
Testando o retorno do endpoint de pesquisa de um ingrediente.
  
**Dado** o endpoint de pesquisa de ingrediente
  
**Quando**
enviada uma string via parâmetro GET ("tom")
  
**Então** o retorno deverá ser "Tomate"

## Teste Unitário 4
Mesmo que o teste 3; entretanto, utilizando o InjectSpy FoodService.

# Testes com Cypress

## Teste de Sistema 1
* cookit_filter_food
1) Abre a página Home
2) Clica no menu "All Ingredients"
3) Digita uma sequência de letras "ceb" na barra de pesquisa
4) Verifica se o valor retornado na tabela de ingredientes é "Cebola"

* cookit_search_recipe
1) Abre a página Home
2) Insere dois ingredientes na barra de pesquisa princial: "Tomate" e "Cebola"
3) Clica no botão Go
4) Verifica se o retorno da pesquisa contém "Receita 1"


