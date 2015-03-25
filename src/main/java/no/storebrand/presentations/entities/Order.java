package no.storebrand.presentations.entities;

/**
 * @author OZY on 2015.03.23
 */

public class Order {

    public final Product product;

    public final int amount;

    public Order(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

}
