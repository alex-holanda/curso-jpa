package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@Id
	private Integer id;
	
	private Integer produtoId;
	
	private BigDecimal precoProduto;
	
	private Integer quantidade;
}
