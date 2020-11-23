package com.algaworks.ecommerce.relacionamento;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class RelacionanemtoManyToOneTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamentoPedidoCliente() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(BigDecimal.TEN);
		
		pedido.setCliente(cliente);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getCliente());
	}
	
	@Test
	public void verificarRelacionamentoItemPedido() {
		entityManager.getTransaction().begin();
		Produto produto = entityManager.find(Produto.class, 1);
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(BigDecimal.TEN);
		entityManager.persist(pedido);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));	
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		entityManager.persist(itemPedido);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(pedido.getId(), produto.getId()));
		Assert.assertNotNull(itemPedidoVerificacao.getPedido());
		Assert.assertNotNull(itemPedidoVerificacao.getProduto());
	}
}
