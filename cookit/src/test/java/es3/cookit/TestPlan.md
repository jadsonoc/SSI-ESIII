# Plano de Teste para o Projeto CookIt

## Levantamento de possíveis cenários de Teste
* Acessar o endpoint Food via método POST
* Passar uma string com espaço como parâmetro no endpoint de busca de Ingredientes
* Passar uma string vazia no endpoint de busca de ingredientes ...


## Teste 1
Testar o endpoint Food para verificar se a consulta retorna um response positivo (código 200).

## Teste 2
Utilizando o Spy para verificar se o conteúdo retornado pelo endpoint Foods (array de Ingredientes) está de acordo.


## Teste 3
Testando o retorno do endpoint de pesquisa de um ingrediente.
### Dado 
o endpoint de pesquisa de ingrediente
### Quando 
passada uma string via parâmetro GET ("tom")
### Então 
o retorno deverá ser "Tomate" 

## Teste 4
Mesmo que o teste 3; entretanto, utilizando o InjectSpy FoodService.

