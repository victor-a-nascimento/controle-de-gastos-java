# Controle de Gastos

Aplicação de desktop em Java (Swing) para registrar receitas e despesas, consultar o saldo e ver um extrato com o saldo acumulado. Os dados são salvos em arquivos CSV e carregados automaticamente na próxima vez que o programa abre. Projeto acadêmico da graduação em Ciência da Computação, usado para praticar Programação Orientada a Objetos.

## Funcionalidades

- Cadastro de **receitas** (salário, décimo terceiro, férias, outras receitas)
- Cadastro de **despesas** (alimentação, transporte, residência, saúde, educação, entretenimento, outras despesas)
- **Saldo atual**: considera só os lançamentos até a data de hoje
- **Saldo total**: considera todos os lançamentos, inclusive os com data futura
- Listagem de receitas e de despesas
- **Extrato** com todos os lançamentos em ordem de data e o saldo acumulado após cada um
- Salvamento automático em `receitas.csv` e `despesas.csv`, com carregamento na abertura

## Como usar

Na janela principal, os botões à esquerda abrem cada função e o resultado aparece na área de texto à direita. Ao adicionar um lançamento, o programa pede, em ordem: a categoria, o valor (use **ponto** como separador decimal, por exemplo `1500.50`) e a data no formato `dd/MM/yyyy`.

## Estrutura do projeto

```
controledegastos/
├── App.java                ponto de entrada (abre a tela principal)
├── TelaPrincipal.java      interface gráfica (Swing)
├── ControleFinanceiro.java regras do sistema: saldo, ordenação e extrato
├── Lancamento.java         classe abstrata com valor e data
├── Receita.java            lançamento com categoria de receita
├── Despesa.java            lançamento com categoria de despesa
├── CategoriaReceita.java   enum das categorias de receita
├── CategoriaDespesa.java   enum das categorias de despesa
├── ItemExtrato.java        uma linha do extrato (data, descrição, valor, saldo)
└── PersistenciaCSV.java    leitura e gravação dos arquivos CSV
```

O código está dividido em três camadas: interface (`TelaPrincipal`), regras de negócio (`ControleFinanceiro` e as classes de lançamento) e persistência (`PersistenciaCSV`).

## Conceitos de POO aplicados

- **Herança e classe abstrata:** `Receita` e `Despesa` herdam de `Lancamento`, que concentra o que elas têm em comum (valor e data)
- **Enumerações (`enum`)** para as categorias, que evitam valores digitados errado
- **Encapsulamento:** atributos privados com acesso por métodos `get`
- **Separação de responsabilidades** entre interface, regras e persistência
- **Tratamento de exceções** nas entradas do usuário e na leitura e gravação de arquivos

Também usa `java.time.LocalDate` para datas e `streams` para somar os valores.

## Como executar

Requisito: JDK 8 ou superior.

**Pelo Eclipse:** importe o projeto e execute a classe `App` (*Run As → Java Application*).

**Pelo terminal**, na pasta que contém a pasta `controledegastos`:

```
javac controledegastos/*.java
java controledegastos.App
```

O repositório já inclui `receitas.csv` e `despesas.csv` com **dados fictícios**, só para testar o programa. Para começar do zero, apague os dois arquivos antes de executar. Eles são lidos e gravados na pasta de onde o programa é executado, e cada linha tem o formato `valor;data;categoria`, por exemplo:

```
1500.5;2026-03-10;SALARIO
```

## Limitações conhecidas

- O valor deve ser digitado com ponto decimal. Com vírgula, o programa mostra uma mensagem de erro.
- Não há edição nem exclusão de lançamentos depois de cadastrados.
- O programa aceita valores negativos ou zero, e não valida isso.
- Os valores são `double`. Para dinheiro, `BigDecimal` é mais indicado, para evitar erros de arredondamento.
- Se um arquivo CSV tiver uma linha em formato inválido, o programa não consegue abrir.
- No extrato, a descrição de cada linha mostra só o tipo do lançamento (Receita ou Despesa), sem a categoria.

## O que aprendi

- Modelar um problema real com classes, herança e enumerações
- Dividir um programa em camadas, deixando as regras separadas da interface
- Salvar e carregar dados em arquivo CSV
- Montar uma interface gráfica simples com Swing
- Documentar o código com Javadoc

## Autor

Victor Nascimento · [LinkedIn](https://www.linkedin.com/in/victor-a-nascimento) · [GitHub](https://github.com/victor-a-nascimento)
