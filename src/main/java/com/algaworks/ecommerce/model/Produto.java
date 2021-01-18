package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.service.GenericoListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners({GenericoListener.class})
@Table(name = "produto", indexes = {@Index(name = "idx_nome", columnList = "nome")}, uniqueConstraints = {
        @UniqueConstraint(name = "unq_nome", columnNames = {"nome"})})
@NamedQueries({
        @NamedQuery(name = "Produto.listar", query = "select p from Produto p"),
        @NamedQuery(name = "Produto.listarPorCategoria", query = "select p from Produto p where exists (select 1 from Categoria c2 join c2.produtos p2 where p2 = p and c2.id = :categoria)")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "produto_loja.Produto", entities = { @EntityResult(entityClass = Produto.class) }),
        @SqlResultSetMapping(name = "ecm_produto.Produto", entities = { @EntityResult(entityClass = Produto.class,
            fields = {
                @FieldResult(name = "id", column = "prd_id"),
                @FieldResult(name = "nome", column = "prd_nome"),
                @FieldResult(name = "descricao", column = "prd_descricao"),
                @FieldResult(name = "preco", column = "prd_preco"),
                @FieldResult(name = "foto", column = "prd_foto"),
                @FieldResult(name = "dataCriacao", column = "prd_data_criacao"),
                @FieldResult(name = "dataUltimaAtualizacao", column = "prd_data_ultima_atualizacao")
            }
        ) }),
        @SqlResultSetMapping(name = "ecm_produto.ProdutoDTO",
                classes = {
                    @ConstructorResult(targetClass = ProdutoDTO.class,
                            columns = {
                                @ColumnResult(name = "prd_id", type = Integer.class),
                                @ColumnResult(name = "prd_nome", type = String.class)
                            })
                })
})
public class Produto extends EntidadeBaseInteger {

    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(length = 100, nullable = false)
    private String nome;

    //	sql: descricao longtext
    @Lob
    private String descricao;

    private BigDecimal preco;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_produto_categoria_produto")),
            inverseJoinColumns = @JoinColumn(name = "categoria_id", foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria")))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_produto_tag_produto")))
    @Column(name = "tag", length = 50, nullable = false)
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_produto_atributo_produto")))
    private List<Atributo> atributos;

    @Lob
    private byte[] foto;
}
