**TUTORIAL PARA CRIAR AS TABELAS:**

> Rodar os comandos em um editor SQL (sugestão: [DBeaver](https://dbeaver.io/download/)) após configurar o banco presente no passo-a-passo do [README.md](./README.md):
- Rode um comando por vez!
- Exemplo: Primeiro rode o create table, depois o comando create unique index, em seguida alter table… E assim por diante. 

**COMANDOS:**

```
create table cliente
(
nome varchar(225) null, 
sexo varchar(10) null, 
dataDeNascimento varchar(10) null, 
cpf varchar(11) null, 
telefone varchar(11) null, 
cep varchar(9) null, 
numero varchar(5) null, 
rua varchar(225) null, 
bairro varchar(100) null, 
cidade varchar(100) null, 
nomeEmergencia varchar(225) null, 
telefoneEmergencia varchar(11) null,
matriculado bool null
);
```

```
create unique index cliente_cpf_uindex  on cliente (cpf);
```

```
alter table cliente add constraint cliente_pk primary key (cpf);
```

```
create table funcionario
(
nome varchar(225) null, 
sexo varchar(10) null, 
dataDeNascimento varchar(10) null, 
cpf varchar(11) null, 
telefone varchar(11) null, 
cep varchar(9) null, 
numero varchar(5) null, 
rua varchar(225) null, 
bairro varchar(100) null, 
cidade varchar(100) null, 
senha varchar(100) null, 
gerente bool null
);
```

```
create unique index funcionario_cpf_uindex  on funcionario (cpf);
```

```
alter table funcionario add constraint funcionario_pk  primary key (cpf);
```

```
create table matricula
(
cpfCliente varchar(11) null, 
constraint matricula_cliente_cpf_fk 
	foreign key (cpfCliente) references cliente (cpf) 
		on delete set null 
);
```

```
create table modalidade
(
nome varchar(100) null, 
codigoModalidade int null, 
preco double null
);
```

```
create unique index modalidade_codigoModalidade_uindex on modalidade (codigoModalidade);
```

```
alter table modalidade add constraint modalidade_pk primary key (codigoModalidade);
```

```
create table mensalidade
(
codCliente varchar(11) null,
data varchar(10) null,
valor double null,
pago bool null,
constraint mensalidade_matricula_cpfCliente_fk
 foreign key (codCliente) references matricula (cpfCliente)
  on delete set null
);
```

```
create table matricula_modalidade
(
	codMatricula varchar(11) null,
	codModalidade int null,
	constraint matricula_modalidade_matricula_cpfCliente_fk
		foreign key (codMatricula) references matricula (cpfCliente)
			on delete set null,
	constraint matricula_modalidade_modalidade_codigoModalidade_fk
		foreign key (codModalidade) references modalidade (codigoModalidade)
			on delete set null
);
```

```
create table avaliacao
(
codCliente varchar(11) null, 
numeroDaAvaliacao int null, 
circunferenciaAbdominal double null, 
torax double null, 
cintura double null, 
quadril double null, 
antebracoDireito double null, 
antebracoEsquerdo double null, 
bracoDireito double null, 
bracoEsquerdo double null, 
coxaDireita double null, 
coxaEsquerda double null, 
panturrilhaDireita double null, 
panturrilhaEsquerda double null, 
data varchar(10) null, 
constraint avaliacao_cliente_cpf_fk
   		foreign key (codCliente) references cliente (cpf)
on delete set null
);
```