create table foto_produto (
  	codigo_foto_produto BIGINT AUTO_INCREMENT NOT NULL,
  	produto_codigo_produto BIGINT NOT NULL,
  	file_name VARCHAR(150) NOT NULL,
  	descricao VARCHAR(150),
  	content_type VARCHAR(80) NOT NULL,

  	primary key (codigo_foto_produto),
  	constraint fk_foto_produto_produto foreign key (produto_codigo_produto) references produto (codigo_produto)
);