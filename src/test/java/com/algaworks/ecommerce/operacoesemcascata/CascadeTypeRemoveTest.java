package com.algaworks.ecommerce.operacoesemcascata;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

public class CascadeTypeRemoveTest extends EntityManagerTest {
	
//	@Test
	public void removerItensOrfaos() {
		entityManager.getTransaction().begin();
		
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		Assert.assertFalse(pedido.getItens().isEmpty());
		
		pedido.getItens().clear(); // Precisa do CascadeType.PERSIST e orphanRemove = true
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertTrue(pedidoVerificacao.getItens().isEmpty());
	}
	
	@Test
	public void removerRelacaoProdutoCategoria() {
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		Assert.assertFalse(produto.getCategorias().isEmpty());
		
		produto.getCategorias().clear();
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertTrue(produtoVerificacao.getCategorias().isEmpty());
	}
	
//	@Test
	public void removerPedidoEItens() {
		entityManager.getTransaction().begin();
		
		Pedido pedido = entityManager.find(Pedido.class, 1);
		entityManager.remove(pedido); // Necessário CascadeType.REMOVE no atributo "itens"
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNull(pedidoVerificacao);
	}
	
//	@Test
	public void removerItemPedidoEPedido() {
		entityManager.getTransaction().begin();
		
		ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));
		entityManager.remove(itemPedido); // Necessário CascadeType.REMOVE no atributo "pedido"
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, itemPedido.getId().getPedidoId());
		Assert.assertNull(pedidoVerificacao);
	}
}
