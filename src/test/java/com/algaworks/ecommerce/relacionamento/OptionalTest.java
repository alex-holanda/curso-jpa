package com.algaworks.ecommerce.relacionamento;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class OptionalTest extends EntityManagerTest {

	@Test
	public void verificarComportamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
//		Altera o join de left outer join para inner join
		
		Assert.assertNotNull(pedido);
	}
}
