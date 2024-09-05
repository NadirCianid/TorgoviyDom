package backend.repository;

import backend.model.order.Order;
import backend.model.order.OrderItem;
import backend.model.product.Product;
import backend.model.Warehouse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static InterfaceControllers.StartPoint.openSecondWindow;

public class WarehouseRepository {
    private final Connection conn;

    public WarehouseRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Warehouse> getWarehousesStoringProduct(Product product) {
        List<Warehouse> warehouses = new ArrayList<>();

        String query = """
                SELECT w.id, w.title, w.address, w.capacity, wp.amount
                                FROM warehouse w
                                JOIN warehouse_product wp ON w.id = wp.warehouse_id
                                WHERE wp.product_id = ?
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, product.getId());

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Long warehouseId = rs.getLong("id");
                    String title = rs.getString("title");
                    String address = rs.getString("address");
                    int capacity = rs.getInt("capacity");

                    Warehouse warehouse = new Warehouse(warehouseId, title, address, capacity, new HashMap<>());

                    warehouses.add(warehouse);
                }
            }
        } catch (SQLException e) {
            openSecondWindow("Ошибка при получении данных о складах: " + e.getMessage(), "Ошибка сервера.");
            e.printStackTrace();
        }

        return warehouses;
    }

    public Map<Long, Integer> getWarehouseStorage(Long warehouseId) {
        Map<Long, Integer> storage = new HashMap<>();

        String query = """
                SELECT
                    wp.product_id, wp.amount AS product_amount
                FROM warehouse_product wp
                WHERE
                        wp.warehouse_id = ?;
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, warehouseId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Long productId = rs.getLong("product_id");
                    int totalAmount = rs.getInt("product_amount");

                    storage.put(productId, totalAmount);
                }
            }
        } catch (SQLException e) {
            openSecondWindow("Ошибка при получении продуктов: " + e.getMessage(), "Ошибка сервера.");
            e.printStackTrace();
        }

        return storage;
    }

    public void saveAllStorages(List<Warehouse> warehouseList) {
        String query = """
                        INSERT INTO warehouse_product (warehouse_id, product_id, amount)
                        VALUES (?, ?, ?)
                        ON CONFLICT (warehouse_id, product_id) DO UPDATE SET amount = EXCLUDED.amount
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (Warehouse warehouse : warehouseList) {
                Long warehouseId = warehouse.getId();
                Map<Long, Integer> productsStorage = warehouse.getProductsStorage();

                for (Map.Entry<Long, Integer> entry : productsStorage.entrySet()) {
                    Long productId = entry.getKey();
                    Integer amount = entry.getValue();

                    pstmt.setLong(1, warehouseId);
                    pstmt.setLong(2, productId);
                    pstmt.setInt(3, amount);
                    pstmt.addBatch();
                }
            }

            pstmt.executeBatch();

        } catch (SQLException e) {
            openSecondWindow("Ошибка при сохранении продуктов: " + e.getMessage(), "Ошибка сервера.");
            e.printStackTrace();
        }
    }

    public void saveOrder(Order order) throws SQLException {
        String orderInsertQuery = "INSERT INTO \"order\" (client, status_id, address) VALUES (?, ?, ?)";
        String orderItemsInsertQuery = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

        try {
            // Begin transaction
            conn.setAutoCommit(false);

            // Insert order data
            try (PreparedStatement orderStatement = conn.prepareStatement(orderInsertQuery, Statement.RETURN_GENERATED_KEYS)) {
                orderStatement.setLong(1, order.getClient().getId());
                orderStatement.setInt(2, order.getStatus().getId());
                orderStatement.setString(3, order.getAddress());

                orderStatement.executeUpdate();

                // Retrieve the generated order ID
                ResultSet generatedKeys = orderStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long orderId = generatedKeys.getLong(1);

                    // Insert order items
                    try (PreparedStatement orderItemsStatement = conn.prepareStatement(orderItemsInsertQuery)) {
                        for (OrderItem item : order.getOrderItems()) {
                            orderItemsStatement.setLong(1, orderId);
                            orderItemsStatement.setLong(2, item.getProduct().getId());
                            orderItemsStatement.setInt(3, item.getQuantity());
                            orderItemsStatement.addBatch();
                        }
                        orderItemsStatement.executeBatch();
                    }
                }
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
