# Instruções para Compilação e Execução

## Pré-requisitos
Antes de iniciar a compilação e execução, certifique-se de que os seguintes pré-requisitos estão instalados em sua máquina:

### 1. **Java**:
- Instale o JDK 8 ou superior.
- Verifique a instalação com o comando:
- IDE recomendada: NetBeans
 java -version

### 2. **MongoDB:**
Instale o MongoDB compass.
Verifique a instalação com o comando:
mongo --version


## Estrutura do Projeto
- src
  - main
    - java
      - [pacote da aplicação]
        - Mavenproject2.java
        - [outras classes, modelos, controladores, etc.]

## Conexão do Banco de Dados
1. Certifique-se de que o banco de dados está em funcionamento e que as tabelas estão criadas conforme especificado.
2. Configure a conexão com o banco de dados no código, verificando se as credenciais e a URL estão corretas.
   - Abra o Arquivo `Conexao.java`
   - Na linha 12, depois de `localhost:5432/` Substituia o nome pelo nome da sua base de dados (ex: gerenciamento).
   - Na linha 16, altere o nome do seu banco de dados do MongoDB
     
## Passos para Compilação
1. Abra sua IDE e carregue o projeto.
2. Verifique se todas as dependências estão configuradas corretamente.
3. Compile o projeto.

## Passos para Execução
1. Após a compilação localize a classe `Mavenproject2` que contém o método `main`.
2. Clique com o botão direito na classe `Mavenproject2` e escolha "Executar" ou "Run".
3. Siga as instruções apresentadas no console para interagir com a aplicação.

## Exemplos de Uso
1. Cadastrar uma editora.
2. Listar todas as editoras cadastrados.
3. Atualizar as informações de uma editora existente.
4. Remover uma editora da base de dados.
