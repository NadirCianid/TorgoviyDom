package backend.model.basket;

import backend.model.product.Product;
import backend.service.ProductService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private ProductService productService;
    private final List<Position> selectedPositions = new ArrayList<>();

    public boolean addProductInBasket(Product currentProduct, int amountDiff) {
        if(amountDiff > currentProduct.getAmount()) {
            return false;
        }

        Position positionToBeIncreased =  selectedPositions.stream()
                .filter(position -> position.getProduct().equals(currentProduct))
                .findAny()
                .orElse(null);

        productService.decreaseProductAmount(currentProduct, amountDiff);

        if(positionToBeIncreased == null) {
            Position newPosition = new Position(currentProduct, 0);
            newPosition.addInBasket(amountDiff);
            selectedPositions.add(newPosition);

            return true;
        }

        positionToBeIncreased.addInBasket(amountDiff);

        return true;
    }

    public ObservableList<Position> getSelectedPositions() {
        ObservableList<Position> positionObservableList = FXCollections.observableArrayList();
        positionObservableList.addAll(selectedPositions);
        return positionObservableList;
    }

    public BigDecimal getTotalSum() {
        final BigDecimal[] totalSum = {BigDecimal.ZERO};
        selectedPositions.forEach(position -> totalSum[0] = totalSum[0].add(position.getPositionCost()));
        return  totalSum[0];
    }

    public boolean basketIsEmpty() {
        return selectedPositions.size() <= 0 ;
    }

    public void dropPosition(Position positionToBeDropped) {
        selectedPositions.remove(positionToBeDropped);

        productService.decreaseProductAmount(positionToBeDropped.getProduct(), -positionToBeDropped.getAmountInBasket());
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
