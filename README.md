# Conversor de Moedas Java

Este é um conversor de moedas simples que permite converter valores entre diferentes moedas, utilizando a cotação do dólar como base para as conversões. O usuário pode escolher a moeda base (como BRL, USD, EUR, etc.) e a moeda de destino, informando o valor a ser convertido.

## Funcionalidades

- Escolha a moeda base (como BRL, USD, EUR, etc.).
- Escolha a moeda para a qual deseja converter.
- Realiza a conversão com base nas taxas de câmbio obtidas de uma API externa.
- Exibe o valor convertido com duas casas decimais.

## Pacotes Utilizados

- **Gson**: Utilizado para parsear as respostas da API em formato JSON. O Gson é uma biblioteca do Google que facilita a conversão entre objetos Java e JSON, tornando mais simples manipular os dados obtidos da API.

## Como Rodar

### Pré-requisitos

Antes de rodar a aplicação, você precisa ter o seguinte instalado:

- **Java 8 ou superior**: Certifique-se de que o Java está instalado e configurado corretamente no seu sistema.
- **Dependências**: A biblioteca Gson precisa ser adicionada ao seu projeto. Se estiver utilizando o Maven ou Gradle, adicione a dependência conforme abaixo:

#### Maven
Adicione a dependência do Gson no seu arquivo `pom.xml`:

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.8</version>
</dependency>
