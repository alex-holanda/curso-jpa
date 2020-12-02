package com.algaworks.ecommerce.mapeamentoavancado;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.SexoCliente;

public class SecondaryTableTest extends EntityManagerTest {

	@Test
	public void salvarCliente() {
		entityManager.getTransaction().begin();
		
		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Finotti");
		cliente.setCpf("334567765432");
		cliente.setSexo(SexoCliente.MASCULINO);
		cliente.setDataNascimento(LocalDate.of(1990, 1, 1));
		entityManager.persist(cliente);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao.getSexo());
	}
}
