package backend.repository;

import backend.model.product.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static InterfaceControllers.StartPoint.openSecondWindow;

public class CategoryRepository {
    private final Connection conn;

    public CategoryRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Category> getCategories() {
        // Список для хранения всех категорий
        List<Category> categories = new ArrayList<>();

        // SQL запрос для получения всех категорий
        String query = "SELECT id, name FROM category";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Проходим по всем строкам результата
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");

                // Создаем объект Category и добавляем его в список
                Category category = new Category(id, name);
                categories.add(category);
            }

        } catch (SQLException e) {
            openSecondWindow("Ошибка при проверке получении категорий товаров: " + e.getMessage(),
                    "Ошибка сервера.");

            e.printStackTrace(); // Выводим ошибку в случае исключения
        }

        return categories;
    }
}
