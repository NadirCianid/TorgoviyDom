package backend.service;

import backend.model.product.Category;
import backend.model.product.Product;
import backend.model.Warehouse;
import backend.repository.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;
    private final WarehouseService warehouseService;

    public ProductService(ProductRepository productRepository, WarehouseService warehouseService) {
        this.productRepository = productRepository;
        this.warehouseService = warehouseService;
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

    public void decreaseProductAmount(Product currentProduct, int amountDiff) {
        List<Warehouse> warehouseList = warehouseService.getWarehousesStoringProduct(currentProduct);
        warehouseService.writeOffProduct(currentProduct.getId(), amountDiff, warehouseList);
    }


}
