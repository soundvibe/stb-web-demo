package no.storebrand.presentations.usecases;


import no.storebrand.presentations.entities.Order;
import no.storebrand.presentations.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

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
        if (productsInStock < order.amount) {
            throw new InsufficientProductAmount("Insufficient amount [" + productsInStock + "] of product " + order.product.name + " in stock");
        }
    }

    public boolean validateImperativelyCollectingErrors(Order order, List<String> errors) {
        if (order == null) {
            errors.add("Order cannot be null");
            return false;
        }

        if (order.product == null) {
            errors.add("Product cannot be null");
            return false;
        }

        int productsInStock = productRepository.availableProductsInStockByName(order.product.name);
        if (productsInStock < order.amount) {
            errors.add("Insufficient amount [" + productsInStock + "] of product " + order.product.name + " in stock");
            return false;
        }
        return true;
    }

    public void validateWithOptionalUsingExceptions(Order order) {
        Optional.ofNullable(order)
                .map(o -> order.product)
                .map(product -> productRepository.availableProductsInStockByName(product.name))
                .filter(productsInStock -> productsInStock >= order.amount)
                .orElseThrow(() -> new InsufficientProductAmount("Insufficient product amount in stock"));
    }

    public class InsufficientProductAmount extends RuntimeException {
        public InsufficientProductAmount(String message) {
            super(message);
        }
    }
}
