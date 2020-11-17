package com.algaworks.ecommerce.model;

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
	
	private Integer pedidoId;
	
	private StatusPagamento status;
	
	private String numero;
}
