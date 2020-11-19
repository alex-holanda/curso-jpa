package com.algaworks.ecommerce.relacionamento;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Produto;

public class RelacionanemtoManyToManyTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamentoProdutoCategoria() {
		Produto produto = entityManager.find(Produto.class, 1);
		Categoria categoria = entityManager.find(Categoria.class, 1);
		
		
		entityManager.getTransaction().begin();
		
		produto.setCategorias(Arrays.asList(categoria));
//		Não funciona por não ser o Owner da relação
//		categoria.setProdutos(Arrays.asList(produto));
		
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
		Assert.assertFalse(categoriaVerificacao.getProdutos().isEmpty());
	}
}
