package com.algaworks.ecommerce.jpql;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.dto.ProdutoDTO;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;

public class BasicoJPQLTest extends EntityManagerTest {

    @Test
    public void usarDistinct() {
        String jpql = "select distinct(p) from Pedido p " +
                "join p.itens i join i.produto pro " +
                "where pro.id in (1, 2, 3, 4)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        System.out.println(lista.size());
    }

    @Test
    public void ordernarResultados() {
        String jpql = "select c from Cliente c order by c.nome desc"; // asc

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista =  typedQuery.getResultList();
        lista.forEach(c -> System.out.println(c.getId() + ", " + c.getNome()));
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void projetarNoDTO() {
        String jpql = "select new com.algaworks.ecommerce.dto.ProdutoDTO(id, nome) from Produto";

        TypedQuery<ProdutoDTO> typedQuery = entityManager.createQuery(jpql, ProdutoDTO.class);
        List<ProdutoDTO> lista = typedQuery.getResultList();

        lista.forEach(p -> System.out.println(p.getId() + ", " + p.getNome()));

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void projetarOResultado() {
        String jpql = "select id, nome from Produto";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));

        Assert.assertTrue(lista.get(0).length == 2);

    }

    @Test
    public void selecionarUmAtributoParaRetorno() {
        String jpql = "select p.nome from Produto p";

        TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);

        List<String> lista = typedQuery.getResultList();
        Assert.assertTrue(String.class.equals(lista.get(0).getClass()));

        String jpqlCliente = "select p.cliente from Pedido p";

        TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);

        List<Cliente> listaClientes = typedQueryCliente.getResultList();
        Assert.assertTrue(Cliente.class.equals(listaClientes.get(0).getClass()));
    }

    @Test
    public void mostrarDiferencaQueries() {
        String jpql = "select p from Pedido p where p.id = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        Pedido pedido1 = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido1);

        Query query = entityManager.createQuery(jpql);
        Pedido pedido2 = (Pedido) query.getSingleResult();
        Assert.assertNotNull(pedido2);

//		List<Pedido> lista = query.getResultList();
//		Assert.assertFalse(lista.isEmpty());

    }

    @Test
    public void buscarPorIdentificador() {
//		JAVA Persistence Query Language - JPQL

//		JPQL - select p from Pedido p where p.id = 1 - Refer�ncia a entidade

//		SQL - select p.* from pedido p where p.id = 1 - Refer�ncia a tabela do banco de dados

        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido);

//		List<Pedido> pedidoList = typedQuery.getResultList();
//		Assert.assertFalse(pedidoList.isEmpty());
    }
}
