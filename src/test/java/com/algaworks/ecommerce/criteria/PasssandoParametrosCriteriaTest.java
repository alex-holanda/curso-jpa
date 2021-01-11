package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PasssandoParametrosCriteriaTest extends EntityManagerTest {

    @Test
    public void passarParametro() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        ParameterExpression<Integer> parameterExpressionId = criteriaBuilder.parameter(Integer.class);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), parameterExpressionId));

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter(parameterExpressionId, 1);

        Pedido pedido = typedQuery.getSingleResult();
        Assert.assertNotNull(pedido);
    }

    @Test
    public void passarParametroDate() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NotaFiscal> criteriaQuery = criteriaBuilder.createQuery(NotaFiscal.class);
        Root<NotaFiscal> root = criteriaQuery.from(NotaFiscal.class);

        criteriaQuery.select(root);

        ParameterExpression<Date> parameterExpressionData = criteriaBuilder.parameter(Date.class, "dataInicial");

        criteriaQuery.where(criteriaBuilder.greaterThan(root.get("dataEmissao"), parameterExpressionData));

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -180);
        Date dataInicial = c.getTime();

        TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("dataInicial", dataInicial, TemporalType.DATE);

        List<NotaFiscal> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
