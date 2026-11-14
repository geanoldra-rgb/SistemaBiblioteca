-- 1. Listar todo o acervo, com código, título, tipo e disponibilidade
-- (Nota: todos esses dados já estão na tabela item, mas incluímos um LEFT JOIN opcional 
-- caso você queira exibir também quem está com o item emprestado no momento)
SELECT 
    i.codigo,
    i.titulo,
    i.tipo,
    i.disponivel,
    u.nome AS emprestado_para
FROM item i
LEFT JOIN emprestimo e ON i.id = e.item_id AND e.data_devolucao IS NULL
LEFT JOIN usuario u ON e.usuario_id = u.id
ORDER BY i.codigo;

-- 2. Listar os empréstimos em aberto, com o nome do usuário e o título do item
SELECT 
    u.nome AS usuario,
    i.titulo AS item,
    e.data_retirada,
    e.data_devolucao_prevista
FROM emprestimo e
JOIN usuario u ON e.usuario_id = u.id
JOIN item i ON e.item_id = i.id
WHERE e.data_devolucao IS NULL;

-- 3. Calcular o total de multas acumuladas por usuário
SELECT 
    u.nome AS usuario,
    u.tipo AS perfil,
    COALESCE(SUM(e.valor_multa), 0.00) AS total_multas
FROM usuario u
LEFT JOIN emprestimo e ON u.id = e.usuario_id
GROUP BY u.id, u.nome, u.tipo
ORDER BY total_multas DESC;

-- 4. Listar os itens que nunca foram emprestados
SELECT 
    i.id,
    i.codigo,
    i.titulo,
    i.tipo
FROM item i
LEFT JOIN emprestimo e ON i.id = e.item_id
WHERE e.id IS NULL;
