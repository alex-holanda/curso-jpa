package com.algaworks.ecommerce.iniciandocomjpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

public class PrimeiroCrudTest extends EntityManagerTest {

//	CRUD
	
	@Test
	public void createClienteTest() {
		Cliente cliente = new Cliente();
		cliente.setNome("Guilherme Boulos");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
		assertNotNull(clienteVerificado);
	}
	
	@Test
	public void readClientTtest() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		assertNotNull(cliente);
	}
	
	@Test
	public void updateClienteTest() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		cliente.setNome("Bruno Covas");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
		assertEquals("Bruno Covas", clienteVerificado.getNome());
	}
	
	@Test
	public void deleteClienteTest() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
		assertNull(clienteVerificado);
	}
}
