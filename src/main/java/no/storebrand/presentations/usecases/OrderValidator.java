package no.storebrand.presentations.usecases;


import no.storebrand.presentations.entities.Order;
import no.storebrand.presentations.repositories.ProductRepository;

/**
 * @author OZY on 2015.03.23
 */

public class OrderValidator {

    private final ProductRepository productRepository;

    public OrderValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validateImperativelyUsingExceptions(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }

        int productsInStock = productRepository.availableProductsInStockByName(order.product.name);
        if (order.amount > productsInStock) {
            throw new InsufficientProductAmount("Insufficient amount [" + productsInStock + "] of product " + order.product.name + " in stock");
        }
    }

    public class InsufficientProductAmount extends RuntimeException {
        public InsufficientProductAmount(String message) {
            super(message);
        }
    }
}
