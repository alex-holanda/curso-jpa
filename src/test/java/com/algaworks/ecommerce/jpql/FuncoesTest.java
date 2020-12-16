package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

public class FuncoesTest extends EntityManagerTest {

    @Test
    public void aplicarFuncaoColecao() {
        String jpql = "select size(p.itens) from Pedido p";

        TypedQuery<Integer> typedQuery = entityManager.createQuery(jpql, Integer.class);

        List<Integer> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(size -> System.out.println(size));
    }

    @Test
    public void aplicarFuncaoNumero() {
        String jpql = "select abs(p.total), mod(3, 2), sqrt(9) from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }

    @Test
    public void aplicarFuncaoData() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));

//        String jpql = "select current_date, current_time, current_timestamp from Pedido p";
//        String jpql = "select year(p.dataCriacao), month(p.dataCriacao), day(p.dataCriacao)  from Pedido p";

        String jpql = "select hour(p.dataCriacao), minute(p.dataCriacao), second(p.dataCriacao)  from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2]));
    }

    @Test
    public void aplicarFuncaoString() {
//        concat, lenght, locate, substring, lower, upper, trim
//        String jpql = "select c.nome, concat('Categoria: ', c.nome) from Categoria c"; - concatena
//        String jpql = "select c.nome, length(c.nome) from Categoria c"; - tamanho da string
//        String jpql = "select c.nome, locate('a', c.nome) from Categoria c"; - localiza o índice - se não encontrar
//          retorna 0
//        String jpql = "select c.nome, substring(c.nome, 1, 2) from Categoria c"; - corta a string
//          - 1a posição - string - 2a - o índice de inicio - 3a (opcional) - quantidade de caracteres
//        String jpql = "select c.nome, lower(c.nome) from Categoria c"; - deixa em caixa baixa
//        String jpql = "select c.nome, upper(c.nome) from Categoria c"; - deixa em caixa alta
//        String jpql = "select c.nome, trim(c.nome) from Categoria c"; - remove espaço em branco do inicio e do fim

        String jpql = "select c.nome, length(c.nome) from Categoria c where length(c.nome) > 10";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + " - " + arr[1]));
    }

}

