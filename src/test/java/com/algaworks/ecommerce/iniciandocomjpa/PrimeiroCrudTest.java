package com.algaworks.ecommerce.iniciandocomjpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

public class PrimeiroCrudTest extends EntityManagerTest {

//	CRUD
	
	@Test
	public void createClienteTest() {
		Cliente cliente = new Cliente();
		cliente.setNome("Guilherme Boulos");
		cliente.setCpf("123455666");
		cliente.setSexo(SexoCliente.MASCULINO);
		
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
		cliente.setCpf("44455566777888");
		cliente.setSexo(SexoCliente.FEMININO);
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
		assertEquals("Bruno Covas", clienteVerificado.getNome());
	}
	
	@Test
	public void deleteClienteTest() {
		entityManager.getTransaction().begin();
		Cliente cliente = entityManager.find(Cliente.class, 2);
		
		entityManager.remove(cliente);

		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
		assertNull(clienteVerificado);
	}
}
