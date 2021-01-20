package com.algaworks.ecommerce.detalhesimportantes;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityGraph;
import java.util.List;

public class ProblemaN1Test extends EntityManagerTest {

    @Test
    public void resolverComEntityGraph() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("cliente" , "notaFiscal", "pagamento");

        List<Pedido> lista = entityManager
                .createQuery("select p from Pedido p")
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void resolverComFetch() {
        List<Pedido> lista = entityManager
                .createQuery("select p from Pedido p " +
                        "join fetch p.cliente " +
                        "join fetch p.notaFiscal " +
                        "join fetch p.pagamento", Pedido.class)
                .getResultList();

        Assert.assertFalse(lista.isEmpty());
    }
}
