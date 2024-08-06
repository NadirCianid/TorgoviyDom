package backend.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public enum Category {
    ALL,
    COFFEE,
    CUPS,
    MILK;

    public static ObservableList<Category> getCategories() {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        categories.addAll(Arrays.asList(Category.values()));

        return  categories;
    }
}
