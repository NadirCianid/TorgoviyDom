package backend.service;

import backend.model.Category;
import backend.repository.CategoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ObservableList<Category> getCategories() {
        ObservableList<Category> categories = FXCollections.observableArrayList();

        categories.addAll(categoryRepository.getCategories());

        return categories;
    }
}
