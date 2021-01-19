package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Atributo {

	@NotBlank
	@Column(length = 100, nullable = false)
	private String nome;

	@NotBlank
	private String valor;
}
