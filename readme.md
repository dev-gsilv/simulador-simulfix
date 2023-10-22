# SIMULFIX
Simulfix é uma API RESTfull em Java e Spring Boot 3. 
Com ela você pode simular diferentes tipos de 
investimentos em renda fixa.

<br>

###### Esta API foi criada como parte do desafio final do Bootcamp Santander Java 2023, promovido pela DIO.

---

Tipos de investimento suportados:

| Isento de Imposto de Renda | Imposto de Renda Regressivo |
| ------ | ------ |
| LCI, LCA, CRI, CRA, Poupança | CDB, Tesouro |

| Indexadores | Exemplos       | Sintaxe JSON |
| ----------- | -------------- |--------------|
| Pré-Fixado  | 9% a.a.        | pre          |
| Pós-Fixado  | 103% da CDI    | pos          |
| Misto       | IPCA + 5% a.a. | misto        |

<br>

Para criar uma simulação, você deve informar:

-   Nome para a simulação.
-   Uma descrição.
-   Tipo de investimento.
-   Indexador.
-   Valor do investimento.
-   Prazo, em meses.
-   Rentabilidade anual, em porcentagem.
    <br>

---

### Documentação

Para testar como criar uma simulação, explore a [documentação Swagger](http://localhost:8080/swagger-ui/index.html) depois de clonar o projeto.