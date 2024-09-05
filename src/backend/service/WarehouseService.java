package backend.service;

import backend.model.basket.Position;
import backend.model.client.Client;
import backend.model.order.Order;
import backend.model.order.OrderItem;
import backend.model.order.Status;
import backend.model.product.Product;
import backend.model.Warehouse;
import backend.repository.WarehouseRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static InterfaceControllers.StartPoint.openSecondWindow;

public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getWarehousesStoringProduct(Product product) {
        List<Warehouse> warehouseList = warehouseRepository.getWarehousesStoringProduct(product);
        for (Warehouse warehouse : warehouseList) {
            warehouse.updateProductStorage(warehouseRepository.getWarehouseStorage(warehouse.getId()));
        }

        return warehouseList;
    }

    public void writeOffProduct(Long productId, int amountDiff, List<Warehouse> warehouseList) {
        int remainingAmountToDecrease = amountDiff;

        for (Warehouse warehouse : warehouseList) {
            Map<Long, Integer> productsStorage = warehouse.getProductsStorage();
            Integer currentAmount = productsStorage.get(productId);

            if (currentAmount != null && currentAmount > 0) {
                int amountToDecrease = Math.min(currentAmount, remainingAmountToDecrease);

                productsStorage.put(productId, currentAmount - amountToDecrease);

                remainingAmountToDecrease -= amountToDecrease;
                if (remainingAmountToDecrease <= 0) {
                    break;
                }
            }
        }

        if (remainingAmountToDecrease > 0) {
            openSecondWindow("На складах недостаточно товара. Попробуйте позже.", "Ошибка списания товара.");
            return;
        }

        warehouseRepository.saveAllStorages(warehouseList);
    }

    public Order createOrder(List<Position> selectedPositions, Client client) {
        // Step 1: Create a new Order instance
        Order newOrder = new Order(client, Status.PENDING, client.getAgreement().companyAddress());

        // Step 2: Create the order items list
        var orderItems = selectedPositions.stream()
                .map( position -> new OrderItem(newOrder, position.getProduct(), position.getAmountInBasket()))
                .toList();

        newOrder.setOrderItems(orderItems);

        // Returning the newly created order
        try {
            warehouseRepository.saveOrder(newOrder);
            return newOrder;
        } catch (SQLException e) {
            openSecondWindow("Ошибка при сохранении продуктов: " + e.getMessage(), "Ошибка сервера.");
            e.printStackTrace();
            return null;
        }
    }
}
