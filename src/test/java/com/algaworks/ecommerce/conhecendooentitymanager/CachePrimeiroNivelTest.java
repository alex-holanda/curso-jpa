package com.algaworks.ecommerce.conhecendooentitymanager;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class CachePrimeiroNivelTest extends EntityManagerTest {

	@Test
	public void analisarEstados() {
	
		Categoria categoriaNovo = new Categoria();
		Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo);
		categoriaGerenciadaMerge.getId();
		
		Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);
		
		entityManager.remove(categoriaGerenciada);
		entityManager.persist(categoriaGerenciada);
		
		entityManager.detach(categoriaGerenciada);
		
		
	}
}
