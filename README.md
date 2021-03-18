# Testes na API do Trello 

## Tecnologias Utilizadas
- Java
- Junit
- Rest Assured
- Jupiter Engine

## Informações gerais

O código foi produzido em java utilizando Rest assured, para sua execução faz-se necessária a utilização do Maven.

Todas as dependências estão salvas no arquivo "pom.xml", portando não será preciso instalar nenhuma outra.

## Informações específicas

A ordem de execução dos testes importa, por este motivo a execução foi ordenada pelo nome, e por decisão de projeto os métodos iniciam-se por letras seguidas de underline.

Alguns casos de testes foram parametrizados, caso deseje alterar algum basta usar a anotação acima do método. 

Os testes resumiram-se aos fluxos básicos e de exceções relacionados aos cards do Trello.   

## Arquivos de código fonte

O teste possui dois arquivos, um para guardar informações sobre os parâmetros e outro para a realização dos testes.

|  Arquivo                       | 		Descrição			  |	
|-------------------------------|-------------------------|
|TrelloTest.java|Possui todos os cenários de testes solicitados nos casos de uso.|
|Trello.java|Possui informações e variáveis de acesso à plataforma da API do Trello, caso deseje alterar algum dos parâmetros, basta atualizar as variáveis presentes nesse arquivo.|

## Importação 

A importação pode ser feita através do clone em qualquer IDE.

## Testes

| Arquivos | Descrição |
| ------ | ------ |
| Cenários | https://github.com/aafavelino/Testes_API_Trello/blob/master/Cenarios.md|
| Erros | https://github.com/aafavelino/Testes_API_Trello/blob/master/Erros.md|


## Autor 

|  Nome                       | 		Email			  |			 			Contato				  |
|-------------------------------|-------------------------|-------------------------------------------|
| Adelino Afonso Fernandes Avelino			    | adelino18fernandes@gmail.com   | (84) 98634-8534		  |
