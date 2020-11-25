package com.algaworks.ecommerce.mapeamentoavancado;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;

public class SalvandoArquivosTest extends EntityManagerTest {

	@Test
	public void salvarXmlNota() {
		entityManager.getTransaction().begin();
		
		Pedido pedido = entityManager.find(Pedido.class, 1);
		
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setPedido(pedido);
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setXml(carregarArquivo("/nota-fiscal.xml"));
		entityManager.persist(notaFiscal);
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
		Assert.assertNotNull(notaFiscalVerificacao.getXml());
		Assert.assertTrue(notaFiscalVerificacao.getXml().length > 0);
		
//		Salva em disco a nota recuperada do banco de dados
//		try {
//			OutputStream out = new FileOutputStream(Files.createFile(Paths.get(System.getProperty("user.home") + "/xml.xml")).toFile());
//			out.write(notaFiscalVerificacao.getXml());
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
	}
	
	@Test
	public void salvarFotoProduto() {
		entityManager.getTransaction().begin();
		
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setFoto(carregarArquivo("/kindle.jpg"));
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao.getFoto());
	}

	private static byte[] carregarArquivo(String nomeArquivo) {
		try {
			return SalvandoArquivosTest.class.getResourceAsStream(nomeArquivo).readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
