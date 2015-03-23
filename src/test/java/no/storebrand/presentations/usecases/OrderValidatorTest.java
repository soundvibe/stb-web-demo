package no.storebrand.presentations.usecases;

import no.storebrand.presentations.entities.Order;
import no.storebrand.presentations.entities.Product;
import org.junit.Test;

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
}