CREATE TABLE produto(
	codigo_produto BIGINT PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(800) NOT NULL,
	unidade_de_medida VARCHAR(10) NOT NULL,
	data_de_vencimento VARCHAR(10) NOT NULL
);