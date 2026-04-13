# ConsumersAndProducers

Implementação do padrão **Producer-Consumer** em Java, simulando a arquitetura conceitual de um sistema de mensageria com um Broker central.

## Visão Geral

O projeto demonstra como múltiplos produtores e consumidores podem operar de forma concorrente e desacoplada, comunicando-se exclusivamente através de uma fila gerenciada por um Broker — o mesmo princípio usado em sistemas como Kafka e RabbitMQ.

```
[Producer 1] ─┐
[Producer 2] ──→ [Broker / BlockingQueue] ──→ [Consumer]
[Producer 3] ─┘
```

## Estrutura

```
src/main/
├── Behavior.java   # Interface funcional que define a estratégia de produção
├── Producer.java   # Produz mensagens e as coloca na fila
├── Consumer.java   # Consome mensagens da fila
├── Broker.java     # Gerencia produtores, consumidores e a fila compartilhada
└── Main.java       # Ponto de entrada — configura e inicia o sistema
```

## Como funciona

| Componente | Responsabilidade |
|---|---|
| `Broker` | Registra produtores/consumidores, fornece a fila compartilhada e inicia as threads |
| `Producer` | Executa um `Behavior` para gerar um valor e o publica na fila via `put()` |
| `Consumer` | Retira mensagens da fila via `take()` e as processa |
| `BlockingQueue` | Garante thread-safety e backpressure (bloqueia quando a fila está cheia ou vazia) |
| `Behavior` | Interface funcional que permite customizar o que cada Producer produz |

## Como executar

Compile e execute a partir da raiz do projeto:

```bash
javac -d out src/main/*.java
java -cp out main.Main
```

### Saída esperada

```
[Producer] produced: 1 | queue size: 1
[Producer] produced: 3 | queue size: 2
[Consumer] consumed: 1 | queue size: 1
[Producer] produced: 2 | queue size: 2
[Broker] queue size: 2 | contents: [3, 2]
...
```

## Customizando

Para adicionar um novo producer com comportamento diferente, basta passar um `Behavior` via lambda:

```java
broker.registerProducer(new Producer(() -> new Random().nextInt(100)));
```

Para adicionar mais consumers:

```java
broker.registerConsumer(new Consumer());
broker.registerConsumer(new Consumer());
```

## Conceitos demonstrados

- **Padrão Producer-Consumer**
- **Concorrência com threads Java**
- **`BlockingQueue` para comunicação thread-safe**
- **Strategy pattern** via interface funcional `Behavior`
- **Desacoplamento** entre produtores e consumidores
