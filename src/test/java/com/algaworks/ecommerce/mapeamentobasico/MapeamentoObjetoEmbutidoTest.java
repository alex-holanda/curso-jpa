package com.algaworks.ecommerce.mapeamentobasico;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Endereco;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.StatusPedido;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

	@Test
	public void analisarMapeamentoObjetoEmbutidoTest() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Endereco endereco = new Endereco();
		endereco.setCep("00000-000");
		endereco.setLogradouro("Rua das Laranjeiras");
		endereco.setNumero("123");
		endereco.setBairro("Centro");
		endereco.setCidade("Uberlândia");
		endereco.setUf("MG");
		
		Pedido pedido = new Pedido();
//		pedido.setId(1); // Comentado por está utilizando a estratégia Identity
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setTotal(new BigDecimal("1000"));
		pedido.setEndereco(endereco);
		pedido.setCliente(cliente);
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao);
		Assert.assertNotNull(pedidoVerificacao.getEndereco());
		Assert.assertNotNull(pedidoVerificacao.getEndereco().getCep());
	}
}
