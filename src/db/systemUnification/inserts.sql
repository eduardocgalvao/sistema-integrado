-- =============================================
-- INSERTS: Dados iniciais do sistema
-- =============================================

-- Inserção dos usuários do sistema
INSERT INTO usuarios VALUES 
(1,'Yan eduardo','yanedu117@gmail.com','$2a$10$2bdc9X8tdzEwYVydg2VCAONswBwN/IUr.KPk.Xd16fOyyppFuQ7jq','(91) 98302-0280','TI','ADMIN','2025-10-02 08:46:50'),
(2,'iagox','iagox771@gmail.com','$2a$10$lzMzd8CGRv7K1HOraKwv4.is5K.6av5Zn7zX/c/k1xmiu1hX2Hvme','(91) 97913-7415','TI','USER','2025-10-02 18:19:55'),
(3,'demiurgo','demiurgo666@gmail.com','$2a$10$fTtCpgJQoCtvJeJL.NjmfunmoJbu6gEBcQK3JTCRjw1S9yAuD1D3O','(93) 97935-7952','TI','USER','2025-10-02 18:21:59'),
(4,'Renato Lobo','teste@gmail.com','$2a$10$dLpIx8TJcqiuWjsNxUFcieIBPSTOCQlT1Zkhdna2kTszxb1nY7Xgm','(91) 12345-6789','TI','ADMIN','2025-10-02 22:18:59'),
(6,'eduardo','teste2@gmail.com','$2a$10$zci8XC391At/CRrh6G7tPeaB.jSzzXYIMqZ/HrMq17d1B8JhxPBiC','(91) 98302-0280','Suporte','ADMIN','2025-10-02 22:36:22');

-- Inserção dos status de chamado padrão
INSERT INTO status_chamado VALUES 
(1,'Aberto','Chamado aberto e aguardando atendimento','#dc3545',1),
(2,'Em andamento','Chamado em processo de resolução','#ffc107',2),
(3,'Fechado','Chamado resolvido e finalizado','#28a745',3);

-- Inserção dos chamados existentes
INSERT INTO chamados VALUES 
(12,'Problema com Mouse','Mouse','bugou carai','2025-09-30 00:00:00',NULL,1,2,NULL,'Média',NULL,NULL,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
(17,'Teclado com defeito','Teclado','quebrou','2025-10-02 00:00:00',NULL,1,1,NULL,'Média',NULL,NULL,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),
(20,'Notebook com problema','notebook','quebrou','2025-10-02 00:00:00',NULL,1,1,NULL,'Média',NULL,NULL,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);usuarios