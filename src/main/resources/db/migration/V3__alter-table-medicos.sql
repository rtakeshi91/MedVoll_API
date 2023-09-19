-- adicionando coluna TELEFONE
alter table medicos
    drop column ativo;

alter table medicos
    add ativo tinyint;

update medicos set ativo = 1;

alter table medicos
    modify ativo tinyint NOT NULL;

