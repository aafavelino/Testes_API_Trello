# Resumo dos Testes

| Teste | Erros Encontrados | Causas |
| ------ | ------ | ------|
| criarBoard| 1| Um quadro não pode ser criado com um nome vazio
| getList| 0|  Sem observações
| criarCard| 0| Observação: Um cartão pode ser criado sem um nome
| getCard| 0|  Sem observações
| editarCard| 0|  Sem observações
| moverCard| 0| Sem observações
| apagarCards| 1| Um cartão pode ser replicado, mas o identificador é diferente, logo não há como apagar o mesmo cartão mais de uma vez.
| deletarBoard| 1| Uma vez excluído um quadro não pode ser excluído novamente.