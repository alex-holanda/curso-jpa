package com.algaworks.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

	@Id
	private Integer id;
	
	@Column(name = "pedido_id")
	private Integer pedidoId;
	
	private String xml;
	
	@Column(name = "data_emissao")
	private Date dataEmissao;
}
