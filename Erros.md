# Resumo dos Testes

| Teste | Erros Encontrados | Causas | Número de execuções
| ------ | ------ | ------| ------|
| criarBoard| 1| Um quadro não pode ser criado com um nome vazio| 2
| getList| 0|  Sem observações | 1
| criarCard| 0| Observação: Um cartão pode ser criado sem um nome | 2
| getCard| 0|  Sem observações | 1
| editarCard| 0|  Sem observações | 1
| moverCard| 0| Sem observações | 2
| apagarCards| 1| Um cartão pode ser replicado, mas o identificador é diferente, logo não há como apagar o mesmo cartão mais de uma vez. | 2
| deletarBoard| 1| Uma vez excluído um quadro não pode ser excluído novamente. | 2