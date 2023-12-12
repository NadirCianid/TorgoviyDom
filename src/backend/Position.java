package backend;

import static InterfaceControllers.StartPoint.warehouseController;

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

    public int getPositionCost() {
        return cost;
    }

    public int getProductPrice() {
        return product.getPrice();
    }

    public String getProductName() {
        return product.getName();
    }


    public void addInBasket(int amountDiff) {
        if(amountDiff > product.getAmount()) {
            return;
        }
        amountInBasket += amountDiff;
        cost = product.getPrice() * amountInBasket;
        product.decreaseAmount(amountDiff);
    }

    public void removeFromBasket(int amountDiff) {
        if(amountDiff > amountInBasket) {
            return;
        }
        amountInBasket -= amountDiff;
        cost = product.getPrice() * amountInBasket;
        product.increaseAmount(amountDiff);
    }
}
