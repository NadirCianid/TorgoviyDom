package backend;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Position> selectedPositions = new ArrayList<>();

    public boolean addProductInBasket(Product currentProduct, int amountDiff) {
        if(amountDiff > currentProduct.getAmount()) {
            return false;
        }

        Position positionToBeIncreased =  selectedPositions.stream()
                .filter(position -> position.getProduct().equals(currentProduct))
                .findAny()
                .orElse(null);

        if(positionToBeIncreased == null) {
            return false;
        }

        positionToBeIncreased.addInBasket(amountDiff);
        return true;
    }

    public boolean decreaseProductInBasket(Product currentProduct, int amountDiff) {
        Position positionToBeDecreased =  selectedPositions.stream()
                .filter(position -> position.getProduct().equals(currentProduct))
                .findAny()
                .orElse(null);

        if(positionToBeDecreased == null) {
            return false;
        }

        positionToBeDecreased.removeFromBasket(amountDiff);
        return true;
    }
}
