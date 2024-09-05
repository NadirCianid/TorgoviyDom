package backend.model.basket;

import backend.model.product.Product;

import java.math.BigDecimal;

public class Position {
    private final Product product;
    private int amountInBasket;
    private BigDecimal cost;

    public Position(Product product, int amountInBasket) {
        this.product = product;
        this.amountInBasket = amountInBasket;
        cost = product.getPrice().multiply(BigDecimal.valueOf(amountInBasket));
    }

    public Product getProduct() {
        return product;
    }

    public int getAmountInBasket() {
        return amountInBasket;
    }

    public BigDecimal getPositionCost() {
        return cost;
    }

    public BigDecimal getProductPrice() {
        return product.getPrice();
    }

    public String getProductName() {
        return product.getName();
    }


    public void addInBasket(int amountDiff) {
        if (amountDiff > product.getAmount()) {
            return;
        }
        amountInBasket += amountDiff;
        cost = product.getPrice().multiply(BigDecimal.valueOf(amountInBasket));
        product.decreaseAmount(amountDiff);
    }

    public void removeFromBasket(int amountDiff) {
        if (amountDiff > amountInBasket) {
            return;
        }
        amountInBasket -= amountDiff;
        cost = product.getPrice().multiply(BigDecimal.valueOf(amountInBasket));
        product.increaseAmount(amountDiff);
    }
}
