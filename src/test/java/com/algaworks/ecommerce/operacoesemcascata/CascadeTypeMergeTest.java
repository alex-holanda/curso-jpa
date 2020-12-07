package com.algaworks.ecommerce.operacoesemcascata;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Assert;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.ItemPedidoId;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class CascadeTypeMergeTest extends EntityManagerTest {
	
//	@Test
	public void atualizarProdutoComCategoria() {
		entityManager.getTransaction().begin();
		
		Produto produto = new Produto();
		produto.setId(1);
		produto.setDataUltimaAtualizacao(LocalDateTime.now());
		produto.setPreco(new BigDecimal("5000"));
		produto.setNome("Kindle");
		produto.setDescricao("Agora com iluminação embutida ajustável.");
		
		Categoria categoria = new Categoria();
		categoria.setId(2);
		categoria.setNome("Tablets");
		
		produto.setCategorias(Arrays.asList(categoria)); // CascadeType.MERGE
		
		entityManager.merge(produto);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
		Assert.assertTrue(categoriaVerificacao.getNome().equals("Tablets"));
	}

//	@Test
	public void atualizarPedidoComItens() {

		entityManager.getTransaction().begin();

		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);

		Pedido pedido = new Pedido();
		pedido.setId(1);
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.AGUARDANDO);

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.getId().setPedidoId(pedido.getId());
		itemPedido.getId().setProdutoId(produto.getId());
		itemPedido.setQuantidade(3);
		itemPedido.setProduto(produto);
		itemPedido.setPrecoProduto(produto.getPreco());

		pedido.setItens(Arrays.asList(itemPedido)); // CascadeType.MERGE

		entityManager.merge(pedido);

		entityManager.getTransaction().commit();

		entityManager.clear();

		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
		Assert.assertNotNull(itemPedidoVerificacao);
		Assert.assertTrue(itemPedidoVerificacao.getQuantidade().equals(3));
	}

//	@Test
	public void atualizarItemPedidoComPedido() {
		entityManager.getTransaction().begin();

		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);

		Pedido pedido = new Pedido();
		pedido.setId(1);
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.PAGO);

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setId(new ItemPedidoId());
		itemPedido.getId().setPedidoId(pedido.getId());
		itemPedido.getId().setProdutoId(produto.getId());
		itemPedido.setQuantidade(5);
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setProduto(produto);
		itemPedido.setPedido(pedido); // CascadeType.MERGE
		
		pedido.setItens(Arrays.asList(itemPedido));

		entityManager.merge(itemPedido);

		entityManager.getTransaction().commit();

		entityManager.clear();

		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
		Assert.assertTrue(StatusPedido.PAGO.equals(itemPedidoVerificacao.getPedido().getStatus()));
	}
}
