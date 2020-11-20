package com.algaworks.ecommerce.conhecendooentitymanager;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class FlushTest extends EntityManagerTest {

	@Test(expected = Exception.class)
	public void chamarFlush() {

		try {
			entityManager.getTransaction().begin();

			Pedido pedido = entityManager.find(Pedido.class, 1);
			pedido.setStatus(StatusPedido.PAGO);

			entityManager.flush();

			if (pedido.getPagamento() == null) {
				throw new RuntimeException("Pedido ainda não foi pago");
			}
			
//			Uma consulta obriga o JPA a sincronizar o que ele tem na memória
//			Pedido pedidoPago = entityManager
//					.createQuery("select p from Pedido p where p.id = 1", Pedido.class)
//					.getSingleResult();
			
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}

	}
}
