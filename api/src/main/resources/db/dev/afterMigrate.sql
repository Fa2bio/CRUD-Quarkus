delete from produto;
delete from pessoa;

INSERT INTO pessoa (nome, data_de_aniversario, tipo_sanguineo, endereco_cep, endereco_logradouro, endereco_complemento, endereco_bairro, endereco_localidade) 
VALUES ('Marcelo','10/01/2023', 'A+','01001000','Praça da Sé','lado impar','Sé','São Paulo');

INSERT INTO pessoa (nome, data_de_aniversario, tipo_sanguineo, endereco_cep, endereco_logradouro, endereco_complemento, endereco_bairro, endereco_localidade) 
VALUES ('Andrea','10/01/2023', 'B+','01001000','Praça da Sé','lado impar','Sé','São Paulo');

INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento) 
VALUES ('MAMINHA','KG', '10/01/2023');
INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento) 
VALUES ('MAMINHA','LBS', '10/02/2023');
INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento)
VALUES ('FRALDINHA','KG', '05/02/2023');
INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento)
VALUES ('FRALDINHA','LBS', '05/03/2023');