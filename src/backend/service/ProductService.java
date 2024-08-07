package backend.service;

import backend.model.Category;
import backend.model.Product;
import backend.repository.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();

        products.addAll(productRepository.getProducts());

        return products;
    }

    public ObservableList<Product> getProducts(Category categoryToBeDisplayed) {
        ObservableList<Product> products = FXCollections.observableArrayList();

        products.addAll(productRepository.getProducts(categoryToBeDisplayed.getId()));

        return products;
    }
}
