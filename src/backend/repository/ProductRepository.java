package backend.repository;

import backend.model.Category;
import backend.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static InterfaceControllers.StartPoint.openSecondWindow;

public class ProductRepository {
    private final Connection conn;

    public ProductRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        String query = """
                    SELECT p.id AS product_id, p.name AS product_name, p.description, p.price, 
                           c.id AS category_id, c.name AS category_name, SUM(wp.amount) AS total_amount
                    FROM product p
                    JOIN category c ON p.category = c.id
                    JOIN warehouse_product wp ON p.id = wp.product_id
                    GROUP BY p.id, p.name, p.description, p.price, c.id, c.name
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            readProducts(products, rs);
        } catch (SQLException e) {
            openSecondWindow("Ошибка при получении продуктов: " + e.getMessage(), "Ошибка сервера.");
            e.printStackTrace();
        }

        return products;
    }

    public List<Product> getProducts(Long categoryId) {
        List<Product> products = new ArrayList<>();

        String query = """
                    SELECT p.id AS product_id, p.name AS product_name, p.description, p.price, 
                           c.id AS category_id, c.name AS category_name, SUM(wp.amount) AS total_amount
                    FROM product p
                    JOIN category c ON p.category = c.id
                    JOIN warehouse_product wp ON p.id = wp.product_id
                    WHERE c.id = ?
                    GROUP BY p.id, p.name, p.description, p.price, c.id, c.name
                """;

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setLong(1, categoryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                readProducts(products, rs);
            }
        } catch (SQLException e) {
            openSecondWindow("Ошибка при получении продуктов: " + e.getMessage(), "Ошибка сервера.");
            e.printStackTrace();
        }

        return products;
    }

    private void readProducts(List<Product> products, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Long productId = rs.getLong("product_id");
            String productName = rs.getString("product_name");
            String description = rs.getString("description");
            BigDecimal price = rs.getBigDecimal("price");
            Long category_id = rs.getLong("category_id");
            String categoryName = rs.getString("category_name");
            int totalAmount = rs.getInt("total_amount");

            Category category = new Category(category_id, categoryName);

            Product product = new Product(productId, productName, description, price, category, totalAmount);

            products.add(product);
        }
    }
}
