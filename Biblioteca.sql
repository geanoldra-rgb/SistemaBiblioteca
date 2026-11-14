-- ============================================================================
-- 1. CRIAÇÃO DAS TABELAS (Ordem: item -> usuario -> emprestimo)
-- ============================================================================

CREATE TABLE item (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    titulo VARCHAR(200) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('livro', 'revista')),
    autor VARCHAR(150),
    edicao VARCHAR(50),
    disponivel BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE usuario (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('aluno', 'professor')),
    limite_itens INTEGER NOT NULL CHECK (limite_itens > 0)
);

CREATE TABLE emprestimo (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id INTEGER NOT NULL REFERENCES item(id),
    usuario_id INTEGER NOT NULL REFERENCES usuario(id),
    data_retirada DATE NOT NULL DEFAULT CURRENT_DATE,
    data_devolucao_prevista DATE NOT NULL,
    data_devolucao DATE, -- Sem NOT NULL: permanece NULL enquanto estiver em aberto
    valor_multa NUMERIC(10, 2) NOT NULL DEFAULT 0.00
);

-- ============================================================================
-- 2. INSERÇÃO DE DADOS DE TESTE
-- ============================================================================

-- Inserindo 4 itens (livros e revistas)
-- Note que o segundo item (id 2) será inserido como FALSE pois terá um empréstimo em aberto abaixo
INSERT INTO item (codigo, titulo, tipo, autor, edicao, disponivel) VALUES
    ('L01', 'Clean Code', 'livro', 'Robert C. Martin', '1ª Edição', TRUE),
    ('L02', 'O Senhor dos Anéis', 'livro', 'J.R.R. Tolkien', 'Edição Única', FALSE),
    ('R01', 'National Geographic', 'revista', NULL, 'Edição 298', TRUE),
    ('R02', 'Superinteressante', 'revista', NULL, 'Edição 450', TRUE);

-- Inserindo 2 usuários (1 aluno com limite 3, 1 professor com limite 5)
INSERT INTO usuario (nome, tipo, limite_itens) VALUES
    ('Lucas Silva', 'aluno', 3),
    ('Dra. Helena Costa', 'professor', 5);

-- Inserindo 2 empréstimos:
-- 1) Em aberto: Livro 'L02' (item_id = 2) emprestado para Lucas (usuario_id = 1)
-- 2) Devolvido: Revista 'R01' (item_id = 3) devolvida com 2 dias de atraso (multa de R$ 2,00)
INSERT INTO emprestimo (item_id, usuario_id, data_retirada, data_devolucao_prevista, data_devolucao, valor_multa) VALUES
    (2, 1, '2026-11-10', '2026-11-24', NULL, 0.00),
    (3, 2, '2026-11-01', '2026-11-08', '2026-11-10', 2.00);
