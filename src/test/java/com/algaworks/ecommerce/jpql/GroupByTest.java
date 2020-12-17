package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class GroupByTest extends EntityManagerTest {

    @Test
    public void agruparEFiltrarResultado() {

//        String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) from Pedido p"
//                + " where year(p.dataCriacao) = year(current_date)"
//                + " group by year(p.dataCriacao), month(p.dataCriacao)";

//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip "
//                + "join ip.produto pro join pro.categorias c join ip.pedido p "
//                + "where year(p.dataCriacao) = year(current_date) and month(p.dataCriacao) = month(current_date) "
//                + "group by c.id";

        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip "
                + "join ip.pedido p join p.cliente c "
                + "where year(p.dataCriacao) = year(current_date) and month(p.dataCriacao) >= (month(current_date) - 3) "
                + "group by c.id";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }

    @Test
    public void agruparResultado() {
//        Quantidade de produtos por categoria
//        String jpql = "select c.nome, count(c) from Categoria c join c.produtos p group by c.id";

//        Total de vendas por mês
//        String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) from Pedido p"
//                + " group by year(p.dataCriacao), month(p.dataCriacao)";

//        Total de vendas por categoria
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.produto pro join pro.categorias c"
//                + " group by c.id";

//        Total de vendas por cliente
//        String jpql = "select cli.nome, sum(ip.precoProduto) from ItemPedido ip join ip.pedido p join ip.produto pro"
//                + " join p.cliente cli group by cli.id";

//        Total de vendas por dia e por categoria
        String jpql = "select concat(year(p.dataCriacao), '/', month(p.dataCriacao), '/', day(p.dataCriacao)), "
                + " concat(c.nome, ' ', sum(ip.precoProduto)) from ItemPedido ip join ip.pedido p"
                + " join ip.produto pro join pro.categorias c"
                + " group by c.id, year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao)";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1]));
    }
}
