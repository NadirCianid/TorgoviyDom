package backend;

public class Position {
    private Product product;
    private int amountInBasket;
    private int cost;

    public Position(Product product, int amountInBasket) {
        this.product = product;
        this.amountInBasket = amountInBasket;
        cost = product.getPrice() * amountInBasket;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmountInBasket() {
        return amountInBasket;
    }

    public int getCost() {
        return cost;
    }

    public void addInBasket(int amountDiff) {
        amountInBasket += amountDiff;
    }

    public void removeFromBasket(int amountDiff) {
        amountInBasket -= amountDiff;
    }
}
