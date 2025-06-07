
# 🧳 Sistema de Agência de Viagens

Aplicação web desenvolvida para facilitar a compra de pacotes de viagem com transporte, hospedagem e serviços adicionais. O sistema permite o gerenciamento de usuários e compras, considerando lógica de negócio com padrões de projeto para manter o código simples, coeso e flexível.

## ✅ Funcionalidades Implementadas

| Requisito                                         | Status |
|--------------------------------------------------|--------|
| Cadastro de usuários e dados pessoais            | ✅     |
| Criação de compra com transporte e extras        | ✅     |
| Cálculo automático de preço com base na temporada| ✅     |
| Cancelamento de compra com regras especiais      | ✅     |
| Inclusão de transporte de ida e volta            | ✅     |
| Serviços adicionais: guia turístico e seguro viagem | ✅     |

## 🛠️ Tecnologias Utilizadas

**Backend:**

- Java 23
- Spring Boot
- Spring Data JPA
- Banco de Dados: MySQL
- Thymeleaf
- Lombok

## 📐 Padrões de Projeto Utilizados

### 🔧 Builder — construção flexível de objetos Compra

Utilizado para criar compras com diversos parâmetros opcionais de forma controlada e legível:

```java
Compra compra = new CompraBuilder()
    .cliente(cliente)
    .transporteIda(transporteIda)
    .transporteVolta(transporteVolta)
    .dataIda(dataIda)
    .dataVolta(dataVolta)
    .hospedagem(hospedagem)
    .comGuia()
    .comSeguro()
    .build();
```

### 🧱 Decorator — serviços adicionais de forma modular

Permite adicionar funcionalidades extras à compra sem alterar sua estrutura original, como seguro viagem e guia turístico:

```java
Compra compraBase = ...;
compraBase = new CompraComGuia(compraBase);
compraBase = new CompraComSeguro(compraBase);
```

Esses decorators modificam tanto o valor quanto a descrição final da compra.

### 🧠 Strategy — cálculo de preço conforme a temporada

Define regras diferentes de precificação com base no mês da viagem (alta/baixa temporada ou regular):

```java
CalculoPrecoStrategy strategy = CalculoPrecoStrategyFactory.criarEstrategia(dataIda);
BigDecimal precoFinal = strategy.calcularPreco(precoBase);
```

As estratégias alteram o valor final da compra em até ±30% de acordo com o mês.

### 🕹️ Command — ações como confirmar e cancelar compra

Permite executar ações encapsuladas com regras específicas. Por exemplo, compras com seguro ativado só podem ser canceladas com permissão extra:

```java
CompraCommand comando = new CancelarCompraCommand(compra);
comando.executar();
```

## 📌 Trecho de Código com Integração dos Padrões

Abaixo, um resumo funcional onde os padrões são usados em conjunto na criação de uma compra:

```java
public Compra criarCompra(...dados...) {
    Compra compra = new Compra();
    compra.setCliente(cliente);
    compra.setTransporteIda(transporteIda);
    compra.setTransporteVolta(transporteVolta);
    compra.setDataIda(dataIda);
    compra.setDataVolta(dataVolta);
    compra.setHospedagem(hospedagem);
    compra.setStatus(Compra.StatusCompra.AGUARDANDO_PAGAMENTO);

    // Strategy: define o valor base conforme a data
    BigDecimal precoBase = new BigDecimal("500.00");
    CalculoPrecoStrategy strategy = CalculoPrecoStrategyFactory.criarEstrategia(dataIda);
    BigDecimal preco = strategy.calcularPreco(precoBase);

    // Decorator (manual): aplica guia e seguro
    if (possuiGuia) {
        compra.setNumeroGuia("GUIA-" + UUID.randomUUID().toString().substring(0, 8));
        preco = preco.add(new BigDecimal("150.00"));
    }

    if (possuiSeguro) {
        compra.setSeguroAtivo(true);
        preco = preco.add(new BigDecimal("200.00"));
    }

    compra.setPreco(preco);
    // Descrição final montada com os dados e extras
    ...
    return compra;
}
```

## 💻 Execução do Projeto

**Requisitos:**

- JDK 23
- Maven
- MySQL (ou outro compatível)

**Passos:**

1. Configure o banco de dados no `application.properties`
2. Execute a aplicação pela classe `AgenciaViagensApplication`
3. Acesse via navegador (`localhost:8080`) e use a interface com Thymeleaf

## 🎯 Conclusão

Este sistema demonstrou na prática como aplicar padrões de projeto (GoF) no desenvolvimento de aplicações reais com Java e Spring Boot, resultando em um código modular, limpo e de fácil manutenção.
