## Narrativa:

O usuário deve solicitar um token e uma chave para a API do Trello. Os testes realizados estão relacionados a criação de quadro, cartões e limpeza dos dados sujos durante os testes. 

---

## Critérios de aceitação:

### **Cenário 1:** Criar Quadro
>**Dado** o usuário possui chave e token da API do Trello
**Quando** houver a necessidade de criar novos cards
**E** não existir nenhum quadro com nome vazio
**E** fizer a chamada via POST
**Então** o usuário poderá criar um novo quadro para testes.


### **Cenário 2:** Criar Cartão
>**Dado** o usuário possui chave e token da API do Trello
**E** já existe um quadro com identificador disponível
**Quando** fizer a chamada via GET do Identificador do quadro
**E** atribuir o valor da chamada como parâmetro
**E** fizer a chamada via GET do Identificador de uma das listas presentes no quadro
**Então** o cartão poderá ser criado  



### **Cenário 3:** Mover Cartão
>**Dado** o usuário possui cartões criados em listas do quadro
**E** houver mais de uma lista disponível
**Quando** executar uma atualização via PUT
**E** pegar o identificador da lista para a qual o cartão será movido
**Então** a atualização da lista do cartão é alterada 


### **Cenário 1:** Criar Quadro
>**Dado** o usuário possui chave e token da API do Trello
**E** houver saldo disponível no banco de vagas
**Quando** existir demanda para novas vagas
**E** não ultrapassar o limite do saldo
**Então** o gestor de banco 