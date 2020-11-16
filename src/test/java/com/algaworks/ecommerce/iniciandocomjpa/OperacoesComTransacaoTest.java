package com.algaworks.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OperacoesComTransacaoTest extends EntityManagerTest {

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
