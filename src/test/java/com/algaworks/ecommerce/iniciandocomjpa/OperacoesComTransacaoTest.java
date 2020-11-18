package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {
	
	@Test
	public void impedirOperacaoComBancoDedadosTest() {
		Produto produto = entityManager.find(Produto.class, 1);		
		entityManager.detach(produto);
		
		produto.setNome("Kindle Paperwhite 2gen");
		
		entityManager.getTransaction().begin();
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle", produtoVerificado.getNome());
	}
	
	@Test
	public void mostrarDiferencaPersistMerge() {
		Produto produtoPersist = new Produto();
		produtoPersist.setNome("Smartphone One Plus");
		produtoPersist.setDescricao("O processador mais rápido.");
		produtoPersist.setPreco(new BigDecimal("2000"));
		
		entityManager.getTransaction().begin();
		entityManager.persist(produtoPersist);
		
		produtoPersist.setNome("Smartphone Two Plus"); // instância gerenciada
		
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Produto produtoVerificadoPersist = entityManager.find(Produto.class, produtoPersist.getId());
		Assert.assertNotNull(produtoVerificadoPersist);
		
		Produto produtoMerge = new Produto();
		produtoMerge.setNome("Notebook Dell");
		produtoMerge.setDescricao("O melhor da categoria.");
		produtoMerge.setPreco(new BigDecimal("2000"));
		
		entityManager.getTransaction().begin();
		produtoMerge = entityManager.merge(produtoPersist);
		
		produtoMerge.setNome("Notebook Dell 2");
		
		entityManager.getTransaction().commit();
		entityManager.clear();
		
		Produto produtoVerificadoMerge = entityManager.find(Produto.class, produtoMerge.getId());
		Assert.assertNotNull(produtoVerificadoMerge);
	}
	
	@Test
	public void inserirObjetoComMerge() {
		Produto produto = new Produto();
		produto.setNome("Microfone Rode Videmic");
		produto.setDescricao("A melhor qualidade de som.");
		produto.setPreco(new BigDecimal("4000"));
		
		entityManager.getTransaction().begin();
		
		produto = entityManager.merge(produto);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
		
		Assert.assertNotNull(produtoVerificado);
	}
	
	@Test
	public void atualizarObjetoGerenciado() {
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setNome("Kindle Paperwhite 2gen");
		
		entityManager.getTransaction().begin();
		
		entityManager.merge(produto);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle Paperwhite 2gen", produtoVerificado.getNome());
	}
	
	@Test
	public void atualizarObjetoTest() {
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setNome("Kindle Paperwhite");
		produto.setDescricao("Conheça o novo Kindle.");
		produto.setPreco(new BigDecimal(599));
		
		entityManager.getTransaction().begin();
		
		entityManager.merge(produto);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificado);
	}

	@Test
	public void removerObjetoTest() {
		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		
		entityManager.remove(produto);
		
		entityManager.getTransaction().commit();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		
		Assert.assertNull(produtoVerificacao);
	}
	
	@Test
	public void inserirPrimeiroObjeto() {
		Produto produto = new Produto();
		produto.setNome("Câmera Canon");
		produto.setDescricao("A melhor definição para suas fotos.");
		produto.setPreco(new BigDecimal("5000"));
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(produto);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
		
		Assert.assertNotNull(produtoVerificado);
	}
	
	public void abrirEFecharATransacao() {
//		Somente para o método não mostrar erro
		Produto produto = new Produto();
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(produto);
		entityManager.merge(produto);
		entityManager.remove(produto);
		
		entityManager.getTransaction().commit();
	}
}
