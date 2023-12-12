package backend;

import InterfaceControllers.StartPoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static InterfaceControllers.StartPoint.currentClient;

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
        //currentProduct.decreaseAmount(amountDiff);

        if(positionToBeIncreased == null) {
            Position newPosition = new Position(currentProduct, 0);
            newPosition.addInBasket(amountDiff);
            selectedPositions.add(newPosition);

            return true;
        }

        positionToBeIncreased.addInBasket(amountDiff);

        return true;
    }

    public boolean removeProductInBasket(Product currentProduct, int amountDiff) {
        Position positionToBeDecreased =  selectedPositions.stream()
                .filter(position -> position.getProduct().equals(currentProduct))
                .findAny()
                .orElse(null);

        if(positionToBeDecreased == null) {
            return false;
        }

        positionToBeDecreased.removeFromBasket(amountDiff);
        currentProduct.decreaseAmount(amountDiff);
        return true;
    }

    public ObservableList<Position> getSelectedPositions() {
        ObservableList<Position> positionObservableList = FXCollections.observableArrayList();
        positionObservableList.addAll(selectedPositions);
        return positionObservableList;
    }

    public int getTotalSum() {
        AtomicInteger totalSum = new AtomicInteger(0);
        selectedPositions.forEach(position -> totalSum.addAndGet(position.getPositionCost()));
        return  totalSum.get();
    }

    public boolean basketIsEmpty() {
        return selectedPositions.size() <= 0 ;
    }

    public void dropPosition(Position positionToBeDropped) {
        selectedPositions.remove(positionToBeDropped);
        StartPoint.warehouseController.returnProduct(positionToBeDropped);
    }
}
