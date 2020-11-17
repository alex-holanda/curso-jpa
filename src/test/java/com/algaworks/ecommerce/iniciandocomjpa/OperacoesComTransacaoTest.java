package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {
	
	@Test
	public void atualizarObjetoGerenciado() {
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setNome("Kindle Paperwhite 2gen");
		
		entityManager.getTransaction().begin();
		
		entityManager.merge(produto);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificado = entityManager.find(Produto.class, 1);
		Assert.assertEquals("Kindle Paperwhite 2gen", produtoVerificado.getNome());
	}
	
	@Test
	public void atualizarObjetoTest() {
		Produto produto = new Produto();
		produto.setId(1);
		produto.setNome("Kindle Paperwhite");
		produto.setDescricao("Conheça o novo Kindle.");
		produto.setPreco(new BigDecimal(599));
		
		entityManager.getTransaction().begin();
		
		entityManager.merge(produto);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificado = entityManager.find(Produto.class, 1);
		Assert.assertNotNull(produtoVerificado);
	}

	@Test
	public void removerObjetoTest() {
		Produto produto = entityManager.find(Produto.class, 3);
		
		entityManager.getTransaction().begin();
		
		entityManager.remove(produto);
		
		entityManager.getTransaction().commit();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, 3);
		
		Assert.assertNull(produtoVerificacao);
	}
	
	@Test
	public void inserirPrimeiroObjeto() {
		Produto produto = new Produto();
		produto.setId(2);
		produto.setNome("Câmera Canon");
		produto.setDescricao("A melhor definição para suas fotos.");
		produto.setPreco(new BigDecimal("5000"));
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(produto);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificado = entityManager.find(Produto.class, 2);
		
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
