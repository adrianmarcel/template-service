# Template Service

## Visão Geral
Template Service é uma aplicação Java projetada usando os princípios do Clean Architecture. Ele fornece funcionalidades para criar, atualizar, buscar e deletar templates. Este serviço é estruturado em várias camadas para garantir a separação de responsabilidades e facilitar a manutenção e a escalabilidade.

## Estrutura do Projeto
O projeto está organizado da seguinte forma:

- **core**: Contém as configurações de aplicação e infraestrutura.
    - `config`: Configurações de cache, scanner de componentes e OpenAPI.
    - `database`: Configurações de entidades e JPA.

- **domain**: Contém as regras de negócio e lógica de exceções.
    - `shared`: Erros e exceções compartilhadas.
    - `template`: Modelos e interfaces de gateway.

- **usecase**: Contém os casos de uso da aplicação.
    - `model`: Modelos utilizados nos casos de uso.
    - Implementações dos casos de uso para criar, deletar, buscar e atualizar templates.

- **entrypoint**: Pontos de entrada para a aplicação.
    - `job`: Jobs para gerar templates.
    - `queue`: Manipulação de filas.
    - `rest`: Controlador REST para expor endpoints.

- **gateway**: Implementações de gateways para acessar o banco de dados.
    - `database`: Modelos, repositórios e especificações de templates.

- **test**: Testes unitários organizados por camadas.

- **testIntegration**: Testes de integração organizados por camadas.

- **testLoad**: Testes de carga para verificar a performance do serviço.

## Funcionalidades
1. **Criação de Template**
    - Endpoint REST para criar novos templates.
    - Validações de entrada e persistência no banco de dados.

2. **Atualização de Template**
    - Endpoint REST para atualizar templates existentes.
    - Validações de entrada e atualização no banco de dados.

3. **Busca de Templates**
    - Endpoint REST para buscar templates com filtros.
    - Implementação de especificações para consultas flexíveis.

4. **Deleção de Templates**
    - Endpoint REST para deletar templates existentes.
    - Validações de entrada e remoção no banco de dados.

5. **Gerenciamento de Jobs**
    - Job para gerar templates periodicamente.

6. **Integração com Filas**
    - Manipulação de mensagens em filas para processamento assíncrono.

## Configurações
As configurações da aplicação são gerenciadas pelos arquivos:
- `application.properties`
- `application-dev.properties`
- `application-integration.properties`

## Testes
- **Unitários**: Testes de cada componente individualmente.
- **Integração**: Testes que cobrem a interação entre múltiplos componentes.
- **Carga**: Testes para avaliar a performance sob alta demanda.

### Executando Testes de Carga
Os testes de carga podem ser executados usando o script `load-test-template.sh` localizado em `testLoad/scripts`.

```sh
./testLoad/scripts/load-test-template.sh
