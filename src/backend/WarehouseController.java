package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseController {
    private List<Product> products = new ArrayList<>(); //Список товаров, которые хранятся на складе



    public void fillWarehouse() {
        products.add(new Product("Arabica 1 kg", Category.COFFEE, 10, 400));
        products.add(new Product("Arabica 2 kg", Category.COFFEE, 20, 750));
        products.add(new Product("Paper cups", Category.CUPS, 100, 5));
        products.add(new Product("Plastic cups", Category.CUPS, 300, 2));
        products.add(new Product("Cow milk", Category.MILK, 50, 80));
    }

    public List<Product> filter(Category categoryFilter) {
      return products.stream().filter(product -> {
           return  product.getCategory().equals(categoryFilter);
        }).collect(Collectors.toList());
    }
}
