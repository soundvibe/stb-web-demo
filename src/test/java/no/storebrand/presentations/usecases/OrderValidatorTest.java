package no.storebrand.presentations.usecases;

import no.storebrand.presentations.entities.Order;
import no.storebrand.presentations.entities.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderValidatorTest {


    @Test(expected = OrderValidator.InsufficientProductAmount.class)
    public void whenOrdering10Products_AndIs1ProductInStock_ThrowIllegalProductAmountException() throws Exception {
        OrderValidator orderValidator = new OrderValidator(name -> 1);
        orderValidator.validateImperativelyUsingExceptions(new Order(new Product("Foo"), 10));
    }

    @Test
    public void whenOrdering10Products_AndIs10ProductsInStock_NoException() throws Exception {
        OrderValidator orderValidator = new OrderValidator(name -> 10);
        orderValidator.validateImperativelyUsingExceptions(new Order(new Product("Foo"), 10));
        assertTrue(true);
    }

    @Test(expected = OrderValidator.InsufficientProductAmount.class)
    public void whenOrdering10Products_AndIs1ProductInStock_ThrowIllegalProductAmountException_2() throws Exception {
        OrderValidator orderValidator = new OrderValidator(name -> 1);
        orderValidator.validateWithOptionalUsingExceptions(new Order(new Product("Foo"), 10));
    }

    @Test
    public void whenOrdering10Products_AndIs10ProductsInStock_NoException_2() throws Exception {
        OrderValidator orderValidator = new OrderValidator(name -> 10);
        orderValidator.validateWithOptionalUsingExceptions(new Order(new Product("Foo"), 10));
        assertTrue(true);
    }

    @Test(expected = OrderValidator.InsufficientProductAmount.class)
    public void whenOrderIsNull_ThrowException() throws Exception {
        OrderValidator orderValidator = new OrderValidator(name -> 10);
        orderValidator.validateWithOptionalUsingExceptions(null);
    }

    @Test
    public void whenFailsCollectOneError() throws Exception {
        OrderValidator orderValidator = new OrderValidator(name -> 10);
        List<String> errors = new ArrayList<>();
        orderValidator.validateImperativelyCollectingErrors(null, errors);
        assertEquals(1, errors.size());
    }
}