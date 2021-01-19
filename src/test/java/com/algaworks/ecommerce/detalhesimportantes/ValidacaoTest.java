package com.algaworks.ecommerce.detalhesimportantes;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Test;

import javax.validation.ConstraintViolationException;

public class ValidacaoTest extends EntityManagerTest {

    @Test(expected = ConstraintViolationException.class)
    public void validarCliente() {
        entityManager.getTransaction().begin();

        Cliente cliente = new Cliente();

        entityManager.merge(cliente);

        entityManager.getTransaction().commit();
    }
}
