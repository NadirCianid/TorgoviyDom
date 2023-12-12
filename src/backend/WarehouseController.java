package backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class WarehouseController {
    private List<Product> products = new ArrayList<>(); //Список товаров, которые хранятся на складе

    public void fillWarehouse() {
        products.add(new Product("Arabica 1 kg", Category.COFFEE, 10, 400));
        products.add(new Product("Arabica 2 kg", Category.COFFEE, 20, 750));
        products.add(new Product("Paper cups", Category.CUPS, 100, 5));
        products.add(new Product("Plastic cups", Category.CUPS, 300, 2));
        products.add(new Product("Cow milk", Category.MILK, 50, 80));
    }

    public ObservableList<Product> filter(Category categoryFilter) {
        ObservableList<Product> productObservableList =  FXCollections.observableArrayList();

        if(categoryFilter.equals(Category.ALL)) {
            productObservableList.addAll(products);
            return productObservableList;
        }

        List<Product> filteredProducts = products.stream()
                .filter(product -> product.getCategory().equals(categoryFilter))
                .toList();

        productObservableList.addAll(filteredProducts);

      return productObservableList;
    }

    private Product getProduct(String productName) {
        return  products.stream()
                .filter(product -> product.getName().equals(productName))
                .findAny().orElse(null);
    }

    public ObservableList<Product> getProducts() {
        ObservableList<Product> productObservableList =  FXCollections.observableArrayList();
        productObservableList.addAll(products);

        return productObservableList;
    }

    public void returnProduct(Position positionToBeAdded) {
        Product product = getProduct(positionToBeAdded.getProductName());

        if(product == null) {
            products.add(new Product(positionToBeAdded.getProductName(),
                    positionToBeAdded.getProduct().getCategory(),
                    positionToBeAdded.getAmountInBasket(),
                    positionToBeAdded.getProductPrice()));
        } else {
            product.increaseAmount(positionToBeAdded.getAmountInBasket());
        }

        positionToBeAdded = null;
    }

    public void makeOrder(Basket basket) {
        basket = null; //Здесь должен быть код по обработке заказа, но с ним пока нечего делать. Оставим это на будущее
    }
}
