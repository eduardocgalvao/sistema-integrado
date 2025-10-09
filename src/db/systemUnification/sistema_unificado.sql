-- Criação do banco de dados unificado
CREATE DATABASE IF NOT EXISTS sistema_unificado;
USE sistema_unificado;

-- =============================================
-- TABELA: usuarios
-- Descrição: Armazena os usuários do sistema
-- =============================================
CREATE TABLE IF NOT EXISTS usuarios (
  id int NOT NULL AUTO_INCREMENT,                           -- ID único do usuário
  nome varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,    -- Nome completo do usuário
  email varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,   -- E-mail único do usuário
  senha varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,   -- Senha criptografada
  telefone varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Telefone para contato
  setor varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Setor/departamento do usuário
  role enum('ADMIN','USER') COLLATE utf8mb4_unicode_ci DEFAULT 'USER', -- Nível de acesso (ADMIN/USER)
  criado_em timestamp NULL DEFAULT CURRENT_TIMESTAMP,       -- Data de criação do registro
  PRIMARY KEY (id),
  UNIQUE KEY email (email)                                -- Garante e-mail único
);

-- =============================================
-- TABELA: ativos
-- Descrição: Cadastro de ativos/equipamentos da empresa
-- =============================================
CREATE TABLE IF NOT EXISTS ativos (
  id int NOT NULL AUTO_INCREMENT,                           -- ID único do ativo
  tipo varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,    -- Tipo de equipamento (notebook, mouse, etc)
  marca varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Marca do equipamento
  modelo varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Modelo do equipamento
  num_serie varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Número de série único
  setor varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Setor onde o ativo está alocado
  responsavel_id int DEFAULT NULL,                          -- ID do usuário responsável pelo ativo
  status enum('Em uso','Em manutenção','Baixado') COLLATE utf8mb4_unicode_ci DEFAULT 'Em uso', -- Status atual do ativo
  valor decimal(10,2) DEFAULT NULL,                         -- Valor do equipamento
  garantia_fim date DEFAULT NULL,                           -- Data final da garantia
  descricao text COLLATE utf8mb4_unicode_ci,                -- Descrição detalhada do ativo
  criado_em timestamp NULL DEFAULT CURRENT_TIMESTAMP,       -- Data de criação do registro
  atualizado_em timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Data da última atualização
  PRIMARY KEY (id),
  UNIQUE KEY num_serie (num_serie),                       -- Garante número de série único
  KEY idx_ativos_setor (setor),                           -- Índice para busca por setor
  KEY idx_ativos_responsavel (responsavel_id),            -- Índice para busca por responsável
  CONSTRAINT fk_ativos_responsavel FOREIGN KEY (responsavel_id) REFERENCES usuarios (id) ON DELETE SET NULL ON UPDATE CASCADE -- Relacionamento com usuários
);

-- =============================================
-- TABELA: status_chamado
-- Descrição: Status disponíveis para os chamados
-- =============================================
CREATE TABLE IF NOT EXISTS status_chamado (
  id int NOT NULL AUTO_INCREMENT,                           -- ID único do status
  nome varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,     -- Nome do status (Aberto, Em andamento, etc)
  descricao varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Descrição do status
  cor varchar(7) COLLATE utf8mb4_unicode_ci DEFAULT '#007bff', -- Cor para representação visual
  ordem int DEFAULT 0,                                      -- Ordem de exibição
  PRIMARY KEY (id),
  UNIQUE KEY nome (nome)                                  -- Garante nome único
);

-- =============================================
-- TABELA: chamados
-- Descrição: Armazena os chamados técnicos
-- =============================================
CREATE TABLE IF NOT EXISTS chamados (
  id int NOT NULL AUTO_INCREMENT,                           -- ID único do chamado
  titulo varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,  -- Título resumido do chamado
  equipamento varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL, -- Equipamento com problema
  descricao text COLLATE utf8mb4_unicode_ci NOT NULL,       -- Descrição detalhada do problema
  data_abertura datetime DEFAULT CURRENT_TIMESTAMP,         -- Data e hora de abertura
  data_fechamento datetime DEFAULT NULL,                    -- Data e hora de fechamento (quando resolvido)
  usuario_id int NOT NULL,                                  -- ID do usuário que abriu o chamado
  status_id int NOT NULL DEFAULT 1,                         -- Status atual do chamado
  ativo_id int DEFAULT NULL,                                -- ID do ativo relacionado (opcional)
  prioridade enum('Baixa','Média','Alta','Urgente') COLLATE utf8mb4_unicode_ci DEFAULT 'Média', -- Nível de prioridade
  solucao text COLLATE utf8mb4_unicode_ci,                  -- Descrição da solução aplicada
  tecnico_id int DEFAULT NULL,                              -- ID do técnico responsável
  criado_em timestamp NULL DEFAULT CURRENT_TIMESTAMP,       -- Data de criação do registro
  atualizado_em timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Data da última atualização
  PRIMARY KEY (id),
  KEY fk_chamados_usuario (usuario_id),                   -- Índice para usuário
  KEY fk_chamados_status (status_id),                     -- Índice para status
  KEY fk_chamados_ativo (ativo_id),                       -- Índice para ativo
  KEY fk_chamados_tecnico (tecnico_id),                   -- Índice para técnico
  CONSTRAINT fk_chamados_ativo FOREIGN KEY (ativo_id) REFERENCES ativos (id) ON DELETE SET NULL, -- Relacionamento com ativos
  CONSTRAINT fk_chamados_status FOREIGN KEY (status_id) REFERENCES status_chamado (id), -- Relacionamento com status
  CONSTRAINT fk_chamados_tecnico FOREIGN KEY (tecnico_id) REFERENCES usuarios (id) ON DELETE SET NULL, -- Relacionamento com técnico
  CONSTRAINT fk_chamados_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) -- Relacionamento com usuário
);

-- =============================================
-- TABELA: chamado_historico
-- Descrição: Histórico de alterações dos chamados (auditoria)
-- =============================================
CREATE TABLE IF NOT EXISTS chamado_historico (
  id int NOT NULL AUTO_INCREMENT,                           -- ID único do registro de histórico
  chamado_id int NOT NULL,                                  -- ID do chamado relacionado
  usuario_id int NOT NULL,                                  -- ID do usuário que fez a alteração
  acao varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,    -- Tipo de ação realizada
  descricao text COLLATE utf8mb4_unicode_ci,                -- Descrição da alteração
  dados_anteriores json DEFAULT NULL,                       -- Dados antes da alteração (formato JSON)
  dados_novos json DEFAULT NULL,                            -- Dados após a alteração (formato JSON)
  criado_em timestamp NULL DEFAULT CURRENT_TIMESTAMP,       -- Data da alteração
  PRIMARY KEY (id),
  KEY fk_historico_chamado (chamado_id),                  -- Índice para chamado
  KEY fk_historico_usuario (usuario_id),                  -- Índice para usuário
  CONSTRAINT fk_historico_chamado FOREIGN KEY (chamado_id) REFERENCES chamados (id) ON DELETE CASCADE, -- Relacionamento com chamados
  CONSTRAINT fk_historico_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) -- Relacionamento com usuários
);