package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import com.algaworks.ecommerce.service.GenericoListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido")
@EntityListeners({ GerarNotaFiscalListener.class, GenericoListener.class })
public class Pedido extends EntidadeBaseInteger {

	@PastOrPresent
	@Column(name = "data_criacao", updatable = false, nullable = false)
	private LocalDateTime dataCriacao;

	@PastOrPresent
	@Column(name = "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;

	@PastOrPresent
	@Column(name = "data_conclusao")
	private LocalDateTime dataConclusao;

	@OneToOne(mappedBy = "pedido")
	private NotaFiscal notaFiscal;

	@NotNull
	@PositiveOrZero
	private BigDecimal total;

	@NotNull
	@Column(length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@Embedded
	private Endereco endereco;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
	private Cliente cliente;

	@NotEmpty
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;

	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;

	private void calcularTotal() {
		total = BigDecimal.ZERO;

		if (itens != null) {
			total = itens.stream()
					.map(i -> new BigDecimal(i.getQuantidade()).multiply(i.getPrecoProduto()))
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
	}

	@PrePersist
	public void aoPersistir() {
		dataCriacao = LocalDateTime.now();
		calcularTotal();
	}

	@PreUpdate
	public void aoAtualizar() {
		dataUltimaAtualizacao = LocalDateTime.now();
		calcularTotal();
	}

	@PostPersist
	public void aposPersistir() {
		System.out.println(">>> Após persistir Pedido");
	}

	@PostUpdate
	public void aposAtualizar() {
		System.out.println(">>> Após atualizar Pedido");
	}

	@PreRemove
	public void aoRemover() {
		System.out.println(">>> Antes de remover Pedido ");
	}

	@PostRemove
	public void aposRemover() {
		System.out.println(">>> Após remover Pedido");
	}

	@PostLoad
	public void aoCarregar() {
		System.out.println("Após carregar o Pedido");
	}

	public boolean isPago() {
		return StatusPedido.PAGO.equals(status);
	}
}
