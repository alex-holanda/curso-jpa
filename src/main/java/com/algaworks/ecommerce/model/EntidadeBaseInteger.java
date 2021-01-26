package com.algaworks.ecommerce.model;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@MappedSuperclass
public class EntidadeBaseInteger {

	@Version
	private Integer versao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}
