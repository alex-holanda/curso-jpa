package com.algaworks.ecommerce.model;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "item_pedido")
@SqlResultSetMappings({
		@SqlResultSetMapping(name = "item_pedido-produto.ItemPedido-Produto",
			entities = {@EntityResult(entityClass = ItemPedido.class), @EntityResult(entityClass = Produto.class)})
})
public class ItemPedido {

	@EmbeddedId
	private ItemPedidoId id;
	
	@ManyToOne(optional = false)
	@MapsId("pedidoId")
	@JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_pedido_pedido"))
	private Pedido pedido;
	
	@MapsId("produtoId")
	@ManyToOne(optional = false)
	@JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_pedido_produto"))
	private Produto produto;
	
	@Column(name = "preco_produto", nullable = false)
	private BigDecimal precoProduto;
	
	@Column(nullable = false)
	private Integer quantidade;
}
