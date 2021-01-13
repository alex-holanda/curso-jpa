package com.algaworks.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@DiscriminatorValue("boleto")
public class PagamentoBoleto extends Pagamento {
	
	@Column(name = "codigo_barras", length = 100)
	private String codigoBarras;

	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
}
