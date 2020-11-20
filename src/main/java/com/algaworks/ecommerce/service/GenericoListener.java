package com.algaworks.ecommerce.service;

import javax.persistence.PostLoad;

public class GenericoListener {

	@PostLoad
	public void logCarregamento(Object o) {
		System.out.println("Entidade " + o.getClass().getSimpleName() + " foi carregado");
	}
}
