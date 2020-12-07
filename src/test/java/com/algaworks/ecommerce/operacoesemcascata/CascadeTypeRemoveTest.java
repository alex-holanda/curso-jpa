package com.algaworks.ecommerce.operacoesemcascata;

import org.junit.Assert;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;

public class CascadeTypeRemoveTest extends EntityManagerTest {
	
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
