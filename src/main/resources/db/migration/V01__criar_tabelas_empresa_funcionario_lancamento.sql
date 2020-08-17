create table empresa(
	id serial,
	cpnj character varying(20) NOT NULL,
	data_criacao  timestamp with time zone NOT NULL,
	data_atualizacao timestamp with time zone NOT NULL,
	razao_social character varying(12) NOT NULL,
	PRIMARY KEY(id)
);

create table funcionario(
	id serial,
	cpf character varying(20) NOT NULL,
	data_criacao  timestamp with time zone NOT NULL,
	data_atualizacao timestamp with time zone NOT NULL,
	email character varying(120) NOT NULL,
	senha character varying(12) NOT NULL,
	nome character varying(120) NOT NULL,
	pefil character varying(30) NOT NULL,
	qtd_horas_almoco float NOT NULL,
	qtd_horas_trabalho_dia float NOT NULL,
	valor_hora numeric(10,2) NOT NULL,
	empresa_id bigint NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_empresa
      FOREIGN KEY(empresa_id) 
	  REFERENCES empresa(id)
);

create table lancamento(
	id serial,
	data timestamp with time zone NOT NULL,
	data_criacao timestamp with time zone NOT NULL,
	data_atualizacao timestamp with time zone NOT NULL,
	tipo character varying(50) not null,
	descricao character varying(50) not null, 
	localizacao character varying(50) not null, 
	funcionario_id bigint NOT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_funcionario
      FOREIGN KEY(funcionario_id) 
	  REFERENCES funcionario(id)
);
