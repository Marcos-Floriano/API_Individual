insert into fabricante (nome)
values
('nestlé'), ('danone'), ('parmalat'), ('dolly');

insert into produto (nome, qtd_estoque, preco, fabricante_id)
values
('leite em pó', 1000, 12.5, 1),
('leite UHT 1L', 500, 6.3, 1),
('danoninho', 3000, 4.8, 2),
('achocolatado vitaminado', 820, 20.0, 3),
('guaraná 300ml', 200, 4.5, 4),
('guaraná 2L', 900, 9.5, 4);