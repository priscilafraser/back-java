create table categoria (id bigint generated by default as identity, categoria varchar(255), created_at TIMESTAMP WITHOUT TIME ZONE, update_at TIMESTAMP WITHOUT TIME ZONE, primary key (id));
create table cliente (id bigint generated by default as identity, bairro varchar(255), cep varchar(8) not null, cidade varchar(255), complemento varchar(255), cpf varchar(11) not null, created_at TIMESTAMP WITHOUT TIME ZONE, email varchar(255) not null, estado varchar(255), logradouro varchar(255), nome varchar(255), numero integer, perfil varchar(255), senha varchar(255), telefone varchar(255), update_at TIMESTAMP WITHOUT TIME ZONE, primary key (id));
create table fornecedor (id bigint generated by default as identity, bairro varchar(255), cep varchar(9), cidade varchar(255), cnpj varchar(255), complemento varchar(255), created_at TIMESTAMP WITHOUT TIME ZONE, email varchar(50) not null, estado varchar(255), logradouro varchar(255), numero integer not null, perfil varchar(255), razaosocial varchar(50) not null, senha varchar(255), telefone varchar(255), update_at TIMESTAMP WITHOUT TIME ZONE, primary key (id));
create table ligacao_fornecedor_produto (id bigint generated by default as identity, created_at TIMESTAMP WITHOUT TIME ZONE, descricao varchar(255), imagem varchar(255), preco float not null, update_at TIMESTAMP WITHOUT TIME ZONE, idcategoria bigint, idfornecedor bigint, idtipoproduto bigint, primary key (id));
create table ligacao_fornecedor_servico (id bigint generated by default as identity, created_at TIMESTAMP WITHOUT TIME ZONE, descricao varchar(255), horafinal varchar(255), horainicial varchar(255), preco float not null, update_at TIMESTAMP WITHOUT TIME ZONE, idcategoria bigint, idfornecedor bigint, idtiposervico bigint, primary key (id));
create table produto (id bigint generated by default as identity, created_at TIMESTAMP WITHOUT TIME ZONE, produtos varchar(255), update_at TIMESTAMP WITHOUT TIME ZONE, idcategoria bigint, idtipoproduto bigint, primary key (id));
create table servico (id bigint generated by default as identity, created_at TIMESTAMP WITHOUT TIME ZONE, servicos varchar(255), update_at TIMESTAMP WITHOUT TIME ZONE, idcategoria bigint, idtiposervico bigint, primary key (id));
create table tipo_produto (id bigint generated by default as identity, created_at TIMESTAMP WITHOUT TIME ZONE, produto varchar(255), update_at TIMESTAMP WITHOUT TIME ZONE, primary key (id));
create table tipo_servico (id bigint generated by default as identity, created_at TIMESTAMP WITHOUT TIME ZONE, servico varchar(255), update_at TIMESTAMP WITHOUT TIME ZONE, primary key (id));
alter table cliente add constraint UK_r1u8010d60num5vc8fp0q1j2a unique (cpf);
alter table fornecedor add constraint UK_6emiqll9boyqfhebn54afnm4r unique (email);
alter table ligacao_fornecedor_produto add constraint FK1q00gi5o4asu5u8ldk2n3tujb foreign key (idcategoria) references categoria;
alter table ligacao_fornecedor_produto add constraint FKk198k4q7y93786pjlrnk5c7fv foreign key (idfornecedor) references fornecedor;
alter table ligacao_fornecedor_produto add constraint FK5x50gw55xqf20te3fvavvugho foreign key (idtipoproduto) references tipo_produto;
alter table ligacao_fornecedor_servico add constraint FKkt0ktkh7anovcy1ao61tupv07 foreign key (idcategoria) references categoria;
alter table ligacao_fornecedor_servico add constraint FK1ra0of81orasy7w7o2bkgxb1h foreign key (idfornecedor) references fornecedor;
alter table ligacao_fornecedor_servico add constraint FKf9msts2t8frumdq7n01u6nauy foreign key (idtiposervico) references tipo_servico;
alter table produto add constraint FKg4cti9kc2xoiir44vmkwhfhmv foreign key (idcategoria) references categoria;
alter table produto add constraint FKlsqxnavohx1srw6bjra91n1rg foreign key (idtipoproduto) references tipo_produto;
alter table servico add constraint FK37qa7flajsm4limnnu9r4xq6m foreign key (idcategoria) references categoria;
alter table servico add constraint FKq76c52qqsvg2452dl05hl2shp foreign key (idtiposervico) references tipo_servico;
