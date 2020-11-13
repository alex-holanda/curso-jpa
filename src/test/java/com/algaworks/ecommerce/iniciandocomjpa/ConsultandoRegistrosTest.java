package com.algaworks.ecommerce.iniciandocomjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.ecommerce.model.Produto;

public class ConsultandoRegistrosTest {

	private static EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		entityManagerFactory.close();
	}
	
	@Before
	public void setUp() {
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@After
	public void tearDown() {
		entityManager.close();
	}
	
	@Test
	public void buscarPorIdentificador() {
		Produto produto = entityManager.find(Produto.class, 1);
		
		Assert.assertNotNull(produto);
		Assert.assertEquals("Kindle", produto.getNome());
	}
	
	@Test
	public void atualizarReferencia() {
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setNome("Microfone Samson");
		
		entityManager.refresh(produto);
		
		Assert.assertEquals("Kindle", produto.getNome());
	}

}
