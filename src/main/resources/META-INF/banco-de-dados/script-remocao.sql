alter table categoria 
       drop 
       foreign key fk_categoria_categoriapai
dez 02, 2020 12:41:44 PM org.hibernate.resource.transaction.backend.jdbc.internal.DdlTransactionIsolatorNonJtaImpl getIsolatedConnection
INFO: HHH10001501: Connection obtained from JdbcConnectionAccess [org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentInitiator$ConnectionProviderJdbcConnectionAccess@3c0e00a8] for (non-JTA) DDL execution was not in auto-commit mode; the Connection 'local transaction' will be committed and the Connection will be set into auto-commit mode.
 
    
    alter table cliente_contato 
       drop 
       foreign key FKmrem5leyn2kay6sk0ja4ff8es
 
    
    alter table cliente_detalhe 
       drop 
       foreign key fk_cliente_detalhes_cliente
 
    
    alter table estoque 
       drop 
       foreign key fk_estoque_produto
 
    
    alter table item_pedido 
       drop 
       foreign key fk_item_pedido_pedido
 
    
    alter table item_pedido 
       drop 
       foreign key fk_item_pedido_produto
 
    
    alter table nota_fiscal 
       drop 
       foreign key fk_nota_fiscal_pedido
 
    
    alter table pagamento 
       drop 
       foreign key fk_pagamento_pedido
 
    
    alter table pedido 
       drop 
       foreign key fk_pedido_cliente
 
    
    alter table produto_atributo 
       drop 
       foreign key fk_produto_atributo_produto
 
    
    alter table produto_categoria 
       drop 
       foreign key fk_produto_categoria_categoria
 
    
    alter table produto_categoria 
       drop 
       foreign key fk_produto_categoria_produto
 
    
    alter table produto_tag 
       drop 
       foreign key fk_produto_tag_produto
 
    
    drop table if exists categoria
 
    
    drop table if exists cliente
 
    
    drop table if exists cliente_contato
 
    
    drop table if exists cliente_detalhe
 
    
    drop table if exists estoque
 
    
    drop table if exists item_pedido
 
    
    drop table if exists nota_fiscal
 
    
    drop table if exists pagamento
 
    
    drop table if exists pedido
 
    
    drop table if exists produto
 
    
    drop table if exists produto_atributo
 
    
    drop table if exists produto_categoria
 
    
    drop table if exists produto_tag