package com.algaworks.ecommerce.conhecendooentitymanager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class ListenersTest extends EntityManagerTest {
	
	@Test
	public void carregarEntidades() {
		Produto produto = entityManager.find(Produto.class, 1);
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		Assert.assertNotNull(produto);
		Assert.assertNotNull(pedido);
	}

	@Test
	public void adicionarListener() {
		entityManager.getTransaction().begin();
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);
		
		entityManager.persist(pedido);
		
		entityManager.flush();
		
		pedido.setStatus(StatusPedido.PAGO);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
		Assert.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
	}
}
