package com.algaworks.ecommerce.operacoesemcascata;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.SexoCliente;
import com.algaworks.ecommerce.model.StatusPedido;

public class CascadeTypePersistTest extends EntityManagerTest {

//	@Test
	public void persistirPedidoComItens() {
		
		entityManager.getTransaction().begin();
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(produto.getPreco());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(1);
		itemPedido.setPrecoProduto(produto.getPreco());
		
		pedido.setItens(Arrays.asList(itemPedido)); // CascadeType.PERSIST
		
		entityManager.persist(pedido);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
	}
	
	@Test
	public void persistirItemPedidoComPedido() {
		entityManager.getTransaction().begin();
		
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(produto.getPreco());
		pedido.setStatus(StatusPedido.AGUARDANDO);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.setPedido(pedido); // Não é necessário CascadeType.PERSIST por que possui @MapsId
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(1);
		itemPedido.setPrecoProduto(produto.getPreco());
		entityManager.persist(itemPedido);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
	}
	
//	@Test
	public void persistirPedidoComCliente() {
		entityManager.getTransaction().begin();
		
		Cliente cliente = new Cliente();
		cliente.setDataNascimento(LocalDate.of(1980, 1, 1));
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setNome("José Carlos");
		cliente.setCpf("09876534567");
		
		Pedido pedido = new Pedido();
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setCliente(cliente); // CascadeType.PERSIST
		pedido.setTotal(BigDecimal.ZERO);
		pedido.setStatus(StatusPedido.AGUARDANDO);
		entityManager.persist(pedido);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao);
	}
}
