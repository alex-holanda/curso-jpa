package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("cartao")
public class PagamentoCartao extends Pagamento {
	
	@Column(name = "numero_cartao")
	private String numero;
}
