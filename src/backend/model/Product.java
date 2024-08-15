package backend.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int amount;

    private Category category;

    public Product(Long id, String name, String description, BigDecimal price,  Category category, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.amount = amount;
    }

    public void takeFromWarehouse(int amountDiff) {
        amount -= amountDiff;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", category=" + category +
                '}';
    }

    public ObservableList<Integer> getPossibleAmounts() {
        ObservableList<Integer> possibleAmounts = FXCollections.observableArrayList();
        List<Integer> numberList = new ArrayList<>();

        int endRange = Math.min(amount, 5);

        for (int i = 1; i <= endRange; i++) {
            numberList.add(i);
        }

        possibleAmounts.addAll(numberList);
        return possibleAmounts;
    }

    public void decreaseAmount(int amountDiff) {
        amount -= amountDiff;
    }

    public void increaseAmount(int amountDiff) {
        amount += amountDiff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id.equals(product.id) && name.equals(product.name) && price.equals(product.price) && category.equals(product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }
}
