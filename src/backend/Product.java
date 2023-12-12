package backend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private int price;
    private int amount;

    private Category category;

    public Product(String name, Category category, int amount, int price) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.amount = amount;
    }

    public void takeFromWarehouse(int amountDiff) {
        amount -= amountDiff;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
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
}
