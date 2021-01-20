package com.algaworks.ecommerce.detalhesimportantes;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.Map;

public class EntityGraphTest extends EntityManagerTest {

    @Test
    public void buscarAtributosEssenciaisDePedido() {
        EntityGraph<Pedido> entityGraph = entityManager.createEntityGraph(Pedido.class);
        entityGraph.addAttributeNodes("dataCriacao", "status", "total", "cliente");

        Map<String, Object> properties = new HashMap<>();
//        properties.put("javax.persistence.fetchgraph", entityGraph);
        properties.put("javax.persistence.loadgraph", entityGraph);

        Pedido pedido = entityManager.find(Pedido.class, 1, properties);
        Assert.assertNotNull(pedido);
    }
}
