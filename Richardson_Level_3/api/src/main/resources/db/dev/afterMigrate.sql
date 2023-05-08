delete from produto;
delete from pessoa;

INSERT INTO pessoa (nome, data_de_aniversario, tipo_sanguineo, cpf, endereco_cep, endereco_logradouro, endereco_complemento, endereco_bairro, endereco_localidade) 
VALUES ('Marcelo','10/01/2023', 'A+','509.282.630-45','01001000','Praça da Sé','lado impar','Sé','São Paulo');

INSERT INTO pessoa (nome, data_de_aniversario, tipo_sanguineo,cpf, endereco_cep, endereco_logradouro, endereco_complemento, endereco_bairro, endereco_localidade) 
VALUES ('Andrea','10/01/2023', 'B+','890.940.110-93','01001000','Praça da Sé','lado impar','Sé','São Paulo');

INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento, total_de_vendas, preco, total_em_estoque) 
VALUES ('MAMINHA','KG', '10/01/2023',10, 29.90,10);
INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento, total_de_vendas, preco, total_em_estoque) 
VALUES ('MAMINHA','KG', '15/05/2023',5, 39.90,8);
INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento, total_de_vendas, preco, total_em_estoque) 
VALUES ('MAMINHA','LBS', '10/02/2023',7,19.90,5);
INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento, total_de_vendas, preco, total_em_estoque)
VALUES ('FRALDINHA','KG', '05/02/2023',25,39.90,7);
INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento, total_de_vendas, preco, total_em_estoque)
VALUES ('FRALDINHA','LBS', '05/03/2023',5,49.9,10);
INSERT INTO produto (descricao, unidade_de_medida, data_de_vencimento, total_de_vendas, preco, total_em_estoque)
VALUES ('FRALDINHA','LBS', '08/02/2023',6,29.9,5);