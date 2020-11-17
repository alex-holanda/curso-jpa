package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "estoque")
public class Estoque {

	@Id
	private Integer id;
	
	@Column(name = "produto_id")
	private Integer produtoId;
	
	private Integer quantidade;
}
