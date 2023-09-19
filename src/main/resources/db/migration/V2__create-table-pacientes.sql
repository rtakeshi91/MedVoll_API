-- CRIANDO TABELA PACIENTES
create table pacientes (
                           id bigint not null auto_increment,
                           nome varchar(100) not null,
                           email varchar(100) not null unique,
                           telefone varchar(20) not null,
                           cpf  varchar(100) not null,

                           primary key (id)
);

-- ADICIONANDO DADOS DE ENDERECO AO PACIENTE
alter table pacientes
    add logradouro varchar(100) not null,
    add bairro varchar(100) not null,
    add cep  varchar(9) not null,
    add complemento varchar(100) not null,
    add numero  varchar(20) not null,
    add uf char(2) not null,
    add cidade varchar(100) not null;