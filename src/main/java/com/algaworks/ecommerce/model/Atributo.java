package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Atributo {

	@Column(length = 100, nullable = false)
	private String nome;
	
	private String valor;
}
