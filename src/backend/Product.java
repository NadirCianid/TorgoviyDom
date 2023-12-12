package backend;

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
}
