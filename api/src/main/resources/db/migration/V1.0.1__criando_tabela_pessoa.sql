CREATE TABLE pessoa(
	codigo_pessoa BIGINT PRIMARY KEY IDENTITY,
	nome VARCHAR(100) NOT NULL,
	data_de_aniversario VARCHAR(10) NOT NULL,
	tipo_sanguineo VARCHAR(3) NOT NULL,
	endereco_cep VARCHAR(255) NOT NULL,
	endereco_logradouro VARCHAR(255) NOT NULL,
	endereco_complemento VARCHAR(255) NOT NULL,
	endereco_bairro VARCHAR(255) NOT NULL,
	endereco_localidade VARCHAR(255) NOT NULL
);