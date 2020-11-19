package com.algaworks.ecommerce.relacionamento;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class EagerELazyTest extends EntityManagerTest {

	@Test
	public void verificarComportamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
//		Eager - j� est� preenchido
		pedido.getCliente();
		
//		Lazy - ainda n�o est� preenchido - precisa utilizar
//		ao utilizar, faz a busca na base de dados
		pedido.getItens().isEmpty();
	}
}
