package no.storebrand.presentations.repositories;

/**
 * @author OZY on 2015.03.23
 */

public interface ProductRepository {

    int availableProductsInStockByName(String productName);
}
