package com.algaworks.ecommerce.conhecendooentitymanager;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class EstadosEcicloDeVidaTest extends EntityManagerTest {

	@Test
	public void analisarEstados() {
		Categoria categoriaNovo = new Categoria();
		categoriaNovo.setNome("TV");
		
		Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);
		System.out.println(categoriaGerenciadaMerge);
		
		Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);
		
		entityManager.remove(categoriaGerenciada);
		entityManager.persist(categoriaGerenciada);
		
		entityManager.detach(categoriaGerenciada);
	}
}
