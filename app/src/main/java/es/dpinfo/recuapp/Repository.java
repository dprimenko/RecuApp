package es.dpinfo.recuapp;

import java.util.ArrayList;
import java.util.List;

import es.dpinfo.recuapp.models.Product;

/**
 * Created by dprimenko on 2/02/17.
 */
public class Repository {

    private List<Product> listProducts;

    private static Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
        listProducts = new ArrayList<>();

        addProduct(new Product("sports", "Balón de futbol", 5.99, Product.MEDIUM_IMPORTANT));
        addProduct(new Product("tech", "Samsung Galaxy S7 Note", 600.00, Product.VERY_IMPORTANT));
        addProduct(new Product("home", "Cuchara", 0.50, Product.LOW_IMPORTANT));
    }

    public void addProduct(Product product) {
        listProducts.add(product);
    }

    public List<Product> getListProducts() {
        return listProducts;
    }
}
