package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Table(name = "pagamento_boleto")
@DiscriminatorValue("Boleto") 
public class PagamentoBoleto extends Pagamento {
	
	@Column(name = "codigo_barras")
	private String codigoBarras;
}
