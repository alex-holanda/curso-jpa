package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto {

	@Id
	private Integer id;
	
	@Column(name = "pedido_id")
	private Integer pedidoId;
	
	private StatusPagamento status;
	
	private String numero;
}
