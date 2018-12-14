# **Projeto de Banco de Dados - Academia**

### Para funcionar você deve fazer o seguinte:

1 - Instalar o MySQL:

```sh
$ sudo apt update
$ sudo apt install mysql-server
```

2 - Configurar o MySQL:

```sh
$ sudo mysql_secure_installation
```

3 - Entrar com sua senha do terminal.

4 - Nas perguntas responda com:

`(n = não // y = sim)`
`(no primeiro "n" será criado uma senha, coloque 'academia')`


```sh
$ n
$ n
$ n
$ n
$ y
```

5 - Agora, para criar um usuário MySQL para a academia, logue novamente como root:

```sh
$ sudo mysql -u root -p
$ GRANT ALL PRIVILEGES ON *.* TO academia@localhost IDENTIFIED BY 'academia' WITH GRANT OPTION;
```

6 - Saia do bash do MySQL (ctrl+d) e logue novamente com o usuário (e senha) criado, inserindo o seguinte para criar e usar a tabela:

```
$ mysql -u academia -p
$ CREATE DATABASE academia;
$ USE academia;
```

7 - Ótimo, usuário criado. Agora só linkar no projeto baixado!

8 - Com o projeto aberto no IntelliJ, aperte: ``ctrl+alt+shift+s``.

9 - Vá em ``Libraries``, no lado esquerdo da janela que abriu.

10 - Clique no ``+`` para adicionar uma biblioteca, e selecione a opção ``Java``

11 - Na pasta raiz do projeto baixado tem um arquivo chamado ``connection-java-client-2.3.0.jar``, selecione-o, aperte ``OK``, aplique com o botão ``Apply`` e em seguida ``OK`` novamente.

12 - Pronto, tudo certo para começar a implementar o Banco de Dados.

*13* - Para criar as tabelas do banco com o objetivo de fazer a aplicação funcionar localmente, use um gerenciador SQL (exemplo: [DBeaver](https://dbeaver.io/download/)) e siga o tutorial para criar criar as tabelas aqui: [TUTORIAL](./SQL_das_tabelas.pdf)

> Observação: Projeto criado usando o [Java 8 (java version "1.8.0_191")](./Instalar_Java_8.pdf).  
> Projeto reutilizado da disciplina de [POO](https://github.com/lohhans/projetoPOO), aplicando os conceitos de Banco de Dados.